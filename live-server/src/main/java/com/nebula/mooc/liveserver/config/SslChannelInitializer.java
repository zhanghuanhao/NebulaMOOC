/*
 * @author Zhanghh
 * @date 2019-05-05
 */
package com.nebula.mooc.liveserver.config;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLEngine;

@Component
@ChannelHandler.Sharable
public class SslChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private SslContext sslContext;

    @Override
    protected void initChannel(SocketChannel channel) {
        SSLEngine engine = sslContext.newEngine(channel.alloc());
        engine.setUseClientMode(false);
        channel.pipeline().addFirst(new SslHandler(engine));
    }

}