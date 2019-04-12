/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.core.entity;

public class Constant {


    public static final String SESSION_ID = "SESSIONID";


    /**
     * redirect url (web client)
     */
    public static final String REDIRECT_URL = "redirect_url";

    /**
     * sso user, request attribute (web client)
     */
    public static final String SSO_USER = "xxl_sso_user";


    /**
     * sso server address (web + token client)
     */
    public static final String SSO_SERVER = "sso_server";

    /**
     * login url, server relative path (web client)
     */
    public static final String SSO_LOGIN = "/login";
    /**
     * logout url, server relative path (web client)
     */
    public static final String SSO_LOGOUT = "/logout";

    /**
     * logout path, client relatice path
     */
    public static final String SSO_LOGOUT_PATH = "SSO_LOGOUT_PATH";

    /**
     * login fail result
     */
    public static final Return<String> SSO_LOGIN_FAIL_RESULT = new Return(501, "sso not login.");

}
