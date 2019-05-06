/*
 * @author Zhanghh
 * @date 2019-05-04
 */
package com.nebula.mooc.chatserver.config;

import com.nebula.mooc.chatserver.core.ChatMessage;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 添加Handler Bean
 */
@Configuration
public class HandlerHouse {

    private static final int maxContentLength = 65536;

    @Bean
    @Scope("prototype")
    public HttpServerCodec httpServerCodec() {
        return new HttpServerCodec();
    }

    @Bean
    @Scope("prototype")
    public HttpObjectAggregator httpObjectAggregator() {
        return new HttpObjectAggregator(maxContentLength);
    }

    @Bean
    @Scope("prototype")
    public ChunkedWriteHandler chunkedWriteHandler() {
        return new ChunkedWriteHandler();
    }

    @Bean
    public ProtobufDecoder protobufDecoder() {
        return new ProtobufDecoder(ChatMessage.request.getDefaultInstance());
    }

    @Bean
    public ProtobufEncoder protobufEncoder() {
        return new ProtobufEncoder();
    }

}
