package com.aoe.mail.service.impl;

import com.aoe.base.dto.CommonResponse;
import com.aoe.base.util.OrderNoUtil;
import com.aoe.mail.constants.EMAIL_ERROR_MSG;
import com.aoe.mail.dto.MailInfo;
import com.aoe.mail.entity.Mail;
import com.aoe.mail.repository.MailRepository;
import com.aoe.mail.service.MailMessageFactory;
import com.aoe.mail.service.api.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by joey on 15-12-20.
 */
@Service
public class MailServiceImpl implements MailService {

    @Resource
    MailSender mailSender;

    @Resource
    MailMessageFactory mailMessageFactory;

    @Value("${spring.mail.username}")
    String senderUserName;

    @Value("${mail.server}")
    String mailServer;

    String senderAddress;

    @Resource
    MailRepository mailRepository;

    @PostConstruct
    public void init(){
        this.senderAddress = senderUserName + "@" + mailServer;
    }

    private static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Override
    public CommonResponse<MailInfo> sendMail(String address, String title, String content, String bizNo, String firstPartName) {

        Mail mail = new Mail();
        CommonResponse<MailInfo> response = new CommonResponse<>();
        SimpleMailMessage simpleMailMessage = mailMessageFactory.getSimpleMailMessage(address, title, content);

        mail.setSenderAdderss(senderAddress);
        mail.setAddress(address);
        mail.setBizNo(bizNo);
        mail.setFirstParty(firstPartName);
        mail.setContent(content);
        mail.setTitle(title);
        mail.setUuid(OrderNoUtil.uuid());
        mail.setIsSent(true);


        try{
            //mailSender.send(simpleMailMessage);
        }catch (MailException e){
            logger.error("", e);
            mail.setIsSent(false);
            response.setResult(EMAIL_ERROR_MSG.SEND_MAIL_FAIL);
        }

        Mail save = mailRepository.save(mail);
        return response;
    }
}
