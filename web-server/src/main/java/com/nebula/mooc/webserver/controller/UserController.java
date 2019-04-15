/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.entity.Constant;
import com.nebula.mooc.core.entity.LoginUser;
import com.nebula.mooc.core.entity.Return;
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
    public Return<String> login(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                LoginUser loginUser, String imgCode) throws IOException {
        boolean result = codeService.verifyImgCode(imgCode, session);
        if (result) {
            String sessionId = request.getSession().getId();
            String token = TokenUtil.generateToken(sessionId);
            result = userService.login(token, loginUser);
            if (result) {
                //成功登陆，设置Cookie
                CookieUtil.set(response, Constant.TOKEN, token);
            } else return Return.USER_ERROR;
        } else return Return.CODE_ERROR;
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
}
