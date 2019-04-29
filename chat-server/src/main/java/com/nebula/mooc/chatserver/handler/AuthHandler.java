/*
 * @author Zhanghh
 * @date 2019/4/25
 */
package com.nebula.mooc.chatserver.handler;

import com.nebula.mooc.core.service.UserService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户信息认证
 */
@ChannelHandler.Sharable
public class AuthHandler extends SimpleChannelInboundHandler<Object> {

    @Autowired
    private UserService userService;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) {
        System.out.println(msg);
        System.out.println();
    }

}
