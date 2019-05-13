/*
 * @author Zhanghh
 * @date 2019/4/25
 */
package com.nebula.mooc.ssoserver.config;

import com.nebula.mooc.ssoserver.service.UserService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class GRpcConfig {

    private static final Logger logger = LoggerFactory.getLogger(GRpcConfig.class);

    @Value("${sso.port}")
    private int ssoPort;

    @Autowired
    private UserService userService;

    private Server server;

    @PostConstruct
    public void init() {
        server = ServerBuilder.forPort(ssoPort)
                .addService(userService)
                .build();
    }

    @PreDestroy
    private void shutdown() {
        server.shutdown();
        logger.info("UserService stop.");
    }

    /**
     * 开启 GRpc - UserService
     */
    @Bean
    public ApplicationRunner run() {
        return args -> {
            try {
                server.start();
                logger.info("UserService started, listening on {}", ssoPort);
            } catch (Exception e) {
                logger.error("UserService error.", e);
            }
        };
    }

}
