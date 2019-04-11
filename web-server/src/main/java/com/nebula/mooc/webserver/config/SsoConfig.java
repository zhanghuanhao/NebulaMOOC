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

/**
 * @author xuxueli 2018-11-15
 */
@Configuration
public class SsoConfig implements DisposableBean {


    @Value("${sso.server}")
    private String xxlSsoServer;

    @Value("${sso.logout-path}")
    private String xxlSsoLogoutPath;

    @Value("${sso.redis-address}")
    private String xxlSsoRedisAddress;


    @Bean
    public FilterRegistrationBean xxlSsoFilterRegistration() {

        // xxl-sso, redis init
        JedisUtil.init(xxlSsoRedisAddress);

        // xxl-sso, filter init
        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setName("XxlSsoWebFilter");
        registration.setOrder(1);
        registration.addUrlPatterns("/*");
        registration.setFilter(new XxlSsoWebFilter());
        registration.addInitParameter(Constant.SSO_SERVER, xxlSsoServer);
        registration.addInitParameter(Constant.SSO_LOGOUT_PATH, xxlSsoLogoutPath);

        return registration;
    }

    @Override
    public void destroy() throws Exception {
        // Close redis
        JedisUtil.close();
    }

}
