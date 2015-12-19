package com.aoe.sms.service;

import com.aoe.sms.SMSServiceLauncher;
import com.aoe.sms.client.api.SMSClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Created by joey on 15-12-19.
 */
@SpringApplicationConfiguration(classes = {SMSServiceLauncher.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSMSClient {

    @Resource
    SMSClient smsClient;

    @Test
    public void foo(){
        Assert.notNull(smsClient);
        Assert.isTrue(smsClient.send("18566231281","Hello joey"));
    }
}
