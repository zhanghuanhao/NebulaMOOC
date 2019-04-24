/*
 * @author Zhanghh
 * @date 2019/4/23
 */
package com.nebula.mooc.chatserver.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    @Autowired
    private ServerBootstrap b;

    public void run(String... args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();     //acceptor
        EventLoopGroup workerGroup = new NioEventLoopGroup();   //IO
        try {
            // 添加线程组
            b.group(bossGroup, workerGroup);
            // 同步等待创建完成
            ChannelFuture ch = b.bind().sync();
            if (ch.isSuccess())
                logger.info("Chat-Server successfully started.");
            else
                throw new Exception(ch.cause());
            // 主线程阻塞（wait），子线程进行接收连接和IO处理
            ch.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        logger.info("Chat-Server exited.");
    }
}
