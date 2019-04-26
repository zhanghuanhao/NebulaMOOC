/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.chatserver.config;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.nebula.mooc.core.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RpcConfig {

    @Value("${sso.server}")
    private String ssoServerAddress;

    /**
     * 连接RPC服务 - UserService
     */
    @Bean
    public UserService importUserService() {
        ConsumerConfig<UserService> consumerConfig = new ConsumerConfig<UserService>()
                .setInterfaceId(UserService.class.getName()) // 指定接口
                .setProtocol("bolt") // 指定协议
                .setDirectUrl(ssoServerAddress); // 指定直连地址
        return consumerConfig.refer();
    }

}
