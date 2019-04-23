package com.nebula.mooc.chatserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ChatServer {

    /**
     * 初始化环境变量
     */
    private static void initEnv() {
        System.setProperty("module.name", "chat-server");
    }

    public static void main(String[] args) {
        initEnv();
        SpringApplication.run(ChatServer.class, args);
    }
}
