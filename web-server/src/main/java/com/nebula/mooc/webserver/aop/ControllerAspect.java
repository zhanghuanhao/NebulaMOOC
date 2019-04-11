/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.webserver.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ControllerAspect {

    private static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    /**
     * 定义AOP扫描路径
     */
    @Pointcut("execution(public * com.nebula.mooc.controller.*.*())")
    public void webLog() {
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            // 记录下请求内容
            logger.info("IP: {} Port: {} Path: {} Parameter: {}",
                    request.getRemoteAddr(), request.getRemotePort(),
                    request.getServletPath(), request.getQueryString());
        }
    }

    @Around("webLog()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            logger.error("Error", e);
            return null;
        }
    }
}