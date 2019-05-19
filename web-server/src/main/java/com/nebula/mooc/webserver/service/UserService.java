/*
 * @author Zhanghh
 * @date 2019/5/13
 */
package com.nebula.mooc.webserver.service;

import com.nebula.mooc.core.entity.LoginUser;
import com.nebula.mooc.core.entity.UserInfo;

public interface UserService {

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
    boolean logout(String token);

    /**
     * 注册
     *
     * @param loginUser 用户信息
     * @return 304 ->已注册 300 -> 失败 100 -> 成功
     */
    int register(LoginUser loginUser);

    /**
     * 重置密码
     *
     * @param loginUser 用户信息
     */
    boolean resetPassword(LoginUser loginUser);

    /**
     * 检查是否已登录
     *
     * @param token 获取唯一标识
     * @return true: 已登录 false: 未登录
     */
    boolean loginCheck(String token);

    /**
     * 检查用户是否存在
     *
     * @param email 用户邮箱
     */
    boolean checkUserExist(String email);

    /**
     * 获得用户信息UserInfo
     *
     * @param token 获取唯一标识
     * @return 用户信息
     */
    UserInfo getUserInfo(String token);

    /**
     * 修改用户个人信息，返回Constant中状态码
     *
     * @param user 用户信息
     */
    boolean updateUser(UserInfo user);

}