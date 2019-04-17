package com.nebula.mooc.core.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by 15722 on 2019/4/13.
 */
public class MailUtil {

    private static final String sender = "nebulamooc@163.com";

    private static final JavaMailSender jms;

    static {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.163.com");
        mailSender.setUsername(sender);
        mailSender.setPassword("mooc123456");
        mailSender.setDefaultEncoding("UTF-8");
        jms = mailSender;
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
        jms.send(mainMessage);
    }
}
