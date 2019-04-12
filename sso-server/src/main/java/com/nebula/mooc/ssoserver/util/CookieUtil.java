/*
 * @author Zhanghh
 * @date 2019/4/12
 */
package com.nebula.mooc.ssoserver.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtil {

    // Cookie最大存活时间
    private static final int COOKIE_MAX_AGE = Integer.MAX_VALUE;
    // 表示仅当前浏览器内有效
    private static final int COOKIE_MIN_AGE = -1;
    // 保存路径,根路径
    private static final String COOKIE_PATH = "/";

    /**
     * 获取Cookie
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

    public static void set(HttpServletResponse response, String key, String value, boolean ifRemember) {
        int maxAge = ifRemember ? COOKIE_MAX_AGE : COOKIE_MIN_AGE;
        Cookie cookie = new Cookie(key, value);
        cookie.setPath(COOKIE_PATH);
        cookie.setMaxAge(maxAge);
        //设置是否保存在http，即js无法获取
        cookie.setHttpOnly(true);
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
            set(response, key, "", false);
        }
    }
}