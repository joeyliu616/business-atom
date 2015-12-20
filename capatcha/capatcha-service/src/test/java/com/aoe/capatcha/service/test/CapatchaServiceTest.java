package com.aoe.capatcha.service.test;

import com.aoe.base.dto.CommonResponse;
import com.aoe.base.util.OrderNoUtil;
import com.aoe.capatcha.dto.CapatchaInfo;
import com.aoe.capatcha.service.CapachaServiceLauncher;
import com.aoe.capatcha.service.api.CapatchaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by joey on 15-12-20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CapachaServiceLauncher.class)
public class CapatchaServiceTest {

    @Resource
    CapatchaService capatchaService;

    @Resource
    ObjectMapper objectMapper;

    @Test
    public void foo() throws IOException {
        Assert.notNull(capatchaService);

        CommonResponse<CapatchaInfo> capatcha = capatchaService.createCapatcha(OrderNoUtil.uuid(), "joey-test");

        Assert.notNull(capatcha.getData());

        String s = objectMapper.writeValueAsString(capatcha);
        System.out.println(s);

        byte[] imageBytes = capatcha.getData().getImage();

        File file = new File("captacha.png");

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imageBytes);
        fos.flush();
        fos.close();

        System.out.println("==> 验证码图片在这里."+file.getAbsolutePath());
    }

    @Test
    public void verify(){
        Assert.isTrue(capatchaService.verifyCode("bf7e41f29bc14bd6a75d1aeff57ba7c5","s3nw").getResult().getCode() == 0);
        Assert.isTrue(capatchaService.verifyCode("bf7e41f29bc14bd6a75d1aeff57ba7c5","s3nw").getData().isMatch() == true);
        Assert.isTrue(capatchaService.verifyCode("bf7e41f29bc14bd6a75d1aeff57ba7c5","ae31").getData().isMatch() == false);
    }

}
