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

    /**
     * 静态变量防止多次初始化
     */
    private static SslContext sslContext;

    /**
     * 证书位置
     */
    @Value("${ssl.certpath}")
    private String certPath;

    /**
     * 私钥位置
     */
    @Value("${ssl.keypath}")
    private String keyPath;


    @Override
    protected void initChannel(SocketChannel channel) {
        if (sslContext == null) {
            try {
                // 通过classpath访问证书文件
                File certFile = new File(getClass().getClassLoader().getResource(certPath).getFile());
                File keyFile = new File(getClass().getClassLoader().getResource(keyPath).getFile());
                // 构建sslContext
                sslContext = SslContextBuilder.forServer(certFile, keyFile).build();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                System.exit(-1);
            }
        }
        SSLEngine engine = sslContext.newEngine(channel.alloc());
        engine.setUseClientMode(false);
        channel.pipeline().addFirst(new SslHandler(engine));
    }

}