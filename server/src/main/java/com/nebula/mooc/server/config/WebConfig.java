package com.nebula.mooc.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;

@Configuration
public class WebConfig extends WebFluxConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler中的是访问路径，可以修改为其他的字符串
        //addResourceLocations中的是实际路径
        registry.addResourceHandler("/*").addResourceLocations("classpath:/WEB/");
        super.addResourceHandlers(registry);
    }

}
