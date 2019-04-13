/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.ssoserver.config;

import com.nebula.mooc.core.util.RedisUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SsoConfig implements InitializingBean, DisposableBean {

    @Value("${redis.address}")
    private String redisAddress;

    @Value("${redis.expire-minute}")
    private int redisExpireMinute;

    @Override
    public void afterPropertiesSet() {
        RedisUtil.init(redisAddress, redisExpireMinute);
    }

    @Override
    public void destroy() {
        RedisUtil.close();
    }

}
