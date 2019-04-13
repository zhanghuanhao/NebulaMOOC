/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.core.entity.Constant;
import com.nebula.mooc.core.entity.LoginUser;
import com.nebula.mooc.core.entity.Return;
import com.nebula.mooc.webserver.service.SsoService;
import com.nebula.mooc.webserver.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

//注意：@RestController = @ResponseBody + @Controller
@Controller
public class StandardController {

    @Resource
    TestService testService;

    @GetMapping(value = "getNickName")
    public String count() {
        return testService.getNickName();
    }

    @RequestMapping("/json")
    @ResponseBody
    public Return<LoginUser> json(Model model, HttpServletRequest request) {
        LoginUser xxlLoginUser = (LoginUser) request.getAttribute(Constant.SSO_USER);
        return new Return(xxlLoginUser);
    }

    @Resource
    SsoService ssoService;

    @GetMapping(value = "sso")
    @ResponseBody
    public String sso() {
        return ssoService.test("web");
    }

}