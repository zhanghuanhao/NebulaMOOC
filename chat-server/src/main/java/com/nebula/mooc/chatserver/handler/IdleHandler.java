/*
 * @author Zhanghh
 * @date 2019/4/24
 */
package com.nebula.mooc.chatserver.handler;

import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class IdleHandler extends IdleStateHandler {

    /**
     * IdleStateHandler: 空闲状态处理器。
     * 四个参数：1.读空闲； 2.写空闲； 3.读写空闲； 4.时间单位。
     * 所谓的空闲是指多长时间没有发生过对应的时间，就触发调用.
     */
    public IdleHandler() {
        super(0, 0, 10, TimeUnit.SECONDS);
    }
}
