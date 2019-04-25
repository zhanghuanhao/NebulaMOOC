/*
 * @author Zhanghh
 * @date 2019/4/25
 */
package com.nebula.mooc.ssoserver.config;

import com.nebula.mooc.ssoserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration
public class RpcConfig {

    private static final Logger logger = LoggerFactory.getLogger(RpcConfig.class);

    @Autowired
    private UserService userService;

    /*
     * 开放RPC服务
     */
    @Bean(name = "/UserService")
    public HessianServiceExporter userService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(userService);
        exporter.setServiceInterface(UserService.class);
        logger.info("Open UserService-RPC.");
        return exporter;
    }
}
