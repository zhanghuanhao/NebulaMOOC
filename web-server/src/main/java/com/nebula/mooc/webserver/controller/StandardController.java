/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.webserver.controller;

import com.nebula.mooc.webserver.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

@RestController
public class StandardController {

    @GetMapping(value = "get")
    public RedirectView index() {
        return new RedirectView("index.html");
    }

    @Resource
    TestService testService;

    @GetMapping(value = "getNickName")
    public String count() {
        return testService.getNickName();
    }
}