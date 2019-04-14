/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.entity.Constant;
import com.nebula.mooc.core.entity.LoginUser;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.util.CookieUtil;
import com.nebula.mooc.webserver.service.CodeService;
import com.nebula.mooc.webserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//注意：@RestController = @ResponseBody + @Controller
@Controller
@RequestMapping("/sysUser/")
public class UserController {

    private final CodeService codeService;

    private final UserService userService;

    @Autowired
    public UserController(CodeService codeService, UserService userService) {
        this.codeService = codeService;
        this.userService = userService;
    }

    @PostMapping(value = "login")
    @ResponseBody
    public Return<String> login(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                LoginUser loginUser, String imgCode) throws IOException {
        if (loginUser == null) return Return.ERROR;
        boolean result = codeService.verifyImgCode(imgCode, session);
        if (result) {
            String sessionId = request.getSession().getId();
            result = userService.login(sessionId, loginUser);
            if (result) {
                //成功登陆，设置Cookie
                CookieUtil.set(response, Constant.SESSION_ID, sessionId);
            }
        }
        return Return.SUCCESS;
    }

    @GetMapping(value = "loginCheck")
    @ResponseBody
    public boolean loginCheck(HttpServletRequest request) {
        // 1. 检查是否浏览器Cookie中是否有sessionId
        String sessionId = CookieUtil.get(request, Constant.SESSION_ID);
        if (sessionId == null)
            return false;
        return userService.loginCheck(sessionId);
    }

    @GetMapping(value = "logout")
    @ResponseBody
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = CookieUtil.get(request, Constant.SESSION_ID);
        if (sessionId == null)
            return;
        CookieUtil.remove(request, response, Constant.SESSION_ID);
        userService.logout(sessionId);
    }
}
