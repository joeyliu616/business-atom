package com.aoe.sms.service.impl;

import com.aoe.base.dto.CommonResponse;
import com.aoe.sms.client.api.SMSClient;
import com.aoe.sms.constants.SMS_ERROR_MSG;
import com.aoe.sms.dto.MatchResult;
import com.aoe.sms.dto.SMSInfo;
import com.aoe.sms.entity.SMS;
import com.aoe.sms.repository.SMSRepository;
import com.aoe.sms.service.api.SMSService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by joey on 15-12-19.
 */
@Service
public class SMSServiceImpl implements SMSService {

    @Resource
    SMSRepository smsRepository;

    @Resource
    RedisTemplate<String,String> redisTemplate;


    @Value("${sms.expire}")
    int expire;

    @Resource
    SMSClient smsClient;



    public String getCode(){
        int i = ThreadLocalRandom.current().nextInt(1000, 9999);
        return Integer.toString(i);

    }

    @Override
    public CommonResponse<SMSInfo> getSMSCode(String mobileNo, String template, String bizNo, String consumer) {
        CommonResponse<SMSInfo> response = new CommonResponse();

        if(StringUtils.isEmpty(mobileNo)){
            return response.setResult(SMS_ERROR_MSG.INVALID_MOBILE_NO);
        }

        String code = this.getCode();
        String content = template.replace("${code}", code);

        if(content.equals(template)){
            return response.setResult(SMS_ERROR_MSG.INVALID_CONTENT_TEMPLATE);
        }

        Boolean sendResult = smsClient.send(mobileNo, content);
        SMS sms = new SMS();
        String smsId = UUID.randomUUID().toString().replaceAll("-", "");
        sms.setContent(content);
        sms.setMobile(mobileNo);

        sms.setSp(smsClient.getSP());
        sms.setIsSent(sendResult);
        sms.setUuid(smsId);
        sms.setBizNo(bizNo);
        sms.setFirstParty(consumer);

        if(sendResult == false){
            //标记为失败.
            sms.setIsReceived(false);
            smsRepository.save(sms);
            return response.setResult(SMS_ERROR_MSG.SEND_SMS_FAIL);
        }


        SMSInfo info = new SMSInfo();
        info.setSMSId(smsId);
        info.setContent(content);
        info.setSMSId(smsId);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,expire);
        info.setExpireAfter(calendar.getTime());
        info.setMobileNo(mobileNo);
        info.setSendTime(new Date());
        response.setData(info);

        redisTemplate.opsForValue().set(smsId,code,expire,TimeUnit.MINUTES);

        return response;
    }

    @Override
    public CommonResponse<MatchResult> verifySms(String uuid, String code) {

        MatchResult matchResult = new MatchResult();

        String value = redisTemplate.opsForValue().get(uuid);
        if(null != value && value.equals(code)){
            matchResult.setIsMatch(true);
        }else{
            matchResult.setIsMatch(false);
        }

        return new CommonResponse<MatchResult>().setData(matchResult);
    }

    @Override
    public CommonResponse<SMSInfo> getSMSInfo(String SMSId) {
        return null;
    }
}
