/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.ssoserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class RedisConfig {

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${redis.addresses}")
    private String redisAddresses;

    @Value("${redis.maxTotal}")
    private int maxTotal;

    @Value("${redis.maxIdle}")
    private int maxIdle;

    @Value("${redis.minIdle}")
    private int minIdle;

    @Value("${redis.maxWaitMillis}")
    private int maxWaitMillis;

    @Value("${redis.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${redis.numTestsPerEvictionRun}")
    private int numTestsPerEvictionRun;

    @Value("${redis.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Bean
    public ShardedJedisPool shardedJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        config.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        List<JedisShardInfo> jedisShardInfos = new LinkedList<>();
        String[] addresses = redisAddresses.split(",");
        for (String address : addresses) {
            JedisShardInfo jedisShardInfo = new JedisShardInfo(address.trim());
            jedisShardInfos.add(jedisShardInfo);
        }
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(config, jedisShardInfos);
        logger.info("RedisPool init success.");
        return shardedJedisPool;
    }

}
