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
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
public class XssInterceptor implements HandlerInterceptor {

    /**
     * 配置可以通过过滤的白名单
     * none: 过滤所有的HTML标签
     * preserveRelativeLinks: 保留相对路径
     */
    private static final Whitelist whitelist = Whitelist.none().preserveRelativeLinks(true);

    private String clean(String content) {
        return Jsoup.clean(content, whitelist);
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        if ((line = br.readLine()) != null) {
            sb.append(line);
        }
        line = sb.toString();
        if (!line.equals(clean(line))) {
            response.sendError(403);
            return false;
        }
        return true;
    }

}
