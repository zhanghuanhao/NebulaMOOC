/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.webserver.config;

import com.nebula.mooc.core.entity.Constant;
import com.nebula.mooc.core.util.CookieUtil;
import com.nebula.mooc.webserver.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查是否已登录
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer, HandlerInterceptor {

    @Resource
    private UserService userService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this).addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/js/**", "/plugins/**", "/loginSpecial/**");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        System.out.println(request.getServletPath());
        if (!Constant.LOGIN_PATH.equals(request.getServletPath())) {
            String sessionId = CookieUtil.get(request, Constant.SESSION_ID);
            boolean result = sessionId != null && userService.loginCheck(sessionId);
            if (!result) {
                response.sendRedirect(Constant.LOGIN_PATH);
                return false;
            }
        }
        return true;
    }

}
