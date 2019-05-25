/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.liveserver.service.impl;

import com.nebula.mooc.core.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Service("RedisService")
public class RedisService {

    /**
     * 默认过期时长，单位：秒
     */
    private static final long DEFAULT_EXPIRE_MINUTES = 60;

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    /**
     * 获取缓存
     */
    public UserInfo getUserInfo(String key) {
        return (UserInfo) redisTemplate.opsForValue().get(key);
    }

    /**
     * 将UserInfo放入缓存
     */
    public boolean setUserInfo(String key, UserInfo value) {
        redisTemplate.opsForValue().set(key, value, DEFAULT_EXPIRE_MINUTES, TimeUnit.MINUTES);
        return true;
    }


    /**
     * 指定缓存失效时间，默认为2小时
     */
    public boolean expireUserInfo(String key) {
        // 1. 检查是否存在此key值，如果token未过期，延长有效期
        if (redisTemplate.hasKey(key))
            return redisTemplate.expire(key, DEFAULT_EXPIRE_MINUTES, TimeUnit.MINUTES);
        return false;
    }

    /**
     * 删除缓存
     */
    public boolean del(String key) {
        redisTemplate.delete(key);
        return true;
    }

}
