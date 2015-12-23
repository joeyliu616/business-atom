package com.aoe.service.test;

import com.aoe.service.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.integration.zookeeper.metadata.ZookeeperMetadataStore;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.jms.Destination;
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
    ZookeeperMetadataStore zookeeperMetadataStore;

    @Resource
    JmsTemplate jmsTemplate;

    @Resource
    Destination destination;

    @Test
    public void foo() throws InterruptedException {
        Assert.notNull(jmsTemplate);
        Assert.notNull(zookeeperMetadataStore);

        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createMessage();
                message.setStringProperty("xx", "xx");
                return message;
            }
        });

        Thread.sleep(1000L);
    }
}
