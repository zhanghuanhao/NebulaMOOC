/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.ssoserver.config;

import com.nebula.mooc.core.util.RedisUtil;
import com.nebula.mooc.ssoserver.service.UserService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration
public class SsoConfig implements InitializingBean, DisposableBean {

    @Value("${redis.address}")
    private String redisAddress;

    @Value("${redis.expire-minute}")
    private int redisExpireMinute;

    @Autowired
    private UserService userService;

    @Override
    public void afterPropertiesSet() {
        RedisUtil.init(redisAddress, redisExpireMinute);
    }

    @Override
    public void destroy() {
        RedisUtil.close();
    }

    /*
     * 开放RPC服务
     */
    @Bean(name = "/UserService")
    public HessianServiceExporter userService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(userService);
        exporter.setServiceInterface(UserService.class);
        return exporter;
    }

}
