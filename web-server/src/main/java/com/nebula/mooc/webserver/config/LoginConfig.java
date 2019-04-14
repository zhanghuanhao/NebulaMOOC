/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.webserver.config;

import com.nebula.mooc.core.entity.Constant;
import com.nebula.mooc.core.util.CookieUtil;
import com.nebula.mooc.webserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查是否已登录
 */
@Configuration
public class LoginConfig extends WebMvcConfigurationSupport implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    /**
     * 配置静态资源路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this).addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/js/**", "/res/**",
                        "/plugins/**", "/sys/**", "/error/**");
    }

    /**
     * 绑定默认欢迎页
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("index"); //添加index.html为view -> index
        registry.addViewController("/").setViewName("index");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        System.out.println(request.getServletPath());
        if (!Constant.LOGIN_PATH.equals(request.getServletPath())) {
            String token = CookieUtil.get(request, Constant.SESSION_ID);
            boolean result = token != null && userService.loginCheck(token);
            if (!result) {
                response.sendRedirect(Constant.LOGIN_PATH);
                return false;
            }
        }
        return true;
    }

}
