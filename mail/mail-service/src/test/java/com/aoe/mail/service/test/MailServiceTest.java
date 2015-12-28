package com.aoe.mail.service.test;

import com.aoe.base.dto.CommonResponse;
import com.aoe.base.util.OrderNoUtil;
import com.aoe.mail.MailServiceLauncher;
import com.aoe.mail.dto.MailInfo;
import com.aoe.mail.service.api.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Created by joey on 15-12-20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MailServiceLauncher.class)
public class MailServiceTest {

    @Resource
    MailService mailService;

    @Resource
    MailSender mailSender;

    @Value("${spring.mail.username}")
    String senderUserName;
    String senderPwd;

    @Test
    public void foo(){
        Assert.notNull(mailService);
        Assert.notNull(mailSender);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(senderUserName + "@163.com");
        simpleMailMessage.setReplyTo(senderUserName + "@163.com");
        simpleMailMessage.setText("Hi");
        simpleMailMessage.setTo("joeyliu616@live.cn");
        simpleMailMessage.setSubject("邮件测试");

        mailSender.send(simpleMailMessage);

    }

    @Test
    public void testMailService(){
        CommonResponse<MailInfo> response = mailService.sendMail("joeyliu616@live.cn", "这不是玩笑", "邮件服务测试", OrderNoUtil.uuid(), "unit-test");
        Assert.isTrue(response.getResult().getCode() == 0);
    }
}