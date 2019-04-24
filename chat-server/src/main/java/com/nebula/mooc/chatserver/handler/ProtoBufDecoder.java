/*
 * @author Zhanghh
 * @date 2019/4/24
 */
package com.nebula.mooc.chatserver.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;

@ChannelHandler.Sharable
public class ProtoBufDecoder extends MessageToMessageDecoder<WebSocketFrame> {
    @Override
    protected void decode(ChannelHandlerContext ctx, WebSocketFrame frame, List<Object> objs) {
        ByteBuf buf = frame.content();
        objs.add(buf);
        buf.retain();
    }
}
