/*
 * @author Zhanghh
 * @date 2019/4/12
 */
package com.nebula.mooc.liveserver.core;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;


public class LiveManager {
    Cache<Long, String> anchor = CacheBuilder.newBuilder()
            .expireAfterWrite(3, TimeUnit.HOURS)
            .build();

    public void add(long userId, String liveToken) {
        anchor.put(userId, liveToken);
    }

    public boolean check(long userId, String liveToken) {
        String token = anchor.getIfPresent(userId);
        return token != null && token.equals(liveToken);
    }

}