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
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}