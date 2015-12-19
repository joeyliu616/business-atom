package com.aoe.service.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;

/**
 * Created by joey on 15-12-18.
 */
public abstract class ServiceLauncherTemplate {

    @Bean
    ObjectMapper objectMapper(){return new ObjectMapper();}
    public static void startService(Class Launcher,String... args){
        SpringApplication springApplication = new SpringApplication(Launcher);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }
}
