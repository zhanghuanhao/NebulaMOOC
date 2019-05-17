/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.ssoserver.config;

import com.nebula.mooc.core.UserMessage;
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
     * target：指定实现类，在这里使用cglib作为动态代理
     */
    @Pointcut("target(com.nebula.mooc.ssoserver.service.UserService)")
    public void userServiceLog() {
    }

    @Around("userServiceLog()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) {
        long costTime = System.currentTimeMillis();
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        costTime = System.currentTimeMillis() - costTime;
        Object arg = proceedingJoinPoint.getArgs()[0];
        int size = 0;
        if (arg instanceof UserMessage.StringRet) {
            size = ((UserMessage.StringRet) arg).getSerializedSize();
            arg = ((UserMessage.StringRet) arg).getRet();
        } else if (arg instanceof UserMessage.LoginUser) {
            size = ((UserMessage.LoginUser) arg).getSerializedSize();
            arg = ((UserMessage.LoginUser) arg).getUsername();
        } else if (arg instanceof UserMessage.User) {
            size = ((UserMessage.User) arg).getSerializedSize();
            arg = ((UserMessage.User) arg).getId();
        } else arg = "Unknown";
        logger.info("Service: {}, Cost: {}ms, Param: {}, Size: {} bytes",
                proceedingJoinPoint.getSignature().getName(),
                costTime, arg, size);
    }
}