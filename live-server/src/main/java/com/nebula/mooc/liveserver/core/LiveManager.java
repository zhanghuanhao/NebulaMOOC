/*
 * @author Zhanghh
 * @date 2019/4/12
 */
package com.nebula.mooc.liveserver.core;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.nebula.mooc.core.entity.Live;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.core.util.TokenUtil;

import java.util.concurrent.TimeUnit;


public class LiveManager {

    /**
     * key: 用户Token
     * value: 用户信息
     */
    private static final Cache<String, UserInfo> users = CacheBuilder.newBuilder()
            .expireAfterAccess(3, TimeUnit.HOURS)
            .build();

    /**
     * key: 用户ID
     * value: 申请的房间信息
     */
    private static final Cache<Long, Live> lives = CacheBuilder.newBuilder()
            .expireAfterWrite(3, TimeUnit.HOURS)
            .build();

    /**
     * 生成房间
     *
     * @param userId 用户ID
     * @param live  房间信息
     * @return 房间token
     */
    public static String newLive(Long userId, Live live) {
        String liveToken = TokenUtil.generateToken();
        live.setLiveToken(liveToken);
        lives.put(userId, live);
        return liveToken;
    }

    /**
     * 检查用户申请的房间Token是否正确
     *
     * @param userId 用户ID
     * @param liveToken 房间token
     */
    public static boolean checkToken(long userId, String liveToken) {
        Live live = lives.getIfPresent(userId);
        return live != null && liveToken.equals(live.getLiveToken());
    }

    /**
     * 返回用户的房间信息
     *
     * @param userId 用户ID
     */
    public static Live getLive(long userId) {
        return lives.getIfPresent(userId);
    }

    /**
     * 返回直播列表
     */
    public static Object[] getList() {
        return lives.asMap().values().toArray();
    }

    public static void putUserInfo(String token, UserInfo userInfo) {
        users.put(token, userInfo);
    }

    public static UserInfo getUserInfo(String token) {
        return users.getIfPresent(token);
    }

}