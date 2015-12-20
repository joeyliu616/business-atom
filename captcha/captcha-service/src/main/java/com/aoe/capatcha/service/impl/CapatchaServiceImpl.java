package com.aoe.capatcha.service.impl;

import com.aoe.base.dto.CommonResponse;
import com.aoe.base.util.OrderNoUtil;
import com.aoe.capatcha.dto.CapatchaInfo;
import com.aoe.capatcha.dto.MatchResult;
import com.aoe.capatcha.service.api.CapatchaService;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.service.Captcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by joey on 15-12-20.
 */
@Service
public class CapatchaServiceImpl implements CapatchaService {

    @Resource
    ConfigurableCaptchaService cs;

    @Value("${capatcha.expire}")
    int expireTime;

    @Resource
    RedisTemplate<String, String> redisTemplate;

    private static Logger logger = LoggerFactory.getLogger(CapatchaServiceImpl.class);

    @Override
    public CommonResponse<CapatchaInfo> createCapatcha(String bizNo, String firstPartName) {

        CommonResponse<CapatchaInfo> response = new CommonResponse<>();

        //生成验证码
        Captcha captcha = cs.getCaptcha();
        String code = captcha.getChallenge();
        BufferedImage bufferedImage = captcha.getImage();
        String uuid = OrderNoUtil.uuid();

        redisTemplate.opsForValue().set(uuid, code, expireTime, TimeUnit.MINUTES);
        logger.info("firstParty {}, bizNo {}: capatcha uuid {} code {}",firstPartName,bizNo, uuid, code);

        CapatchaInfo capatchaInfo = new CapatchaInfo(imageToByte(bufferedImage),uuid);
        Calendar calendar = Calendar.getInstance();
        calendar.add(expireTime,Calendar.MINUTE);
        capatchaInfo.setExpireAfter(calendar.getTime());
        return response.setData(capatchaInfo);

    }

    @Override
    public CommonResponse<MatchResult> verifyCode(String uuid, String code) {

        CommonResponse<MatchResult> response = new CommonResponse<>();
        MatchResult matchResult = new MatchResult();

        String value = redisTemplate.opsForValue().get(uuid);

        if(null != value && null != code && value.equals(code)){
            matchResult.setIsMatch(true);
            return response.setData(matchResult);
        }
        matchResult.setIsMatch(false);
        return response.setData(matchResult);
    }

    private byte[] imageToByte(BufferedImage bufferedImage) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", out);
            byte[] bytes = out.toByteArray();
            return bytes;
        } catch (Exception ex) {
            logger.error("", ex);
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                logger.error("",e);
            }
        }
    }
}
