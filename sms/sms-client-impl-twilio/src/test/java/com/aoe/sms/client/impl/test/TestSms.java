package com.aoe.sms.client.impl.test;

import com.aoe.sms.client.impl.twilio.TwilioSMSClientImpl;
import com.aoe.sms.client.impl.twilio.TwilioSMSConfigHook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Created by joey on 15-12-19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TwilioSMSConfigHook.class})
public class TestSms {

    @Resource
    TwilioSMSClientImpl twilioSMSClient;

    @Test
    public void foo(){
        Assert.notNull(twilioSMSClient);
    }

}
