package com.aoe.sms.client.impl.test;

import com.aoe.sms.client.impl.emay.EmaySMSClientImpl;
import com.aoe.sms.client.impl.emay.EmaySMSConfigHook;
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
@SpringApplicationConfiguration(classes = {EmaySMSConfigHook.class})
public class SmsTest {

    @Resource
    EmaySMSClientImpl emaySMSClient;

    @Test
    public void foo(){
        Assert.notNull(emaySMSClient);
        emaySMSClient.send("18566231281","hello joey");
    }

}
