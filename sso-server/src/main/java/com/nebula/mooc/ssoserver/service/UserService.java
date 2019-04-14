/*
 * @author Zhanghh
 * @date 2019/4/8
 */
package com.nebula.mooc.ssoserver.service;

import com.nebula.mooc.core.entity.LoginUser;

public interface UserService {

    /**
     * 检查是否已登录
     *
     * @param token 获取唯一标识
     * @return true: 已登录 false: 未登录
     */
    boolean loginCheck(String token);

    /**
     * 登陆
     *
     * @param token 获取唯一标识
     * @param loginUser 登陆的用户
     * @return true: 登陆成功
     */
    boolean login(String token, LoginUser loginUser);

    /**
     * 注销
     *
     * @param token 获取唯一标识
     */
    void logout(String token);
}
