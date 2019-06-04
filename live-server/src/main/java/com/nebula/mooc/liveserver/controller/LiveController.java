/*
 * @author Zhanghh
 * @date 2019/6/1
 */
package com.nebula.mooc.liveserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.Live;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.liveserver.service.LiveService;
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

    @Autowired
    private LiveService liveService;

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
        if (token != null) {
            UserInfo userInfo = liveService.getUserInfo(token);
            if (userInfo != null) return userInfo;
            if (userService.loginCheck(token)) {
                userInfo = userService.getUserInfo(token);
                liveService.putUserInfo(token, userInfo);
                return userInfo;
            }
        }
        return null;
    }

    @PostMapping(value = "newLive")
    public Return newLive(HttpServletRequest request, Live live) {
        if (live.getTitle() == null || live.getTitle().trim().length() == 0) return Return.CLIENT_PARAM_ERROR;
        String token = getToken(request);
        UserInfo userInfo = getUserInfo(token);
        if (userInfo == null) return new Return(Constant.CLIENT_NOT_LOGIN);
        if (liveService.getMyLive(userInfo.getId()) != null)
            return new Return(Constant.CLIENT_REGISTERED);
        return new Return<>(liveService.newLive(userInfo, live) + "?token=" + token);
    }

    @GetMapping(value = "getLiveList")
    public Return getLiveList() {
        return new Return<>(liveService.getLiveList());
    }

    @GetMapping(value = "getMyLive")
    public Return getMyLive(HttpServletRequest request) {
        String token = getToken(request);
        UserInfo userInfo = getUserInfo(token);
        if (userInfo == null) return new Return(Constant.CLIENT_NOT_LOGIN);
        return new Return<>(liveService.getMyLive(userInfo.getId()));
    }

}
