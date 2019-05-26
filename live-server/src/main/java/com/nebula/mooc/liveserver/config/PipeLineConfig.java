/*
 * @author Zhanghh
 * @date 2019/4/23
 */
package com.nebula.mooc.liveserver.config;

import com.nebula.mooc.liveserver.core.ChatMessage;
import com.nebula.mooc.liveserver.handler.ByteToFrameHandler;
import com.nebula.mooc.liveserver.handler.ChatHandler;
import com.nebula.mooc.liveserver.handler.FrameToByteHandler;
import com.nebula.mooc.liveserver.handler.HandshakeHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PipeLineConfig extends SslChannelInitializer {

    private static final int maxContentLength = 65536;

    @Autowired
    private ProtobufDecoder protobufDecoder;

    @Autowired
    private ProtobufEncoder protobufEncoder;

    @Autowired
    private HandshakeHandler handshakeHandler;

    @Autowired
    private FrameToByteHandler frameToByteHandler;

    @Autowired
    private ByteToFrameHandler byteToFrameHandler;

    @Autowired
    private ChatHandler chatHandler;

    @Bean
    public ProtobufDecoder protobufDecoder() {
        return new ProtobufDecoder(ChatMessage.request.getDefaultInstance());
    }

    @Bean
    public ProtobufEncoder protobufEncoder() {
        return new ProtobufEncoder();
    }

    @Override
    protected void initChannel(SocketChannel channel) {
        super.initChannel(channel);
        channel.pipeline()
                // HTTP请求的解码和编码
                .addLast(new HttpServerCodec())
                // 把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse，
                .addLast(new HttpObjectAggregator(maxContentLength))
                //用于处理接入的连接
                .addLast(handshakeHandler)
                .addLast(frameToByteHandler)
                .addLast(protobufDecoder)
                .addLast(byteToFrameHandler)
                .addLast(protobufEncoder)
                .addLast(chatHandler);
    }
}
