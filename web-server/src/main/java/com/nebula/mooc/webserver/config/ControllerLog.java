/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.webserver.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器切面，记录请求及用时
 */
@Aspect
@Configuration
public class ControllerLog {

    private static final Logger logger = LoggerFactory.getLogger(ControllerLog.class);

    /**
     * 定义AOP扫描路径
     */
    @Pointcut("within(com.nebula.mooc.webserver.controller.*)")
    public void controllerLog() {
    }

    @Around("controllerLog()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        long costTime = System.currentTimeMillis();
        Object returnVal = null;
        try {
            returnVal = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        costTime = System.currentTimeMillis() - costTime;
        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            // 记录下请求内容：花费的时间、路径、方法、IP、Port
            logger.info("Path: {} Method: {} Cost: {}ms IP: {} Port: {}",
                    request.getServletPath(), request.getMethod(),
                    costTime, request.getRemoteAddr(), request.getRemotePort());
        }
        return returnVal;
    }
}