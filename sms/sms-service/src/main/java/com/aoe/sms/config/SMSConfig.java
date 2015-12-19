package com.aoe.sms.config;

import com.aoe.sms.client.impl.emay.EmaySMSConfigHook;
import com.aoe.sms.client.impl.twilio.TwilioSMSConfigHook;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by joey on 15-12-19.
 */
@Configuration
@Import({TwilioSMSConfigHook.class, EmaySMSConfigHook.class})
public class SMSConfig {
}
