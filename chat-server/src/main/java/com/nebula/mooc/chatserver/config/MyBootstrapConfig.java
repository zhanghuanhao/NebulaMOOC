package com.nebula.mooc.chatserver.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBootstrapConfig {

    /**
     * 端口号
     */
    @Value("${websocket.port}")
    private int port;

    /**
     * 最大连接数
     */
    @Value("${websocket.backlog}")
    private int backlog;

    @Bean
    public ServerBootstrap getBootstrap() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.localAddress(port)
                .channel(NioServerSocketChannel.class)
                //服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数
                .option(ChannelOption.SO_BACKLOG, backlog)
                //设置TCP长连接,一般如果两个小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //将小的数据包包装成更大的帧进行传送，提高网络的负载
                .childOption(ChannelOption.TCP_NODELAY, true)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new MyChannelInitializer<SocketChannel>());
        return bootstrap;
    }

}
