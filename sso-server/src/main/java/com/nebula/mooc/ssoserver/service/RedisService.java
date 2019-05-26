/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.ssoserver.service;

import com.nebula.mooc.core.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService {

    /**
     * 默认过期时长，单位：小时
     */
    private static final long DEFAULT_EXPIRE_HOURS = 2;

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
        redisTemplate.opsForValue().set(key, value, DEFAULT_EXPIRE_HOURS, TimeUnit.HOURS);
        return true;
    }


    /**
     *检查是否存在此key值
     */
    public boolean hasUserInfo(String key) {
        // 检查是否存在此key值，如果存在则将过期时间延长
        Boolean ret = redisTemplate.hasKey(key);
        if (ret != null && ret) {
            redisTemplate.expire(key, DEFAULT_EXPIRE_HOURS, TimeUnit.HOURS);
            return true;
        }
        return false;
    }

    /**
     * 删除缓存
     */
    public boolean removeUserInfo(String key) {
        redisTemplate.delete(key);
        return true;
    }

}
