/*
 * @author Zhanghh
 * @date 2019/4/8
 */
package com.nebula.mooc.ssoserver.service.impl;

import com.nebula.mooc.core.entity.Constant;
import com.nebula.mooc.core.entity.User;
import com.nebula.mooc.ssoserver.dao.UserDao;
import com.nebula.mooc.ssoserver.login.SsoWebLoginHelper;
import com.nebula.mooc.ssoserver.service.UserService;
import com.nebula.mooc.ssoserver.util.CookieUtil;
import com.nebula.mooc.ssoserver.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public static void login(HttpServletRequest request, HttpServletResponse response) {
        String sessionId;
        // 1. 检查是否浏览器Cookie中是否有sessionId
        sessionId = CookieUtil.getValue(request, Constant.SESSION_ID);
        if (sessionId != null) {
            //2. 若存在，检查登录时间是否过期
            RedisUtil.
        }


        //不存在sessionId
        CookieUtil.set(response, Constant.SESSION_ID, sessionId, ifRemember);
    }

    /**
     * client logout
     *
     * @param request
     * @param response
     */
    public static void logout(HttpServletRequest request,
                              HttpServletResponse response) {

        String cookieSessionId = CookieUtil.getValue(request, Constant.SESSION_ID);
        if (cookieSessionId == null) {
            return;
        }

        String storeKey = SsoSessionIdHelper.parseStoreKey(cookieSessionId);
        if (storeKey != null) {
            SsoLoginStore.remove(storeKey);
        }

        CookieUtil.remove(request, response, Constant.SESSION_ID);
    }


    /**
     * login check
     *
     * @param request
     * @param response
     * @return
     */
    public static User loginCheck(HttpServletRequest request, HttpServletResponse response) {

        String cookieSessionId = CookieUtil.getValue(request, Constant.SESSION_ID);

        // cookie user
        User xxlUser = SsoTokenLoginHelper.loginCheck(cookieSessionId);
        if (xxlUser != null) {
            return xxlUser;
        }

        // redirect user

        // remove old cookie
        SsoWebLoginHelper.removeSessionIdByCookie(request, response);

        // set new cookie
        String paramSessionId = request.getParameter(Constant.SESSION_ID);
        xxlUser = SsoTokenLoginHelper.loginCheck(paramSessionId);
        if (xxlUser != null) {
            CookieUtil.set(response, Constant.SESSION_ID, paramSessionId, false);    // expire when browser close （client cookie）
            return xxlUser;
        }

        return null;
    }

    /**
     * get sessionid by cookie
     *
     * @param request
     * @return
     */
    public static String getSessionIdByCookie(HttpServletRequest request) {
        String cookieSessionId = CookieUtil.getValue(request, Constant.SESSION_ID);
        return cookieSessionId;
    }

}
