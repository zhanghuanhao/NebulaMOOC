/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.webserver.config;

import com.nebula.mooc.core.entity.Constant;
import com.nebula.mooc.webserver.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class RPCConfig {

    @Value("${sso.server}")
    private String ssoServerAddress;

    @Bean
    public FilterRegistrationBean xxlSsoFilterRegistration() {
        // Filter init
        FilterRegistrationBean<LoginFilter> registration = new FilterRegistrationBean<>();
        registration.setName("LoginFilter");
        registration.setOrder(1);
        registration.addUrlPatterns("/*");
        registration.setFilter(new LoginFilter());
        registration.addInitParameter(Constant.SSO_SERVER, ssoServerAddress);

        return registration;
    }

    @Bean
    public HessianProxyFactoryBean UserService() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setServiceUrl(ssoServerAddress + "/UserService");
        factory.setServiceInterface(UserService.class);
        return factory;
    }

}
