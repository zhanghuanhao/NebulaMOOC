/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.webserver.config;

import com.nebula.mooc.webserver.service.UserService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * 检查是否已登录
 */
//@Configuration
public class LoginConfig extends WebMvcConfigurationSupport implements HandlerInterceptor {

    @Resource
    private UserService userService;

    /**
     * 配置静态资源放行
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//    }

    /**
     * 只过滤HTML文件
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(this).addPathPatterns("/*")
//                .excludePathPatterns("/css/*", "/js/*", "/plugins/*" ,"/res/*", "/sys/*", "/error");
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        System.out.println(request.getServletPath());
//        if (!Constant.LOGIN_PATH.equals(request.getServletPath())) {
//            String sessionId = CookieUtil.get(request, Constant.SESSION_ID);
//            boolean result = sessionId != null && userService.loginCheck(sessionId);
//            if (!result) {
//                response.sendRedirect(Constant.LOGIN_PATH);
//                return false;
//            }
//        }
//        return true;
//    }

}
