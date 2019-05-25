/*
 * @author Zhanghh
 * @date 2019/4/23
 */
package com.nebula.mooc.liveserver.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class Runner {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    @Value("${websocket.port}")
    private int port;

    @Autowired
    private ServerBootstrap b;

    private EventLoopGroup bossGroup;     //acceptor
    private EventLoopGroup workerGroup;   //IO

    @PostConstruct
    public void run() {
        bossGroup = new NioEventLoopGroup();     //acceptor
        workerGroup = new NioEventLoopGroup();   //IO
        try {
            // 添加线程组
            b.group(bossGroup, workerGroup);
            // 同步等待创建完成
            ChannelFuture ch = b.bind(port).sync();
            if (ch.isSuccess())
                logger.info("Chat-Server start, listening on {}", port);
            else
                throw new Exception(ch.cause());
            // 主线程阻塞（wait），子线程进行接收连接和IO处理
            ch.channel().closeFuture();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @PreDestroy
    public void shutdown() throws InterruptedException {
        bossGroup.shutdownGracefully().sync();
        workerGroup.shutdownGracefully().sync();
        logger.info("Chat-Server stop.");
    }
}
