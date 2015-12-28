package com.aoe.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by joey on 15-12-21.
 */
@SpringBootApplication
public class Config {

    @Bean
    public ObjectMapper objectMapper(){return new ObjectMapper();}

    public static void main(String[] args) {
        SpringApplication.run(Config.class,args);
    }
}
