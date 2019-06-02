/*
 * @author Zhanghh
 * @date 2019/6/1
 */
package com.nebula.mooc.liveserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.Live;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.liveserver.core.LiveManager;
import com.nebula.mooc.liveserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/live/")
public class LiveController {

    @Autowired
    private UserService userService;

    private String getToken(HttpServletRequest request) {
        Cookie[] arr_cookie = request.getCookies();
        if (arr_cookie != null) {
            for (Cookie cookie : arr_cookie) {
                if (Constant.TOKEN.equals(cookie.getName()))
                    return cookie.getValue();
            }
        }
        return null;
    }

    private UserInfo getUserInfo(String token) {
        if (token != null && userService.loginCheck(token))
            return userService.getUserInfo(token);
        return null;
    }

    @PostMapping(value = "newLive")
    public Return newLive(HttpServletRequest request, Live live) {
        if (live.getTitle() == null || live.getTitle().trim().length() == 0) return Return.CLIENT_PARAM_ERROR;
        String token = getToken(request);
        UserInfo userInfo = getUserInfo(token);
        if (userInfo == null) return new Return(Constant.CLIENT_NOT_LOGIN);
        if (LiveManager.getLive(token) != null) return new Return(Constant.CLIENT_REGISTERED);
        live.setUserInfo(userInfo);
        String liveToken = LiveManager.newLive(token, live);
        return new Return<>(liveToken);
    }

    @GetMapping(value = "getList")
    public Return getList() {
        return new Return<>(LiveManager.getList());
    }

}
