/*
 * @author Zhanghh
 * @date 2019/5/18
 */
package com.nebula.mooc.webserver.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ServiceLog {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLog.class);

    /**
     * 定义AOP扫描路径
     */
    @Pointcut("within(com.nebula.mooc.webserver.service.impl.*)")
    public void serviceLog() {
    }

    @Around("serviceLog()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        long costTime = System.currentTimeMillis();
        Object returnVal = null;
        try {
            returnVal = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        costTime = System.currentTimeMillis() - costTime;
        logger.info("Service: {} -> {}, Cost: {}ms",
                proceedingJoinPoint.getTarget().getClass().getSimpleName(),
                proceedingJoinPoint.getSignature().getName(), costTime);
        return returnVal;
    }
}
