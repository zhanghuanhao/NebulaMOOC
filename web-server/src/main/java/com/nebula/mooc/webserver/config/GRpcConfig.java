/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.webserver.config;

import com.nebula.mooc.core.service.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class GRpcConfig {

    private static final Logger logger = LoggerFactory.getLogger(GRpcConfig.class);

    @Value("${sso.host}")
    private String ssoHost;

    @Value("${sso.port}")
    private int ssoPort;

    private ManagedChannel channel;

    @Bean
    public UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub() {
        channel = ManagedChannelBuilder.forAddress(ssoHost, ssoPort).usePlaintext().build();
        UserServiceGrpc.UserServiceBlockingStub blockingStub = UserServiceGrpc.newBlockingStub(channel);
        logger.info("UserService - RPC inited.");
        return blockingStub;
    }

    @PreDestroy
    public void shutdown() {
        channel.shutdownNow();
        logger.info("UserService - RPC closed.");
    }

}
