package com.aoe.service.integration.redis.test;

import com.aoe.service.integration.redis.CacheClient;
import com.aoe.service.integration.redis.RedisClientHook;
import com.aoe.unitTest.support.SpringBootTestCase;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by joey on 15-12-27.
 */
@SpringApplicationConfiguration(classes = RedisClientHook.class)
public class TestExpire extends SpringBootTestCase{
    @Resource
    CacheClient<Long> cacheClient;

    @Test
    public void foo() throws InterruptedException {
        cacheClient.delete("a");
        cacheClient.setEx("a", 1000L, 1L, TimeUnit.SECONDS);
        Assert.isTrue(cacheClient.get("a") == 1000L);
        Thread.sleep(1000);
        Assert.isTrue(cacheClient.get("a") == null);
    }
}
