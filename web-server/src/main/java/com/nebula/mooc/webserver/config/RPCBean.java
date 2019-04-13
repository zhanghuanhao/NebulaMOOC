/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.webserver.config;

import com.nebula.mooc.webserver.service.SsoService;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class RPCBean {

    @Bean
    public HessianProxyFactoryBean SsoService() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setServiceUrl("http://localhost:8080/SsoService");
        factory.setServiceInterface(SsoService.class);
        return factory;
    }

}
