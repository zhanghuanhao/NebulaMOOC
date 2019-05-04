/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.ssoserver.config;

import com.nebula.mooc.core.util.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
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
        StopWatch stopWatch = StopWatch.newInstance();
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