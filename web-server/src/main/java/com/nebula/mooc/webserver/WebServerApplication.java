/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.webserver;

import com.nebula.mooc.webserver.service.SsoService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@MapperScan("com.nebula.mooc.webserver.dao")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WebServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebServerApplication.class, args);
    }

    @Bean
    public HessianProxyFactoryBean SsoService() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setServiceUrl("http://localhost:8080/SsoService");
        factory.setServiceInterface(SsoService.class);
        return factory;
    }


}