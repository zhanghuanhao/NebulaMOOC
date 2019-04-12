/*
 * @author Zhanghh
 * @date 2019/4/12
 */
package com.nebula.mooc.ssoserver;

import com.nebula.mooc.ssoserver.service.SsoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;

import javax.annotation.Resource;

/**
 * @author xuxueli 2018-03-22 23:41:47
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SsoServerApplication {

    @Resource
    private SsoService service;

    public static void main(String[] args) {
        SpringApplication.run(SsoServerApplication.class, args);
    }

    /*
     * 开放RPC服务
     */
    @Bean(name = "/SsoService")
    public HessianServiceExporter ssoService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(service);
        exporter.setServiceInterface(SsoService.class);
        return exporter;
    }

}