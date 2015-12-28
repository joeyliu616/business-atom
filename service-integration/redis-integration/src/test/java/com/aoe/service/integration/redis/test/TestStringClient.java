package com.aoe.service.integration.redis.test;

import com.aoe.service.integration.redis.CacheClient;
import com.aoe.service.integration.redis.RedisClientHook;
import com.aoe.unitTest.support.SpringBootTestCase;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * Created by joey on 15-12-27.
 */
@SpringApplicationConfiguration(classes = RedisClientHook.class)
public class TestStringClient extends SpringBootTestCase {

    @Resource
    CacheClient<String> redisCacheClient;


    @Test
    public void foo(){
        redisCacheClient.delete("a");
        String a = redisCacheClient.get("a");
        Assert.isNull(a);
        redisCacheClient.set("a", "a");
        Assert.isTrue("a".equals(redisCacheClient.get("a")));

    }
}
