/*
 * @author Zhanghh
 * @date 2019/4/8
 */
package com.nebula.mooc.webserver.service;

import com.nebula.mooc.core.entity.LoginUser;

public interface UserService {

    /**
     * 检查是否已登录
     *
     * @param sessionId 获取唯一标识
     * @return true: 已登录 false: 未登录
     */
    boolean loginCheck(String sessionId);

    /**
     * 登陆
     *
     * @param sessionId 获取唯一标识
     * @param loginUser 登陆的用户
     * @return true: 登陆成功
     */
    boolean login(String sessionId, LoginUser loginUser);

    /**
     * 注销
     *
     * @param sessionId 获取唯一标识
     */
    void logout(String sessionId);
}
