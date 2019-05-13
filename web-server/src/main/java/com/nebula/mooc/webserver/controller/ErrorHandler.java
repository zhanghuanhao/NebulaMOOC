/*
 * @author Zhanghh
 * @date 2019/4/15
 */
package com.nebula.mooc.webserver.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局错误处理器
 */
@Controller
public class ErrorHandler implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        //获取statusCode:404,500等
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        // 只有当找不到html时返回
        if (statusCode == 500)
            return "/err/500";
        else
            return "/err/404";
    }

}
