/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.LoginUser;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.service.UserService;
import com.nebula.mooc.webserver.service.CodeService;
import com.nebula.mooc.webserver.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
                        LoginUser loginUser) throws IOException {
        if (!codeService.verifyImgCode(loginUser.getCode(), session))
            return Return.CODE_ERROR;
        String token = userService.login(loginUser);
        if (token == null)
            return Return.USER_ERROR;
        //成功登陆，设置Cookie
        CookieUtil.set(response, Constant.TOKEN, token);
        return new Return<>(userService.getUserInfo(token));
    }

    @GetMapping(value = "logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieUtil.get(request, Constant.TOKEN);
        if (token != null) {
            CookieUtil.remove(request, response, Constant.TOKEN);
            userService.logout(token);
        }
    }

    @PostMapping(value = "register")
    public Return register(HttpSession session, LoginUser loginUser) throws IOException {
        System.out.println(loginUser.getCode());
        boolean result = codeService.verifyMailCode(loginUser.getCode(), session);
        if (!result) return Return.CODE_ERROR;
        // 邮件验证码验证成功
        result = userService.register(loginUser);
        if (!result) return new Return<>(Constant.CLIENT_ERROR_CODE, "注册失败，请重试！");
        return Return.SUCCESS;
    }

    @PostMapping(value = "resetPassword")
    public Return resetPassword(HttpSession session, LoginUser loginUser) throws IOException {
        boolean result = codeService.verifyMailCode(loginUser.getCode(), session);
        if (!result) return Return.CODE_ERROR;
        // 邮件验证码验证成功
        result = userService.resetPassword(loginUser);
        if (!result) return new Return<>(Constant.CLIENT_ERROR_CODE, "重置密码失败，请重试！");
        return Return.SUCCESS;
    }
}
