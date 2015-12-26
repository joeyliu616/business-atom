package com.aoe.service.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by joey on 15-12-24.
 */
@Configuration
@ConditionalOnProperty("aoe.application.queue.default")
@ConditionalOnClass(ActiveMQConnection.class)
public class DefaultActiveMQQueue {

    @Bean(name="defaultQueue")
    public ActiveMQQueue defaultQueue(@Value("aoe.application.queue.default") String defaultName){
        return new ActiveMQQueue(defaultName);
    }
}
