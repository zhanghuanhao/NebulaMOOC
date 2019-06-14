/*
 * @author Zhanghh
 * @date 2019/6/4
 */
package com.nebula.mooc.liveserver.service;

import com.nebula.mooc.core.entity.Live;

public interface LiveService {

    /**
     * 创建新的直播
     */
    String newLive(long userId, Live live);

    /**
     * 获取直播列表
     */
    Object[] getLiveList();

    /**
     * 获取直播信息
     */
    Live getLive(long userId);

    /**
     * 获取我的直播Token
     */
    String getLiveToken(long userId);

    /**
     * 检查房间token
     */
    boolean checkToken(long userId, String liveToken);

}
