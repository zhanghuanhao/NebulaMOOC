/*
 * @author Zhanghh
 * @date 2019/4/23
 */
package com.nebula.mooc.liveserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiveServer {

    public static void main(String[] args) {
        System.setProperty("module.name", "live-server");
        SpringApplication.run(LiveServer.class, args);
    }

}
