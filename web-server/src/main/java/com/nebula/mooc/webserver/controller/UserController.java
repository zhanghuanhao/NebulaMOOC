/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.LoginUser;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.webserver.service.CodeService;
import com.nebula.mooc.webserver.service.UserService;
import com.nebula.mooc.webserver.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//注意：@RestController = @ResponseBody + @Controller
@RestController
@RequestMapping("/sys/user/")
public class UserController {

    @Autowired
    private CodeService codeService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "login")
    public Return login(HttpServletResponse response, HttpSession session,
                        LoginUser loginUser) {
        if (!codeService.verifyImgCode(loginUser.getCode(), session))
            return Return.CODE_ERROR;
        String token = userService.login(loginUser);
        if (token == null) return new Return(Constant.CLIENT_ERROR_CODE, "账号或密码错误，请重试！");
        //成功登陆
        UserInfo userInfo = userService.getUserInfo(token);
        CacheUtil.set(session, response, token, userInfo);
        codeService.clearImgCode(session);
        return new Return<>(userInfo);
    }

    @GetMapping(value = "logout")
    public Return logout(HttpServletRequest request, HttpServletResponse response) {
        String token = CacheUtil.getToken(request);
        if (token != null) {
            CacheUtil.remove(request, response);
            if (userService.logout(token))
                return Return.SUCCESS;
        }
        return Return.SERVER_ERROR;
    }

    @PostMapping(value = "register")
    public Return register(HttpSession session, LoginUser loginUser) {
        if (!codeService.verifyMailCode(loginUser.getCode(), session)) return Return.CODE_ERROR;
        // 邮件验证码验证成功
        int result = userService.register(loginUser);
        if (result == Constant.CLIENT_ERROR_CODE) return new Return(result, "注册失败，请重试！");
        else if (result == Constant.CLIENT_REGISTERED) return new Return(result, "账号已注册！");
        codeService.clearMailCode(session);
        return Return.SUCCESS;
    }

    @PostMapping(value = "resetPassword")
    public Return resetPassword(HttpSession session, LoginUser loginUser) {
        boolean result = codeService.verifyMailCode(loginUser.getCode(), session);
        if (!result) return Return.CODE_ERROR;
        // 邮件验证码验证成功
        result = userService.resetPassword(loginUser);
        if (!result) return new Return(Constant.CLIENT_ERROR_CODE, "重置密码失败，请重试！");
        return Return.SUCCESS;
    }

    @PostMapping(value = "checkUser")
    public Return checkUser(String email) {
        if (email == null || email.length() == 0) return null;
        if (userService.checkUserExist(email))
            return new Return(Constant.CLIENT_ERROR_CODE, "该账号已存在！");
        else return Return.SUCCESS;
    }

}
