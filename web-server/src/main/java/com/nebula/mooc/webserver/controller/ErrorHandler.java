/*
 * @author Zhanghh
 * @date 2019/4/15
 */
package com.nebula.mooc.webserver.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局错误处理器
 */
@Controller
public class ErrorHandler implements ErrorController, HandlerExceptionResolver {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        //获取statusCode:404,500等
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 404)
            return "/err/404";
        else if (statusCode == 500)
            return "/err/500";
        else
            return "/err/default";
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception exception) {
        if (exception instanceof MaxUploadSizeExceededException)
            request.setAttribute("javax.servlet.error.status_code", 404);
        return new ModelAndView("/error");
    }

}
