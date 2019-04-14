package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.webserver.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*  请求发送邮件验证码 */
@Controller
@RequestMapping("/sysCode/")
public class CodeController {

    private final CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("getMailCode")
    public void getMailCode(HttpServletRequest request, HttpSession session) {
        codeService.sendMailCode(request, session);
    }

    @GetMapping("getImgCode")
    public void codeTest(HttpServletResponse response, HttpSession session) throws IOException {
        codeService.sendImgCode(response, session);
    }

}

