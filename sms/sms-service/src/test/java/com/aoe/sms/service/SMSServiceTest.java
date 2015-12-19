package com.aoe.sms.service;

import com.aoe.sms.SMSServiceLauncher;
import com.aoe.sms.service.api.SMSService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by joey on 15-12-19.
 */
@SpringApplicationConfiguration(classes = {SMSServiceLauncher.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class SMSServiceTest {

    @Resource
    SMSService smsService;

    @Test
    public void testSend(){
        smsService.send("18566231281","hello joey");
    }
}
