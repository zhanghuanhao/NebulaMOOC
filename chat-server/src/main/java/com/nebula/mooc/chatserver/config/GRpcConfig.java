/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.chatserver.config;

import com.nebula.mooc.core.service.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class GRpcConfig {

    @Value("${sso.host}")
    private String ssoHost;

    @Value("${sso.port}")
    private int ssoPort;

    private ManagedChannel channel;

    @Bean
    public UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub() {
        channel = ManagedChannelBuilder.forAddress(ssoHost, ssoPort).usePlaintext().build();
        return UserServiceGrpc.newBlockingStub(channel);
    }

    @PreDestroy
    public void shutdown() {
        channel.shutdown();
    }

}
