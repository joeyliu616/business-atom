package com.aoe.service.integration.redis.test;

import com.aoe.service.integration.redis.CacheClient;
import com.aoe.service.integration.redis.RedisClientHook;
import com.aoe.unitTest.support.SpringBootTestCase;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by joey on 15-12-27.
 */
@SpringApplicationConfiguration(classes = RedisClientHook.class)
public class TestMapClient extends SpringBootTestCase {
    @Resource
    CacheClient<HashMap<String,Date>> cacheClient;

    @Test
    public void foo(){
        cacheClient.delete("a");
        HashMap<String,Date> map = new HashMap<>();
        map.put("time", new Date());
        cacheClient.set("a", map);
        Date savedTime = (Date)cacheClient.get("a").get("time");
        Assert.isTrue(savedTime.before(new Date()));
    }
}
