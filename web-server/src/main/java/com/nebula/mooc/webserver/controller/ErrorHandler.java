/*
 * @author Zhanghh
 * @date 2019/4/15
 */
package com.nebula.mooc.webserver.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局错误处理器
 */
@RestController
public class ErrorHandler implements ErrorController {

    private static final String ErrorPath = "/error";

    @Override
    public String getErrorPath() {
        return ErrorPath;
    }

    /**
     * 错误处理控制器
     */
    @RequestMapping(value = ErrorPath, method = {RequestMethod.GET, RequestMethod.POST})
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return String.format("<html><body><h2>Error Page</h2><div>Status code: <b>%s</b></div>"
                        + "<div>Exception Message: <b>%s</b></div></body></html>",
                statusCode, exception == null ? "N/A" : exception.getMessage());
    }


}
