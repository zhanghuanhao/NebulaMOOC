/*
 * @author Zhanghh
 * @date 2019/5/31
 */
package com.nebula.mooc.webserver.interceptor;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Configuration
public class XssInterceptor implements HandlerInterceptor {

    /**
     * 配置可以通过过滤的白名单
     * none: 过滤所有的HTML标签
     * preserveRelativeLinks: 保留相对路径
     */
    private static final Whitelist whitelist = Whitelist.none().preserveRelativeLinks(true);

    private static String clean(String content) {
        return Jsoup.clean(content, whitelist);
    }

    private boolean checkParameters(HttpServletRequest request) {
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String[] values = request.getParameterValues(name);
            for (String value : values) {
                if (!value.equals(clean(value)))
                    return false;
            }
        }
        return true;
    }

    private boolean checkHeaders(HttpServletRequest request) {
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            Enumeration<String> values = request.getHeaders(name);
            while (values.hasMoreElements()) {
                String value = values.nextElement();
                if (!value.equals(clean(value)))
                    return false;
            }
        }
        return true;
    }

    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        if (checkParameters(req) && checkHeaders(req))
            return true;
        res.sendError(403);
        return false;
    }

}
