/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.webserver.config;

import com.nebula.mooc.webserver.interceptor.LoginInterceptor;
import com.nebula.mooc.webserver.interceptor.XssInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 检查是否已登录
 */
@Configuration
public class BasicConfig extends WebMvcConfigurationSupport {

    private static final Logger logger = LoggerFactory.getLogger(BasicConfig.class);

    @Autowired
    private XssInterceptor xssInterceptor;

    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 配置静态资源路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        logger.info("Add resource handler: /** -> /static/**");
    }

    /**
     * 添加html为可以被解析为视图
     */
    @Bean
    public InternalResourceViewResolver htmlViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".html");
        logger.info("Add view resolver: html");
        return resolver;
    }

    /**
     * 绑定默认欢迎页
     * 添加url: / -> view: index
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        logger.info("Add welcome page: index.html");
    }


    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/api/**");
        logger.info("Add login interceptor: /api/**");
        registry.addInterceptor(xssInterceptor).addPathPatterns("/api/**");
        logger.info("Add xss interceptor: /**");
    }

}
