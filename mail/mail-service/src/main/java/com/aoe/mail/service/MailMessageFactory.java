package com.aoe.mail.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created by joey on 15-12-20.
 */
@Service
public class MailMessageFactory {

    @Value("${mail.server}")
    String mailServer;

    @Value("${spring.mail.username}")
    String senderUserName;

    public SimpleMailMessage getSimpleMailMessage(String sendTo, String title, String content){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(senderUserName + "@" + mailServer);
        simpleMailMessage.setReplyTo(senderUserName + "@" +  mailServer);
        simpleMailMessage.setTo(sendTo);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(content);

        return simpleMailMessage;
    }
}
