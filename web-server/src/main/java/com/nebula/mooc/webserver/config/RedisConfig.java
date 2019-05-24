/*
 * @author Zhanghh
 * @date 2019/5/23
 */
package com.nebula.mooc.webserver.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.lang.reflect.Method;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 自定义生成key的规则
     */
    @Override
    public KeyGenerator keyGenerator() {
        return (Object o, Method method, Object... objects) -> {
            //遍历参数并且追加
            System.out.println("调用Redis缓存Key : " + method.getName());
            return o.getClass().getName();
        };
    }

    /**
     * 采用RedisCacheManager作为缓存管理器
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.create(connectionFactory);
    }

}
