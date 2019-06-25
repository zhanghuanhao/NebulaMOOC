/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.core;

import java.util.HashMap;
import java.util.Map;

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
    String USERINFO = "USERINFO";

    int SUCCESS_CODE = 100;

    int STAR_LIKE_ALREADY = 105;

    int UN_STAR_LIKE = 106;

    int SERVER_ERROR_CODE = 200;

    int CLIENT_ERROR_CODE = 300;

    int CLIENT_ILLEGAL = 302;

    int CLIENT_FILE_ERROR = 303;

    int CLIENT_REGISTERED = 304;

    int CLIENT_PARAM_ERROR = 305;

    /**
     * 浏览过分数
     */
    int VIEW_SCORE = 3;

    /**
     * 点赞分数
     */
    int STAR_SCORE = 4;

    /**
     * 收藏分数
     */
    int LIKE_SCORE = 5;

    String IMG_CHECK_CODE = "IMG_CHECK_CODE";

    String EMAIL_CHECK_CODE = "EMAIL_CHECK_CODE";

    /**
     * 默认分页大小
     */
    int PAGE_SIZE = 10;

    /**
     * 默认主页大小
     */
    int HOME_PAGE_SIZE = 4;

    Map<Integer, String> KIND_MAP = new HashMap<>(11);

}
