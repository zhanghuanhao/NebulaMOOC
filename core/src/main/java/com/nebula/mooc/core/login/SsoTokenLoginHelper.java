package com.nebula.mooc.core.login;

import com.nebula.mooc.core.entity.Constant;
import com.nebula.mooc.core.entity.User;
import com.nebula.mooc.core.store.SsoLoginStore;
import com.nebula.mooc.core.store.SsoSessionIdHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xuxueli 2018-11-15 15:54:40
 */
public class SsoTokenLoginHelper {

    /**
     * client login
     *
     * @param sessionId
     * @param xxlUser
     */
    public static void login(String sessionId, User xxlUser) {

        String storeKey = SsoSessionIdHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            throw new RuntimeException("parseStoreKey Fail, sessionId:" + sessionId);
        }

        SsoLoginStore.put(storeKey, xxlUser);
    }

    /**
     * client logout
     *
     * @param sessionId
     */
    public static void logout(String sessionId) {

        String storeKey = SsoSessionIdHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            return;
        }

        SsoLoginStore.remove(storeKey);
    }
    /**
     * client logout
     *
     * @param request
     */
    public static void logout(HttpServletRequest request) {
        String headerSessionId = request.getHeader(Constant.SESSION_ID);
        logout(headerSessionId);
    }


    /**
     * login check
     *
     * @param sessionId
     * @return
     */
    public static User loginCheck(String sessionId) {

        String storeKey = SsoSessionIdHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            return null;
        }

        User xxlUser = SsoLoginStore.get(storeKey);
        if (xxlUser != null) {
            String version = SsoSessionIdHelper.parseVersion(sessionId);
            if (xxlUser.getVersion().equals(version)) {

                // After the expiration time has passed half, Auto refresh
                if ((System.currentTimeMillis() - xxlUser.getExpireFreshTime()) > xxlUser.getExpireMinite() / 2) {
                    xxlUser.setExpireFreshTime(System.currentTimeMillis());
                    SsoLoginStore.put(storeKey, xxlUser);
                }

                return xxlUser;
            }
        }
        return null;
    }


    /**
     * login check
     *
     * @param request
     * @return
     */
    public static User loginCheck(HttpServletRequest request) {
        String headerSessionId = request.getHeader(Constant.SESSION_ID);
        return loginCheck(headerSessionId);
    }


}
