/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.ssoserver.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.PreDestroy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 操作Redis工具类
 */
@Component
public class RedisUtil {

    @Value("#{${redis.expireMinutes} * 60}")
    private int expireSeconds;

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    /**
     * 获取ShardedJedis实例
     */
    private ShardedJedis getInstance() {
        return shardedJedisPool.getResource();
    }

    /**
     * 将对象-->byte[] (由于jedis中不支持直接存储object所以转换成byte[]存入)
     */
    private byte[] serialize(Object object) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将byte[] -->Object
     */
    private Object unserialize(byte[] bytes) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Set String
     */
    public boolean setString(String key, String value) {
        return setString(key, value, expireSeconds);
    }

    /**
     * Set Object
     */
    public boolean setObject(String key, Object obj) {
        return setObject(key, obj, expireSeconds);
    }

    /**
     * Set String
     */
    public boolean setString(String key, String value, int seconds) {
        try (ShardedJedis client = getInstance()) {
            return "OK".equals(client.setex(key, seconds, value));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Set Object
     */
    public boolean setObject(String key, Object obj, int seconds) {
        try (ShardedJedis client = getInstance()) {
            return "OK".equals(client.setex(key.getBytes(), seconds, serialize(obj)));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get String
     */
    public String getString(String key) {
        try (ShardedJedis client = getInstance()) {
            return client.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get Object
     */
    public Object getObject(String key) {
        try (ShardedJedis client = getInstance()) {
            byte[] bytes = client.get(key.getBytes());
            return unserialize(bytes);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Delete key
     *
     * @param key
     * @return Integer reply, specifically:
     * an integer greater than 0 if one or more keys were removed
     * 0 if none of the specified key existed
     */
    public boolean del(String key) {
        try (ShardedJedis client = getInstance()) {
            return client.del(key) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * exists valid
     *
     * @param key
     * @return Boolean reply, true if the key exists, otherwise false
     */
    public boolean exists(String key) {
        try (ShardedJedis client = getInstance()) {
            return client.exists(key);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * expire reset
     *
     * @param key
     * @return Integer reply, specifically:
     * 1: the timeout was set.
     * 0: the timeout was not set since the key already has an associated timeout (versions lt 2.1.3), or the key does not exist.
     */
    public boolean expire(String key) {
        try (ShardedJedis client = getInstance()) {
            return client.expire(key, expireSeconds) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @PreDestroy
    public void close() {
        shardedJedisPool.close();
    }

}
