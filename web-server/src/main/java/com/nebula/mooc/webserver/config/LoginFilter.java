/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.webserver.config;

import com.nebula.mooc.core.entity.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class LoginFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    private String ssoServer;

    @Override
    public void init(FilterConfig filterConfig) {
        ssoServer = filterConfig.getInitParameter(Constant.SSO_SERVER);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        // valid login user, cookie + redirect
//        LoginUser loginUser = UserController.loginCheck(req, res);
//
//        // valid login fail
//        if (xxlUser == null) {
//
//            String header = req.getHeader("content-type");
//            boolean isJson = header != null && header.contains("json");
//            if (isJson) {
//
//                // json msg
//                res.setContentType("application/json;charset=utf-8");
//                res.getWriter().println("{\"code\":" + Constant.SSO_LOGIN_FAIL_RESULT.getCode() + ", \"msg\":\"" + Constant.SSO_LOGIN_FAIL_RESULT.getMsg() + "\"}");
//                return;
//            } else {
//
//                // redirect logout
//                String loginPageUrl = ssoServer.concat(Constant.SSO_LOGIN);
//
//                res.sendRedirect(loginPageUrl);
//                return;
//            }
//
//        }
//
//        // ser sso user
//        request.setAttribute(Constant.SSO_USER, xxlUser);


        // already login, allow
        chain.doFilter(request, response);
    }

}
