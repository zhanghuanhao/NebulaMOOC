/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.ssoserver.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
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


@Component
public class RedisUtil {
    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    /**
     * Redis Expire Minute
     */
    private static int expireMinute;

    /**
     * 方式01: Redis单节点 + Jedis单例 : Redis单节点压力过重, Jedis单例存在并发瓶颈 》》不可用于线上
     * new Jedis("127.0.0.1", 6379).get("cache_key");
     * 方式02: Redis单节点 + JedisPool单节点连接池 》》 Redis单节点压力过重，负载和容灾比较差
     * new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379, 10000).getResource().get("cache_key");
     * 方式03: Redis分片(通过client端集群,一致性哈希方式实现) + Jedis多节点连接池 》》Redis集群,负载和容灾较好, ShardedJedisPool一致性哈希分片,读写均匀，动态扩充
     * new ShardedJedisPool(new JedisPoolConfig(), new LinkedList<JedisShardInfo>());
     * 方式03: Redis集群；
     * new JedisCluster(jedisClusterNodes);    // TODO
     */
    private static ShardedJedisPool shardedJedisPool;

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
        logger.info("ShardedJedisPool init success.");
    }

    /**
     * @param redisAddress like "{ip}"、"{ip}:{port}"、"{redis/rediss}://xxl-sso:{password}@{ip}:{port:6379}/{db}"；Multiple "," separated
     */
    public static void init(String redisAddress, int redisExpireMinute) {
        expireMinute = redisExpireMinute;
        initShardedJedisPool(redisAddress);
    }

    /**
     * 获取ShardedJedis实例
     */
    private static ShardedJedis getInstance() {
        return shardedJedisPool.getResource();
    }

    public static void close() {
        if (shardedJedisPool != null) {
            shardedJedisPool.close();
        }
    }


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
            logger.error(e.getMessage(), e);
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
            logger.error(e.getMessage(), e);
            return null;
        }

    }

    // ------------------------ Jedis Util ------------------------

    /**
     * Set String
     */
    public static String setString(String key, String value, int seconds) {
        try (ShardedJedis client = getInstance()) {
            return client.setex(key, seconds, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Set Object
     */
    public static String setObject(String key, Object obj, int seconds) {
        try (ShardedJedis client = getInstance()) {
            return client.setex(key.getBytes(), seconds, serialize(obj));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Get String
     */
    public static String getString(String key) {
        try (ShardedJedis client = getInstance()) {
            return client.get(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
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
            logger.error(e.getMessage(), e);
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
    public static Long del(String key) {
        try (ShardedJedis client = getInstance()) {
            return client.del(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * incrBy i(+i)
     *
     * @param key
     * @param i
     * @return new value after incr
     */
    public static Long incrBy(String key, int i) {
        try (ShardedJedis client = getInstance()) {
            return client.incrBy(key, i);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * exists valid
     *
     * @param key
     * @return Boolean reply, true if the key exists, otherwise false
     */
    public static Boolean exists(String key) {
        try (ShardedJedis client = getInstance()) {
            return client.exists(key);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }


}
