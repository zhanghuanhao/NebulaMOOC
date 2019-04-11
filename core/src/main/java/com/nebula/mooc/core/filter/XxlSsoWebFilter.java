/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.core.filter;

import com.nebula.mooc.core.entity.Constant;
import com.nebula.mooc.core.entity.User;
import com.nebula.mooc.core.login.SsoWebLoginHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class XxlSsoWebFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(XxlSsoWebFilter.class);
    private String ssoServer;
    private String logoutPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        ssoServer = filterConfig.getInitParameter(Constant.SSO_SERVER);
        logoutPath = filterConfig.getInitParameter(Constant.SSO_LOGOUT_PATH);

        logger.info("XxlSsoWebFilter init.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // make url
        String servletPath = req.getServletPath();

        // logout path check
        if (logoutPath != null
                && logoutPath.trim().length() > 0
                && logoutPath.equals(servletPath)) {

            // remove cookie
            SsoWebLoginHelper.removeSessionIdByCookie(req, res);

            // redirect logout
            String logoutPageUrl = ssoServer.concat(Constant.SSO_LOGOUT);
            res.sendRedirect(logoutPageUrl);

            return;
        }

        // valid login user, cookie + redirect
        User xxlUser = SsoWebLoginHelper.loginCheck(req, res);

        // valid login fail
        if (xxlUser == null) {

            String header = req.getHeader("content-type");
            boolean isJson = header != null && header.contains("json");
            if (isJson) {

                // json msg
                res.setContentType("application/json;charset=utf-8");
                res.getWriter().println("{\"code\":" + Constant.SSO_LOGIN_FAIL_RESULT.getCode() + ", \"msg\":\"" + Constant.SSO_LOGIN_FAIL_RESULT.getMsg() + "\"}");
                return;
            } else {

                // total link
                String link = req.getRequestURL().toString();

                // redirect logout
                String loginPageUrl = ssoServer.concat(Constant.SSO_LOGIN)
                        + "?" + Constant.REDIRECT_URL + "=" + link;

                res.sendRedirect(loginPageUrl);
                return;
            }

        }

        // ser sso user
        request.setAttribute(Constant.SSO_USER, xxlUser);


        // already login, allow
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
