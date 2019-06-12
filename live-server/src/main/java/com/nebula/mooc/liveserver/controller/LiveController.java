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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/live/")
@CrossOrigin(origins = "*", allowCredentials = "true")     // 解决跨域
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
        if (token != null && userService.loginCheck(token)) {
            return userService.getUserInfo(token);
        }
        return null;
    }

    @PostMapping(value = "newLive")
    public Return newLive(HttpServletRequest request, Live live) {
        if (live.getTitle() == null || live.getTitle().trim().length() == 0) return Return.CLIENT_PARAM_ERROR;
        String token = getToken(request);
        UserInfo userInfo = getUserInfo(token);
        if (userInfo == null) return new Return(Constant.CLIENT_NOT_LOGIN);
        String liveToken = liveService.getLiveToken(userInfo.getId());
        if (liveToken != null)
            return new Return(Constant.CLIENT_REGISTERED);
        live.setUserInfo(userInfo);
        return new Return<>(userInfo.getId() + "?token=" + liveService.newLive(userInfo.getId(), live));
    }

    @GetMapping(value = "getLiveList")
    public Return getLiveList() {
        return new Return<>(liveService.getLiveList());
    }

    @GetMapping(value = "getLive")
    public Return getLive(long id) {
        return new Return<>(liveService.getLive(id));
    }

    @GetMapping(value = "getMyLive")
    public Return getMyLive(HttpServletRequest request) {
        String token = getToken(request);
        UserInfo userInfo = getUserInfo(token);
        if (userInfo == null) return new Return(Constant.CLIENT_NOT_LOGIN);
        long userId = userInfo.getId();
        Return ret = new Return<>(liveService.getLive(userId));
        ret.setMsg(userId + "?token=" + liveService.getLiveToken(userId));
        return ret;
    }

}
