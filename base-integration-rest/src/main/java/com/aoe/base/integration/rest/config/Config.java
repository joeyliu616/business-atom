package com.aoe.base.integration.rest.config;

import com.aoe.sms.SMSServiceLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by joey on 15-12-19.
 */
@Configuration
@Import(SMSServiceLauncher.class)
public class Config {
}
