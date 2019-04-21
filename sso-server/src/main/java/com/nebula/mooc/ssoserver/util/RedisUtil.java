/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.ssoserver.util;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * 操作Redis工具类
 */
public class RedisUtil {

    /**
     * Redis Expire Minute
     */
    private static int expireSeconds;

    private static ShardedJedisPool shardedJedisPool;

    // ------------------------ serialize and unserialize ------------------------

    /**
     * 将对象-->byte[] (由于jedis中不支持直接存储object所以转换成byte[]存入)
     */
    private static byte[] serialize(Object object) {
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
    private static Object unserialize(byte[] bytes) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return ois.readObject();
        } catch (Exception e) {
            return null;
        }
    }


    // ---------------------------------------- Basic Function -----------------------------------

    private static void initShardedJedisPool(String redisAddress) {
        // JedisPoolConfig
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(200);
        config.setMaxIdle(50);
        config.setMinIdle(8);
        config.setMaxWaitMillis(10000);         // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        config.setTestOnBorrow(true);           // 在获取连接的时候检查有效性, 默认false
        config.setTestOnReturn(false);          // 调用returnObject方法时，是否进行有效检查
        config.setTestWhileIdle(true);          // Idle时进行连接扫描
        config.setTimeBetweenEvictionRunsMillis(30000);     // 表示idle object evitor两次扫描之间要sleep的毫秒数
        config.setNumTestsPerEvictionRun(10);               // 表示idle object evitor每次扫描的最多的对象数
        config.setMinEvictableIdleTimeMillis(30000);        // 表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义

        // JedisShardInfo List
        List<JedisShardInfo> jedisShardInfos = new LinkedList<JedisShardInfo>();

        String[] addressArr = redisAddress.split(",");
        for (int i = 0; i < addressArr.length; i++) {
            JedisShardInfo jedisShardInfo = new JedisShardInfo(addressArr[i]);
            jedisShardInfos.add(jedisShardInfo);
        }
        shardedJedisPool = new ShardedJedisPool(config, jedisShardInfos);
    }

    /**
     * 获取ShardedJedis实例
     */
    private static ShardedJedis getInstance() {
        return shardedJedisPool.getResource();
    }

    /**
     * @param redisAddress like "{ip}"、"{ip}:{port}"、"{redis/rediss}://xxl-sso:{password}@{ip}:{port:6379}/{db}"；Multiple "," separated
     */
    public static void init(String redisAddress, int redisExpireMinute) {
        expireSeconds = redisExpireMinute * 60;
        initShardedJedisPool(redisAddress);
    }

    public static void close() {
        if (shardedJedisPool != null) {
            shardedJedisPool.close();
        }
    }

    // ------------------------ Jedis Util ------------------------

    /**
     * Set String
     */
    public static boolean setString(String key, String value) {
        return setString(key, value, expireSeconds);
    }

    /**
     * Set Object
     */
    public static boolean setObject(String key, Object obj) {
        return setObject(key, obj, expireSeconds);
    }

    /**
     * Set String
     */
    public static boolean setString(String key, String value, int seconds) {
        try (ShardedJedis client = getInstance()) {
            return "OK".equals(client.setex(key, seconds, value));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Set Object
     */
    public static boolean setObject(String key, Object obj, int seconds) {
        try (ShardedJedis client = getInstance()) {
            return "OK".equals(client.setex(key.getBytes(), seconds, serialize(obj)));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get String
     */
    public static String getString(String key) {
        try (ShardedJedis client = getInstance()) {
            return client.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get Object
     */
    public static Object getObject(String key) {
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
    public static boolean del(String key) {
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
    public static boolean exists(String key) {
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
    public static boolean expire(String key) {
        try (ShardedJedis client = getInstance()) {
            return client.expire(key, expireSeconds) > 0;
        } catch (Exception e) {
            return false;
        }
    }

}
