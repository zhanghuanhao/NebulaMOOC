/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.ssoserver.config;

import com.nebula.mooc.ssoserver.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig implements InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${redis.address}")
    private String redisAddress;

    @Value("${redis.expire-minute}")
    private int redisExpireMinute;

    @Override
    public void afterPropertiesSet() {
        RedisUtil.init(redisAddress, redisExpireMinute);
        logger.info("RedisPool init.");
    }

    @Override
    public void destroy() {
        RedisUtil.close();
        logger.info("RedisPool close.");
    }

}
