/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.ssoserver.config;

import com.nebula.mooc.ssoserver.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RPCConfig {
    @Resource
    private UserService userService;

    /*
     * 开放RPC服务
     */
    @Bean(name = "/userService")
    public HessianServiceExporter userService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(userService);
        exporter.setServiceInterface(UserService.class);
        return exporter;
    }
}
