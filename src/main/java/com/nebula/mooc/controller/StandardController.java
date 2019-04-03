/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StandardController {

    @GetMapping(value = "/")
    public int aopTest() {
        return 0;
    }

}