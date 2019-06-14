/*
 * @author Zhanghh
 * @date 2019/4/12
 */
package com.nebula.mooc.liveserver.core;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.nebula.mooc.core.entity.Live;
import com.nebula.mooc.core.util.TokenUtil;

import java.util.concurrent.TimeUnit;


public class LiveManager {

    /**
     * 获取某个用户对应的房间token
     * key: 用户ID
     * value: 房间token
     */
    private static final Cache<Long, String> lives = CacheBuilder.newBuilder()
            .expireAfterWrite(3, TimeUnit.HOURS)
            .build();

    /**
     * 获取某个房间信息
     * key: 用户ID作为房间号
     * value: 申请的房间信息
     */
    private static final Cache<Long, Live> liveInfos = CacheBuilder.newBuilder()
            .expireAfterWrite(3, TimeUnit.HOURS)
            .build();

    /**
     * 生成房间
     *
     * @param userId 用户ID
     * @param live   房间信息
     * @return 房间token
     */
    public static String newLive(Long userId, Live live) {
        String liveToken = TokenUtil.generateToken();
        lives.put(userId, liveToken);
        liveInfos.put(userId, live);
        return liveToken;
    }

    /**
     * 检查用户申请的房间Token是否正确
     *
     * @param userId    用户ID
     * @param liveToken 房间token
     */
    public static boolean checkToken(long userId, String liveToken) {
        return liveToken.equals(lives.getIfPresent(userId));
    }

    /**
     * 返回用户的房间信息
     *
     * @param userId 用户ID
     */
    public static Live getLive(long userId) {
        return liveInfos.getIfPresent(userId);
    }

    /**
     * 返回用户的房间token
     *
     * @param userId 用户ID
     */
    public static String getLiveToken(long userId) {
        return lives.getIfPresent(userId);
    }

    /**
     * 返回直播列表
     */
    public static Object[] getList() {
        return liveInfos.asMap().values().toArray();
    }

}