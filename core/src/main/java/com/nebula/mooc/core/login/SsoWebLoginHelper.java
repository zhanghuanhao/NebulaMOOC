package com.nebula.mooc.core.login;

import com.nebula.mooc.core.entity.Constant;
import com.nebula.mooc.core.entity.User;
import com.nebula.mooc.core.store.SsoLoginStore;
import com.nebula.mooc.core.store.SsoSessionIdHelper;
import com.nebula.mooc.core.util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xuxueli 2018-04-03
 */
public class SsoWebLoginHelper {

    /**
     * client login
     *
     * @param response
     * @param sessionId
     * @param ifRemember true: cookie not expire, false: expire when browser close （server cookie）
     * @param xxlUser
     */
    public static void login(HttpServletResponse response,
                             String sessionId,
                             User xxlUser,
                             boolean ifRemember) {

        String storeKey = SsoSessionIdHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            throw new RuntimeException("parseStoreKey Fail, sessionId:" + sessionId);
        }

        SsoLoginStore.put(storeKey, xxlUser);
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
     * client logout, cookie only
     *
     * @param request
     * @param response
     */
    public static void removeSessionIdByCookie(HttpServletRequest request, HttpServletResponse response) {
        CookieUtil.remove(request, response, Constant.SESSION_ID);
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
