/*
 * @author Zhanghh
 * @date 2019/4/12
 */
package com.nebula.mooc.ssoserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.nebula.mooc.ssoserver.dao")
@SpringBootApplication
public class SsoServer {

    public static void main(String[] args) {
        System.setProperty("module.name", "sso-server");
        SpringApplication.run(SsoServer.class, args);
    }

}