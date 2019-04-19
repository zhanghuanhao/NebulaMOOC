/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.webserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.nebula.mooc.webserver.dao")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WebServer {

    /**
     * 初始化环境变量
     */
    private static void initEnv() {
        System.setProperty("module.name", "web-server");
    }

    public static void main(String[] args) {
        initEnv();
        SpringApplication.run(WebServer.class, args);
    }

}