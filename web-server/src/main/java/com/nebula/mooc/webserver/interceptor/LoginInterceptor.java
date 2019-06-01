/*
 * @author Zhanghh
 * @date 2019/5/31
 */
package com.nebula.mooc.webserver.interceptor;

import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.webserver.service.UserService;
import com.nebula.mooc.webserver.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = CacheUtil.getToken(request);
        // 双重检查机制
        if (token != null) {
            // 1. 判断Cookie中的token是否与Session中的token相同
            String tokenInSession = (String) request.getSession().getAttribute(Constant.TOKEN);
            if (token.equals(tokenInSession)) {
                //说明这个Session尚未失效
                return true;
            }
            // 2. 若不相同或不存在，则在SSO检查Cookie中的Token是否过期
            if (userService.loginCheck(token)) {
                UserInfo userInfo = userService.getUserInfo(token);
                request.getSession().setAttribute(Constant.TOKEN, token);
                request.getSession().setAttribute(Constant.USERINFO, userInfo);
                return true;
            }
        }
        // 发送401 Unauthorized
        response.sendError(401);
        return false;
    }

}
