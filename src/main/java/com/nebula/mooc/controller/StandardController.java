/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.controller;

import com.nebula.mooc.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StandardController {

    //    @RequestMapping(value = "getIndex")
//    public ModelAndView index() {
//        return new ModelAndView(new RedirectView("index.html"));
//    }
//
    @Resource
    TestService testService;

    @RequestMapping(value = "getNickName")
    public String count() {
        return testService.getNickName();
    }
}