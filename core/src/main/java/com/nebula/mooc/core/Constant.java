/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.core;

/**
 * 存放常量
 */
public interface Constant {

    /**
     * 存放在浏览器Cookie中的关键字
     */
    String TOKEN = "TOKEN";

    /**
     * 存放在浏览器UserInfo中的关键字
     */
    String USERINFO = "userInfo";

    int SUCCESS_CODE = 100;

    int SERVER_ERROR_CODE = 200;

    int CLIENT_ERROR_CODE = 300;

    int CLIENT_NOT_LOGIN = 301;

    int CLIENT_TOKEN_EXCEED = 302;

    int CLIENT_FILE_ERROR = 303;

    String IMG_CHECK_CODE = "IMG_CHECK_CODE";

    String EMAIL_CHECK_CODE = "EMAIL_CHECK_CODE";

    /**
     * 登录路径
     */
    String LOGIN_PATH = "/login.html";

    /**
     * 头像存放路径
     */
    String HEAD_PATH = "/static/res/head/";
}
