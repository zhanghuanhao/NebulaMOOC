/*
 * @author Zhanghh
 * @date 2019/4/12
 */
package com.nebula.mooc.ssoserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.nebula.mooc.ssoserver.dao")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SsoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoServerApplication.class, args);
    }

}