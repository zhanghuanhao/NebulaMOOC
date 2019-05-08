package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.core.service.UserService;
import com.nebula.mooc.webserver.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*  请求发送验证码 */
@RestController
@RequestMapping("/sys/code/")
public class CodeController {

    @Autowired
    private CodeService codeService;
    @Autowired
    private UserService userService;

    @PostMapping("getMailCode")
    public Return getMailCode(HttpServletRequest request, HttpSession session) {
        if (userService.checkuser(request.getParameter("address")))
            return new Return(300, "该账号已存在");
        if (codeService.sendMailCode(request, session)) return Return.SUCCESS;
        else return Return.SERVER_ERROR;
    }

    @GetMapping("getImgCode")
    public Return getImgCode(HttpServletResponse response, HttpSession session) throws IOException {
        if (codeService.sendImgCode(response, session)) return Return.SUCCESS;
        else return Return.SERVER_ERROR;
    }

}

