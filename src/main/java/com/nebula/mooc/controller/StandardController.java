/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class StandardController {

    @RequestMapping(value = "test")
    public ModelAndView index() {
        return new ModelAndView(new RedirectView("index.html"));
    }
}