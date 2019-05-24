/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.ssoserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.*;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new RedisSerializer<Object>() {

            private final byte[] EMPTY_BYTE = new byte[0];

            @Override
            public byte[] serialize(Object object) {
                try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                     ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                    oos.writeObject(object);
                    return baos.toByteArray();
                } catch (Exception e) {
                    return EMPTY_BYTE;
                }
            }

            @Override
            public Object deserialize(byte[] bytes) {
                try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                     ObjectInputStream ois = new ObjectInputStream(bais)) {
                    return ois.readObject();
                } catch (Exception e) {
                    return null;
                }
            }
        });
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

}
