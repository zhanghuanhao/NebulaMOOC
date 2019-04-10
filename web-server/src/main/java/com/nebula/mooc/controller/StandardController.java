/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.controller;

import com.nebula.mooc.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StandardController {

//    @GetMapping(value = "get")
//    public ModelAndView index() {
//        return new ModelAndView(new RedirectView("index.html"));
//    }

    @Resource
    TestService testService;

    @GetMapping(value = "getNickName")
    public String count() {
        return testService.getNickName();
    }
}