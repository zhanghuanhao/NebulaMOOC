/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.entity.Constant;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.entity.User;
import com.nebula.mooc.core.util.CookieUtil;
import com.nebula.mooc.core.util.TokenUtil;
import com.nebula.mooc.webserver.service.CodeService;
import com.nebula.mooc.webserver.service.UserService;
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

    private final CodeService codeService;

    private final UserService userService;

    @Autowired
    public UserController(CodeService codeService, UserService userService) {
        this.codeService = codeService;
        this.userService = userService;
    }

    @PostMapping(value = "login")
    public Return<String> login(HttpServletRequest request, HttpServletResponse response,
                                HttpSession session, User user) throws IOException {
        boolean result = codeService.verifyImgCode(user.getCode(), session);
        if (!result) return Return.CODE_ERROR;
        String sessionId = request.getSession().getId();
        String token = TokenUtil.generateToken(sessionId);
        result = userService.login(token, user);
        if (!result) return Return.USER_ERROR;
        //成功登陆，设置Cookie
        CookieUtil.set(response, Constant.TOKEN, token);
        return Return.SUCCESS;
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
    public Return register(HttpSession session, User user) throws IOException {
        System.out.println(user.getCode());
        boolean result = codeService.verifyMailCode(user.getCode(), session);
        if (!result) return Return.CODE_ERROR;
        // 邮件验证码验证成功
        result = userService.register(user);
        if (!result) return new Return<>(Constant.CLIENT_ERROR_CODE, "注册失败，请重试！");
        return Return.SUCCESS;
    }

    @PostMapping(value = "resetPassword")
    public Return resetPassword(HttpSession session, User user) throws IOException {
        boolean result = codeService.verifyMailCode(user.getCode(), session);
        if (!result) return Return.CODE_ERROR;
        // 邮件验证码验证成功
        result = userService.resetPassword(user);
        if (!result) return new Return<>(Constant.CLIENT_ERROR_CODE, "重置密码失败，请重试！");
        return Return.SUCCESS;
    }

    @PostMapping(value = "resetPassword")
    public Return resetPassword(HttpSession session, User user) throws IOException {
        boolean result = codeService.verifyMailCode(user.getImgCode(), session);
        if (!result) return Return.CODE_ERROR;
        // 邮件验证码验证成功
        result = userService.resetPassword(user);
        if (!result) return Return.SERVER_ERROR;
        return Return.SUCCESS;
    }
}
