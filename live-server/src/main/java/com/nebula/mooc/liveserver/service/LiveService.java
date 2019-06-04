/*
 * @author Zhanghh
 * @date 2019/6/4
 */
package com.nebula.mooc.liveserver.service;

import com.nebula.mooc.core.entity.Live;
import com.nebula.mooc.core.entity.UserInfo;

public interface LiveService {

    /**
     * 创建新的直播
     */
    String newLive(UserInfo userInfo, Live live);

    /**
     * 获取直播列表
     */
    Object[] getLiveList();

    /**
     * 获取我的直播信息
     */
    Live getMyLive(long userId);

    /**
     * 将UserInfo放入Cache中
     */
    void putUserInfo(String token, UserInfo userInfo);

    /**
     * 从Cache中获取用户信息
     */
    UserInfo getUserInfo(String token);

    /**
     * 检查token信息
     */
    boolean checkToken(String userToken, String liveToken);

}
