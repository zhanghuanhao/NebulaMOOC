/*
 * @author Zhanghh
 * @date 2019/4/23
 */
package com.nebula.mooc.chatserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatServer {

    public static void main(String[] args) {
        System.setProperty("module.name", "chat-server");
        SpringApplication.run(ChatServer.class, args);
    }

}
