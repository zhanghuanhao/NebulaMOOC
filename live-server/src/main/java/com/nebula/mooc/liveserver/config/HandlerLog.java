/*
 * @author Zhanghh
 * @date 2019/4/3
 */
package com.nebula.mooc.liveserver.config;

import com.nebula.mooc.liveserver.core.ChatMessage;
import io.netty.channel.ChannelHandlerContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class HandlerLog {

    private static final Logger logger = LoggerFactory.getLogger(HandlerLog.class);

    /**
     * 代理方法：ChatHandler --> channelRead
     */
    @Pointcut("target(com.nebula.mooc.liveserver.handler.ChatHandler) && execution(public * *.channelRead(..))")
    public void chatChannelRead() {
    }

    /**
     * 代理方法：HandshakeHandler --> channelRead
     */
    @Pointcut("target(com.nebula.mooc.liveserver.handler.HandshakeHandler) && execution(public * *.channelRead(..))")
    public void handShakeChannelRead() {
    }

    /**
     * 代理方法：ChatHandler --> channelActive和channelInactive
     */
    @Pointcut("execution(public * com.nebula.mooc.liveserver.handler.ChatHandler.channel*(..))")
    public void chatChannelStatus() {
    }

    @Around("chatChannelRead()")
    public void chatChannelReadLog(ProceedingJoinPoint proceedingJoinPoint) {
        channelReadLog(proceedingJoinPoint);
    }

    @Around("handShakeChannelRead()")
    public void handShakeChannelReadLog(ProceedingJoinPoint proceedingJoinPoint) {
        channelReadLog(proceedingJoinPoint);
    }

    /**
     * 打印channelRead的消耗时间和参数
     */
    private void channelReadLog(ProceedingJoinPoint proceedingJoinPoint) {
        long costTime = System.currentTimeMillis();
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        costTime = System.currentTimeMillis() - costTime;
        Object[] args = proceedingJoinPoint.getArgs();
        String param;
        if (args[1] instanceof ChatMessage.request) {
            ChatMessage.request request = (ChatMessage.request) args[1];
            param = String.format("Code: %d, Msg: %s", request.getCode(), request.getMsg());
        } else
            param = args[1].getClass().getSimpleName();
        logger.info("{} -> channelRead:  Cost: {}ms, Address: {}, Param: [{}]",
                proceedingJoinPoint.getTarget().getClass().getSimpleName(), costTime,
                ((ChannelHandlerContext) args[0]).channel().remoteAddress(), param);
    }

    /**
     * 记录ChatHandler中的channelActive和channelInactive
     */
    @Around("chatChannelStatus()")
    public void chatHandlerChannelStatusLog(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        Object[] args = proceedingJoinPoint.getArgs();
        String action;
        if ("channelActive".equals(proceedingJoinPoint.getSignature().getName())) action = "Add";
        else action = "Remove";
        logger.info("ChatHandler -> {}: {} Channel{}", proceedingJoinPoint.getSignature().getName(),
                action, ((ChannelHandlerContext) args[0]).channel());
    }

}