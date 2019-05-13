/*
 * @author Zhanghh
 * @date 2019/5/13
 */
package com.nebula.mooc.chatserver.core.service;

import com.nebula.mooc.core.entity.UserInfo;

public interface UserService {

    /**
     * 检查是否已登录
     *
     * @param token 获取唯一标识
     * @return true: 已登录 false: 未登录
     */
    boolean loginCheck(String token);

    /**
     * 获得用户信息UserInfo
     *
     * @param token 获取唯一标识
     * @return 用户信息
     */
    UserInfo getUserInfo(String token);

}