/*
 * @author Zhanghh
 * @date 2019/5/23
 */
package com.nebula.mooc.webserver.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nebula.mooc.core.Constant;
import com.nebula.mooc.core.entity.UserInfo;
import com.nebula.mooc.webserver.util.CacheUtil;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    /**
     * 基于KIND_MAP映射的key生成器
     * 第一个参数必须为int型，代表kind种类
     */
    @Bean("kindMapKeyGenerator")
    public KeyGenerator kindMapKeyGenerator() {
        return (Object o, Method method, Object... objects) -> {
            String kindName = null;
            if (objects.length > 0 && objects[0] instanceof Integer)
                kindName = Constant.KIND_MAP.get(objects[0]);
            if (kindName != null) return kindName;   //返回类型名
            // 格式不正确或不存在返回此方法名
            return method.getName();
        };
    }

    /**
     * 基于userId的key生成器
     * 第一个参数必须为request型，用于获取userId
     */
    @Bean("userIdKeyGenerator")
    public KeyGenerator userIdKeyGenerator() {
        return (Object o, Method method, Object... objects) -> {
            if (objects.length > 0 && objects[0] instanceof HttpServletRequest) {
                UserInfo userInfo = CacheUtil.getUserInfo((HttpServletRequest) objects[0]);
                if (userInfo != null) return userInfo.getId();
                else return 0;
            }
            // 格式不正确或不存在返回此方法名
            return method.getName();
        };
    }

    /**
     * 采用RedisCacheManager作为缓存管理器
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                this.getRedisCacheConfigurationWithTtl(10), // 默认策略，未配置的 key 会使用这个
                this.getRedisCacheConfigurationMap() // 指定 key 策略
        );
    }

    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        // 自定义cacheNames的过期时长
        redisCacheConfigurationMap.put("HOT", this.getRedisCacheConfigurationWithTtl(30));
        redisCacheConfigurationMap.put("showRecommendPostList", this.getRedisCacheConfigurationWithTtl(180));
        redisCacheConfigurationMap.put("getRecommendCourseList", this.getRedisCacheConfigurationWithTtl(180));
        redisCacheConfigurationMap.put("HOME", this.getRedisCacheConfigurationWithTtl(360));
        return redisCacheConfigurationMap;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer minutes) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(jackson2JsonRedisSerializer)
        ).entryTtl(Duration.ofMinutes(minutes));
        return redisCacheConfiguration;
    }

}
