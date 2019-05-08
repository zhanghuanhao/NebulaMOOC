/*
 * @author Zhanghh
 * @date 2019-05-05
 */
package com.nebula.mooc.chatserver.config;

import com.nebula.mooc.core.Constant;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLEngine;
import java.io.File;

@Component
@ChannelHandler.Sharable
public class SslChannelInitializer extends ChannelInitializer<SocketChannel> {

    private SslContext sslContext;

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

    @PostConstruct
    public void sslContext() throws Exception {
        // 通过classpath访问证书文件
        File certFile = new File(Constant.CLASSPATH + certPath);
        File keyFile = new File(Constant.CLASSPATH + keyPath);
        // 构建sslContext
        sslContext = SslContextBuilder.forServer(certFile, keyFile).build();
    }

    @Override
    protected void initChannel(SocketChannel channel) {
        SSLEngine engine = sslContext.newEngine(channel.alloc());
        engine.setUseClientMode(false);
        channel.pipeline().addFirst(new SslHandler(engine));
    }

}