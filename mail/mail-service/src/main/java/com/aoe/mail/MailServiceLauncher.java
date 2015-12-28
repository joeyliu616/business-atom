package com.aoe.mail;

import com.aoe.service.common.ServiceLauncherTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by joey on 15-12-20.
 */
@SpringBootApplication
public class MailServiceLauncher extends ServiceLauncherTemplate{
    @Bean
    public ObjectMapper objectMapper(){return new ObjectMapper();}

    public static void main(String[] args) {
        MailServiceLauncher.startService(MailServiceLauncher.class,args);
    }
}
