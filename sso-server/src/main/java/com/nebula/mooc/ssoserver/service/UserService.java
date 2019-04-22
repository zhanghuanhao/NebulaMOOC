/*
 * @author Zhanghh
 * @date 2019/4/8
 */
package com.nebula.mooc.ssoserver.service;

import com.nebula.mooc.core.entity.LoginUser;
import com.nebula.mooc.core.entity.UserInfo;

public interface UserService {

    /**
     * 检查是否已登录
     *
     * @param token 获取唯一标识
     * @return true: 已登录 false: 未登录
     */
    UserInfo loginCheck(String token);

    /**
     * 登陆
     *
     * @param loginUser 登陆的用户
     * @return token 获取唯一标识
     */
    String login(LoginUser loginUser);

    /**
     * 注销
     *
     * @param token 获取唯一标识
     */
    void logout(String token);

    /**
     * 注册
     *
     * @param loginUser 用户信息
     */
    boolean register(LoginUser loginUser);

    /**
     * 重置密码
     *
     * @param loginUser 用户信息
     */
    boolean resetPassword(LoginUser loginUser);
}
