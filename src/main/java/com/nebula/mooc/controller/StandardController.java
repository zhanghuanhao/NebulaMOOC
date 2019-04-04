/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StandardController {

    @RequestMapping(value = "/")
    public String index() {
        return "forward:/index.html";
    }

}