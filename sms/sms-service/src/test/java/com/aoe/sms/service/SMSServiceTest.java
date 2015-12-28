package com.aoe.sms.service;

import com.aoe.base.dto.CommonResponse;
import com.aoe.base.util.OrderNoUtil;
import com.aoe.sms.SMSServiceLauncher;
import com.aoe.sms.constants.SMS_ERROR_MSG;
import com.aoe.sms.dto.SMSInfo;
import com.aoe.sms.entity.SMS;
import com.aoe.sms.repository.SMSRepository;
import com.aoe.sms.service.api.SMSService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by joey on 15-12-19.
 */
@SpringApplicationConfiguration(classes = {SMSServiceLauncher.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class SMSServiceTest {

    @Resource
    SMSService smsService;

    @Resource
    SMSRepository smsRepository;

    @Resource
    ObjectMapper objectMapper;

    @Test
    public void testSend() throws JsonProcessingException {

        String consumer = "joey";

        Assert.notNull(objectMapper);

        //CommonResponse<SMSInfo> commonResponse = smsService.getSMSCode("18566231281", "Hello joey, your code is ${code}", UUID.randomUUID().toString(),consumer);
        //Assert.isTrue(commonResponse.getResult().getCode() == 0);
        //System.out.println(objectMapper.writeValueAsString(commonResponse));

        CommonResponse<SMSInfo> commonResponse = null;

        commonResponse = smsService.getSMSCode("", "Hello joey, your code is ${code}",UUID.randomUUID().toString(),consumer);
        Assert.isTrue(commonResponse.getResult().getCode() == SMS_ERROR_MSG.INVALID_MOBILE_NO.getCode());


        commonResponse = smsService.getSMSCode("18566231281", "Hello joey, your code is}", UUID.randomUUID().toString(), consumer);
        Assert.isTrue(commonResponse.getResult().getCode() == SMS_ERROR_MSG.INVALID_CONTENT_TEMPLATE.getCode());

    }


    @Test
    public void testVerifyCode(){
        Assert.isTrue(smsService.verifySms("e9598d06e9ab45c1b328ed9e8b752b07","3675").getData().isMatch());
    }

    @Test
    public void testRepo(){
        SMS sms = new SMS();
        sms.setIsSent(true);
        sms.setIsReceived(true);
        sms.setUuid(OrderNoUtil.uuid());
        sms.setContent("中文");
        sms.setBizNo(OrderNoUtil.uuid());
        sms.setFirstParty("joy");
        sms.setMobile("18566231281");
        sms.setSp("sp");

        SMS save = smsRepository.save(sms);
        Assert.notNull(save);
    }
}
