/*
 * @author Zhanghh
 * @date 2019/4/23
 */
package com.nebula.mooc.chatserver.config;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PipeLineInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private HttpServerCodec httpServerCodec;

    @Autowired
    private HttpObjectAggregator httpObjectAggregator;

    @Autowired
    private ChunkedWriteHandler chunkedWriteHandler;

    @Autowired
    private HandshakeHandler handshakeHandler;

    @Autowired
    private FrameToByteHandler frameToByteHandler;

    @Autowired
    private ProtobufDecoder protobufDecoder;

    @Autowired
    private ByteToFrameHandler byteToFrameHandler;

    @Autowired
    private ProtobufEncoder protobufEncoder;

    @Autowired
    private ChatHandler chatHandler;

    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline()
                // HTTP请求的解码和编码
                .addLast(httpServerCodec)
                // 把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse，
                .addLast(httpObjectAggregator)
                .addLast(chunkedWriteHandler)
                //用于处理接入的连接
                .addLast(handshakeHandler)
                .addLast(frameToByteHandler)
                .addLast(protobufDecoder)
                .addLast(byteToFrameHandler)
                .addLast(protobufEncoder)
                .addLast(chatHandler);
    }
}
