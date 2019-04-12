/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.webserver.config;

import com.nebula.mooc.core.entity.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SsoConfig {


    @Value("${sso.server}")
    private String SsoServer;

    @Value("${sso.logout-path}")
    private String SsoLogoutPath;

    @Bean
    public FilterRegistrationBean xxlSsoFilterRegistration() {
        // Filter init
        FilterRegistrationBean<SsoWebFilter> registration = new FilterRegistrationBean<>();
        registration.setName("SsoWebFilter");
        registration.setOrder(1);
        registration.addUrlPatterns("/*");
        registration.setFilter(new SsoWebFilter());
        registration.addInitParameter(Constant.SSO_SERVER, SsoServer);
        registration.addInitParameter(Constant.SSO_LOGOUT_PATH, SsoLogoutPath);

        return registration;
    }

}
