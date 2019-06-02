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
     * key: 用户token
     * value: 申请的房间信息
     */
    private static final Cache<String, Live> lives = CacheBuilder.newBuilder()
            .expireAfterWrite(3, TimeUnit.HOURS)
            .build();

    /**
     * 生成房间
     *
     * @param token 用户token
     * @param live  房间信息
     * @return 房间token
     */
    public static String newLive(String token, Live live) {
        String liveToken = TokenUtil.generateToken();
        live.setLiveToken(liveToken);
        lives.put(token, live);
        return liveToken;
    }

    /**
     * 检查用户申请的房间Token是否正确
     *
     * @param userToken 用户token
     * @param liveToken 房间token
     */
    public static boolean checkToken(String userToken, String liveToken) {
        Live live = lives.getIfPresent(userToken);
        return live != null && liveToken.equals(live.getLiveToken());
    }

    /**
     * 返回用户的房间信息
     *
     * @param userToken 用户token
     */
    public static Live getLive(String userToken) {
        return lives.getIfPresent(userToken);
    }

    public static Object[] getList() {
        return lives.asMap().values().toArray();
    }

}