/*
 * @author Zhanghh
 * @date 2019/5/13
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.webserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户登录后修改个人信息的控制器
 */
@RestController
@RequestMapping("/api/user/")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "updateUser")
    public Return updateUser(UserInfo userInfo) {
        if (userInfo == null) return null;
        if (userService.updateUser(userInfo))
            return new Return(Constant.CLIENT_ERROR_CODE, "修改失败，请重试！");
        else return Return.SUCCESS;
    }

    @PostMapping(value = "getUserInfo")
    public Return getUserInfo(HttpServletRequest request) {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
        return new Return<>(userInfo);
    }

}
