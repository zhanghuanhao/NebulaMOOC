/*
 * @author Zhanghh
 * @date 2019/4/24
 */
package com.nebula.mooc.chatserver.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ChannelHandler.Sharable
public class ByteToFrameHandler extends MessageToMessageEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> objs) {
        BinaryWebSocketFrame frame = new BinaryWebSocketFrame(buf);
        objs.add(frame);
        frame.retain();
    }
}
