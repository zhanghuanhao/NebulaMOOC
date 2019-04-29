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
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    /**
     * 定义AOP扫描路径
     */
    @Pointcut("execution(public * com.nebula.mooc.webserver.controller.*.*(..))")
    public void controllerLog() {
    }

    @Around("controllerLog()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object returnVal = null;
        try {
            returnVal = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        stopWatch.stop();
        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            // 记录下请求内容：花费的时间、路径、方法、IP、Port
            logger.info("Path: {} Method: {} Cost: {}ms IP: {} Port: {}",
                    request.getServletPath(), request.getMethod(),
                    stopWatch.getTotalTimeMillis(),
                    request.getRemoteAddr(), request.getRemotePort());
        } else
            logger.warn("Unknown request Cost: {}ms", stopWatch.getTotalTimeMillis());
        return returnVal;
    }
}