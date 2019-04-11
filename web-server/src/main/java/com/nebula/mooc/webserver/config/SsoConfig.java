/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.webserver.config;

import com.nebula.mooc.core.entity.Constant;
import com.nebula.mooc.core.filter.XxlSsoWebFilter;
import com.nebula.mooc.core.util.JedisUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SsoConfig implements DisposableBean {


    @Value("${sso.server}")
    private String SsoServer;

    @Value("${sso.logout-path}")
    private String SsoLogoutPath;

    @Value("${sso.redis-address}")
    private String SsoRedisAddress;


    @Bean
    public FilterRegistrationBean xxlSsoFilterRegistration() {

        // xxl-sso, redis init
        JedisUtil.init(SsoRedisAddress);

        // xxl-sso, filter init
        FilterRegistrationBean<XxlSsoWebFilter> registration = new FilterRegistrationBean<>();

        registration.setName("XxlSsoWebFilter");
        registration.setOrder(1);
        registration.addUrlPatterns("/*");

        registration.setFilter(new XxlSsoWebFilter());
        registration.addInitParameter(Constant.SSO_SERVER, SsoServer);
        registration.addInitParameter(Constant.SSO_LOGOUT_PATH, SsoLogoutPath);

        return registration;
    }

    @Override
    public void destroy() throws Exception {
        // Close redis
        JedisUtil.close();
    }

}
