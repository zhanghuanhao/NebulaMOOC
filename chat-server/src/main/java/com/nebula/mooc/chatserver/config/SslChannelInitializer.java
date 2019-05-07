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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLEngine;
import java.io.File;


@Component
@ChannelHandler.Sharable
public class SslChannelInitializer extends ChannelInitializer<SocketChannel> {

    private static final Logger logger = LoggerFactory.getLogger(SslChannelInitializer.class);

    private static SslContext sslContext;

    @Value("${ssl.certpath}")
    private String certPath;

    @Value("${ssl.keypath}")
    private String keyPath;

    @Value("${ssl.password}")
    private String password;

    @Override
    protected void initChannel(SocketChannel channel) {
        if (sslContext == null) {
            try {
                File certFile = new File(getClass().getClassLoader().getResource(certPath).getFile());
                File keyFile = new File(getClass().getClassLoader().getResource(keyPath).getFile());
                sslContext = SslContextBuilder.forServer(certFile, keyFile, password).build();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return;
            }
        }
        SSLEngine engine = sslContext.newEngine(channel.alloc());
        engine.setUseClientMode(false);
        channel.pipeline().addFirst(new SslHandler(engine));
    }

}