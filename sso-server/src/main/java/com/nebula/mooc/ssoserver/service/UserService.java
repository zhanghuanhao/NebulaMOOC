/*
 * @author Zhanghh
 * @date 2019/4/8
 */
package com.nebula.mooc.ssoserver.service;

import com.nebula.mooc.core.entity.User;

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
     * @param user 登陆的用户
     * @return true: 登陆成功
     */
    boolean login(String token, User user);

    /**
     * 注销
     *
     * @param token 获取唯一标识
     */
    void logout(String token);

    /**
     * 注册
     *
     * @param user 用户信息
     */
    boolean register(User user);

    /**
     * 重置密码
     *
     * @param user 用户信息
     */
    boolean resetPassword(User user);
}
