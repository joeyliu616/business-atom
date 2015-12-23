package com.aoe.service.mq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.zookeeper.metadata.ZookeeperMetadataStore;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Queue;
import java.io.File;

/**
 * Created by joey on 15-12-22.
 */
@Configuration
@ConditionalOnBean(value = {ZookeeperMetadataStore.class})
@ConditionalOnProperty(name = {"aoe.application.name"})
public class DynamicMessageConsumer {

    private static Logger logger = LoggerFactory.getLogger(DynamicMessageConsumer.class);

    @Resource
    ZookeeperMetadataStore zookeeperMetadataStore;

    @Resource
    Queue queue;

    @Value("${aoe.application.name}")
    String appName;

    private String prefix = "com.aoe" + File.pathSeparator;

    @PostConstruct
    public void init() throws JMSException {
        Assert.isTrue(zookeeperMetadataStore.isRunning());
        String defaultQueueName = prefix + appName + File.pathSeparator + "default";
        zookeeperMetadataStore.put(defaultQueueName, queue.getQueueName());
        String value = zookeeperMetadataStore.get("a");
        logger.info("value {}" ,value);
    }

}
