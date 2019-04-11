/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.ssoserver.config;

import com.nebula.mooc.core.store.SsoLoginStore;
import com.nebula.mooc.core.util.JedisUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SsoConfig implements InitializingBean, DisposableBean {

    @Value("${xxl.sso.redis.address}")
    private String redisAddress;

    @Value("${xxl.sso.redis.expire.minite}")
    private int redisExpireMinite;

    @Override
    public void afterPropertiesSet() throws Exception {
        SsoLoginStore.setRedisExpireMinite(redisExpireMinite);
        JedisUtil.init(redisAddress);
    }

    @Override
    public void destroy() throws Exception {
        JedisUtil.close();
    }

}
