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

    int SUCCESS_CODE = 100;

    int SERVER_ERROR_CODE = 200;

    int CLIENT_ERROR_CODE = 300;

    int CLIENT_NOT_LOGIN = 301;

    int CLIENT_TOKEN_EXCEED = 302;

    String LOGIN_PATH = "/login.html";

}
