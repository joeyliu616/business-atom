package com.aoe.service.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.zookeeper.config.CuratorFrameworkFactoryBean;
import org.springframework.integration.zookeeper.metadata.ZookeeperMetadataStore;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created by joey on 15-12-21.
 */
@Configuration
@ComponentScan
@ConditionalOnProperty(name="zk.address",matchIfMissing = false)
@ConditionalOnClass(ZookeeperMetadataStore.class)
public class ZKConfig{

    private static Logger  logger = LoggerFactory.getLogger(ZKConfig.class);

    @Bean
    CuratorFramework curatorFramework(@Value("${zk.address}") String address) throws Exception {
        CuratorFrameworkFactoryBean frameworkFactoryBean = new CuratorFrameworkFactoryBean(address);
        logger.info("inint CuratorFramework.");
        return frameworkFactoryBean.getObject();
    }

    /**
     * 可以通过ZookeeperMetadataStore addListener 来获取变量变更的信息
     * @param curatorFramework
     * @return
     * @throws Exception
     */
    @Bean
    ZookeeperMetadataStore zookeeperMetadataStore(CuratorFramework curatorFramework) {
        ZookeeperMetadataStore zookeeperMetadataStore = null;
        curatorFramework.start();
        try {
            curatorFramework.blockUntilConnected(3, TimeUnit.SECONDS);
            zookeeperMetadataStore = new ZookeeperMetadataStore(curatorFramework);
        } catch (Exception e) {
            logger.error("",e);
            Assert.isTrue(false,e.getMessage());
        }
        zookeeperMetadataStore.start();
        logger.info("init zookeeperMetadataStore");
        return zookeeperMetadataStore;
    }


}
