/*
 * @author Zhanghh
 * @date 2019/4/25
 */
package com.nebula.mooc.ssoserver.config;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.nebula.mooc.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RpcConfig {

    private static final Logger logger = LoggerFactory.getLogger(RpcConfig.class);

    @Autowired
    private UserService userService;

    /*
     * 开放RPC服务 - UserService
     */
    @Bean
    public ApplicationRunner exportUserService() {
        return args -> {
            // 指定服务端协议和地址
            ServerConfig serverConfig = new ServerConfig()
                    .setProtocol("bolt") // 设置一个协议，默认bolt
                    .setPort(13888) // 设置一个端口，默认12200
                    .setDaemon(false); // 非守护线程
            // 设置提供者
            ProviderConfig<UserService> providerConfig = new ProviderConfig<UserService>()
                    .setInterfaceId(UserService.class.getName()) // 指定接口
                    .setRef(userService) // 指定实现
                    .setServer(serverConfig); // 指定服务端
            providerConfig.export(); // 发布服务
            logger.info("Open UserService - RPC successfully.");
        };
    }


}
