/*
 * @author Zhanghh
 * @date 2019/4/12
 */
package com.nebula.mooc.webserver.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.UserInfo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

public class CacheUtil {

    private static final Cache<String, UserInfo> userCache = CacheBuilder.newBuilder()
            .maximumSize(128)  //最大Cache大小
            .expireAfterAccess(1, TimeUnit.HOURS)   // 一小时后删除
            .build();

    /**
     * 获取对应Cookie
     *
     * @param request 请求
     * @param key     键
     * @return 返回Cookie
     */
    private static Cookie getCookie(HttpServletRequest request, String key) {
        Cookie[] arr_cookie = request.getCookies();
        if (arr_cookie != null) {
            for (Cookie cookie : arr_cookie) {
                if (cookie.getName().equals(key)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 设置Cookie
     *
     * @param response 回应
     * @param key      键
     * @param value    值
     */
    private static void setCookie(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setMaxAge(-1);  // 表示仅当前浏览器周期内有效，退出浏览器后删除
        cookie.setSecure(true);     //如果为true，仅支持HTTPS协议
        cookie.setPath("/");     //cookie对指定目录中的所有页面以及该目录子目录中的所有页面都可见
        cookie.setHttpOnly(true);   // http only，js无法获取
        response.addCookie(cookie);
    }

    /**
     * 根据key从Cookie中删除
     */
    private static void removeCookie(HttpServletRequest request, HttpServletResponse response, String key) {
        Cookie cookie = getCookie(request, key);
        if (cookie != null) {
            setCookie(response, key, "");
        }
    }

    /**
     * 从Cookie中获取Token值
     */
    public static String getToken(HttpServletRequest request) {
        Cookie cookie = getCookie(request, Constant.TOKEN);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    /**
     * 从Session中获取UserInfo值
     */
    public static UserInfo getUserInfo(HttpServletRequest request) {
        return (UserInfo) request.getSession().getAttribute(Constant.USERINFO);
    }

    /**
     * 设置用户信息
     */
    public static void set(HttpSession session, HttpServletResponse response, String token, UserInfo userInfo) {
        CacheUtil.setCookie(response, Constant.TOKEN, token);
        session.setAttribute(Constant.TOKEN, token);
        session.setAttribute(Constant.USERINFO, userInfo);
        userCache.put(Constant.TOKEN, userInfo);
    }

    /**
     * 删除用户信息
     */
    public static void remove(HttpServletRequest request, HttpServletResponse response) {
        CacheUtil.removeCookie(request, response, Constant.TOKEN);
        request.getSession().setAttribute(Constant.TOKEN, "");
        request.getSession().setAttribute(Constant.USERINFO, "");
        userCache.invalidate(Constant.TOKEN);
    }

    /**
     * 从本地userCache获取UserInfo信息
     */
    public static UserInfo getIfPresent(String token) {
        return userCache.getIfPresent(token);
    }

}