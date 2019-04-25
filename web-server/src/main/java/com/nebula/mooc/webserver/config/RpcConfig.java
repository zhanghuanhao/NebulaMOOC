/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.webserver.config;

import com.nebula.mooc.webserver.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class RpcConfig {

    @Value("${sso.server}")
    private String ssoServerAddress;

    @Bean
    public HessianProxyFactoryBean UserService() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setServiceUrl(ssoServerAddress + "/UserService");
        factory.setServiceInterface(UserService.class);
        return factory;
    }

}
