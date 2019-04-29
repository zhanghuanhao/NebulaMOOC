/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.ssoserver.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ServiceAspect {

    private static final Logger logger = LoggerFactory.getLogger(ServiceAspect.class);

    /**
     * 定义AOP扫描路径
     */
    @Pointcut("execution(public * com.nebula.mooc.ssoserver.service.*.*(..))")
    public void serviceLog() {
    }

    @Around("serviceLog()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object returnVal = null;
        try {
            returnVal = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        stopWatch.stop();
        logger.info("Service Name: {} Cost: {}ms",
                proceedingJoinPoint.getSignature().getName(),
                stopWatch.getTotalTimeMillis());
        return returnVal;
    }
}