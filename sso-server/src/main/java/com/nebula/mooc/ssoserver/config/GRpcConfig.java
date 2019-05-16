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

    @PreDestroy
    private void shutdown() {
        server.shutdown();
        logger.info("UserService - RPC stop.");
    }

    /**
     * 开启 RPC - UserService
     */
    @PostConstruct
    public void run() {
        try {
            server = ServerBuilder.forPort(ssoPort)
                    .addService(userService)
                    .build()
                    .start();
            logger.info("UserService - RPC start, listening on {}", ssoPort);
        } catch (Exception e) {
            logger.error("UserService - RPC error.", e);
        }
    }

}
