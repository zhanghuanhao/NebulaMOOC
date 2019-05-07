package com.nebula.mooc.webserver.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Created by 15722 on 2019/4/13.
 */
@Component
public class MailUtil {

    private static String sender;

    private static JavaMailSender javaMailSender;

    @Autowired
    public void setJms(JavaMailSender jms) {
        javaMailSender = jms;
    }

    @Value("${spring.mail.username}")
    public void setSender(String username) {
        sender = username;
    }

    public static void send(String receiver, String title, String text) {
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom(sender);
        //接收者
        mainMessage.setTo(receiver);
        //发送的标题
        mainMessage.setSubject(title);
        //发送的内容
        mainMessage.setText(text);
        javaMailSender.send(mainMessage);
    }

}
