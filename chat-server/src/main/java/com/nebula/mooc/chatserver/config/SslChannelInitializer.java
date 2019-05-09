/*
 * @author Zhanghh
 * @date 2019-05-05
 */
package com.nebula.mooc.chatserver.config;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLEngine;

@Component
@ChannelHandler.Sharable
public class SslChannelInitializer extends ChannelInitializer<SocketChannel> {

    private SslContext sslContext;

    /**
     * 证书位置
     */
    @Value("classpath:${ssl.certpath}")
    private Resource certPath;

    /**
     * 私钥位置
     */
    @Value("classpath:${ssl.keypath}")
    private Resource keyPath;

    @PostConstruct
    public void setSslContext() throws Exception {
        // 构建sslContext
        sslContext = SslContextBuilder
                //使用File
                .forServer(certPath.getInputStream(), keyPath.getInputStream())
                .build();
    }

    @Override
    protected void initChannel(SocketChannel channel) {
        SSLEngine engine = sslContext.newEngine(channel.alloc());
        engine.setUseClientMode(false);
        channel.pipeline().addFirst(new SslHandler(engine));
    }

}