/*
 * @author Zhanghh
 * @date 2019/4/12
 */
package com.nebula.mooc.webserver.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

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
    public static void set(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setMaxAge(-1);  // 表示仅当前浏览器周期内有效
        cookie.setHttpOnly(true);   //设置是否保存在http，即js无法获取
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

    public static String get(HttpServletRequest request, String key) {
        Cookie cookie = getCookie(request, key);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    public static void remove(HttpServletRequest request, HttpServletResponse response, String key) {
        Cookie cookie = getCookie(request, key);
        if (cookie != null) {
            set(response, key, "");
        }
    }
}