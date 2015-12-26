package com.aoe.service.test;

import com.aoe.service.Config;
import com.aoe.service.mq.consumer.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by joey on 15-12-21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Config.class)
public class ConfigTest {

    @Resource
    JmsTemplate jmsTemplate;

    @Test
    public void foo() throws InterruptedException {
        //ong id, String a, T t
        jmsTemplate.send("config", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createMessage();
                message.setLongProperty("id", 1L);
                message.setStringProperty("a", "abc");
                T t = new T();
                t.setK1("kk");
                t.setK2(111L);
                //message.setObjectProperty("t",t);
                return message;
            }
        });

        Thread.sleep(1000L);
    }
}
