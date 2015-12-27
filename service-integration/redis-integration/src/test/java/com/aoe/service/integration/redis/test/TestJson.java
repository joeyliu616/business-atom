package com.aoe.service.integration.redis.test;

import com.aoe.base.dto.CommonResponse;
import com.aoe.base.util.OrderNoUtil;
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
public class TestJson extends SpringBootTestCase {

    @Resource
    CacheClient<CommonResponse> cacheClient;

    @Test
    public void foo(){
        CommonResponse<JsonData> commonResponse = new CommonResponse<>();
        JsonData jsonData = new JsonData();
        String uuid = OrderNoUtil.uuid();
        jsonData.setId(uuid);
        jsonData.setValue(1000L);
        commonResponse.setData(jsonData);

        cacheClient.delete("a");
        cacheClient.set("a", commonResponse);
        CommonResponse<JsonData> response = cacheClient.get("a");

        Assert.isTrue(response.getData().getId().equals(uuid));
    }

}
