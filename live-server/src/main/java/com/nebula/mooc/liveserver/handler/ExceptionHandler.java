/*
 * @author Zhanghh
 * @date 2019-06-25
 */
package com.nebula.mooc.liveserver.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class ExceptionHandler extends ChannelDuplexHandler {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 忽略解析错误，即SSL错误
        if (cause instanceof DecoderException) return;
        ctx.fireExceptionCaught(cause);
    }

}
