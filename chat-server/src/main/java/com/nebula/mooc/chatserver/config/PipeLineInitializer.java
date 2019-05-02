/*
 * @author Zhanghh
 * @date 2019/4/23
 */
package com.nebula.mooc.chatserver.config;

import com.nebula.mooc.chatserver.core.ChatMessage;
import com.nebula.mooc.chatserver.handler.ByteToFrameHandler;
import com.nebula.mooc.chatserver.handler.ChatHandler;
import com.nebula.mooc.chatserver.handler.FrameToByteHandler;
import com.nebula.mooc.chatserver.handler.HandshakeHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class PipeLineInitializer extends ChannelInitializer<SocketChannel> {

    private static final int maxContentLength = 65536;

    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline()
                // HTTP请求的解码和编码
                .addLast(new HttpServerCodec())
                // 把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse，
                .addLast(new HttpObjectAggregator(maxContentLength))
                .addLast(new ChunkedWriteHandler())
                //用于处理接入的连接
                .addLast(new HandshakeHandler())
                .addLast(new FrameToByteHandler())
                .addLast(new ProtobufDecoder(ChatMessage.request.getDefaultInstance()))
                .addLast(new ByteToFrameHandler())
                .addLast(new ProtobufEncoder())
                .addLast(new ChatHandler());
    }
}
