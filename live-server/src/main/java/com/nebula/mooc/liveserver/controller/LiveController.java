/*
 * @author Zhanghh
 * @date 2019/6/1
 */
package com.nebula.mooc.liveserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.liveserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private UserInfo getUserInfo(HttpServletRequest request) {
        String token = getToken(request);
        if (token != null && userService.loginCheck(token))
            return userService.getUserInfo(token);
        return null;
    }

    @PostMapping(value = "newLive")
    public Return newLive(HttpServletRequest request) {
        UserInfo userInfo = getUserInfo(request);
        if (userInfo == null) return new Return(Constant.CLIENT_NOT_LOGIN);
        return null;
    }

}
