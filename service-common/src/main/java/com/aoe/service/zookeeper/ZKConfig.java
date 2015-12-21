package com.aoe.service.zookeeper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by joey on 15-12-21.
 */
@Configuration
@ComponentScan
public class ZKConfig{

    @Value("${zk.address}")
    String zkUrl;

    @Bean
    CuratorFrameworkFactoryBean curatorFrameworkFactoryBean(){
        return new CuratorFrameworkFactoryBean(zkUrl);
    }

    @Bean
    ZookeeperMetadataStore zookeeperMetadataStore(CuratorFrameworkFactoryBean curatorFrameworkFactoryBean){
        return new ZookeeperMetadataStore(curatorFrameworkFactoryBean);
    }




}
