package com.aoe.sms.service.impl;

import com.aoe.base.dto.CommonResponse;
import com.aoe.sms.client.api.SMSClient;
import com.aoe.sms.dto.SMSInfo;
import com.aoe.sms.entity.SMS;
import com.aoe.sms.repository.SMSRepository;
import com.aoe.sms.service.api.SMSService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by joey on 15-12-19.
 */
@Service
public class SMSServiceImpl implements SMSService {

    @Resource
    SMSRepository smsRepository;

    @Resource
    SMSClient smsClient;

    @Override
    public CommonResponse<SMSInfo> send(String mobileNo, String content) {

        //TODO 校验手机号.

        //TODO 校验内容. 不为空.

        Boolean sendResult = smsClient.send(mobileNo, content);
        SMS sms = new SMS();
        String smsId = UUID.randomUUID().toString().replaceAll("-", "");
        sms.setContent(content);
        sms.setMobile(mobileNo);
        sms.setSender(smsClient.getSender());
        sms.setIsSent(sendResult);
        sms.setSMSId(smsId);
        SMS save = smsRepository.save(sms);
        CommonResponse<SMSInfo> response = new CommonResponse();
        SMSInfo info = new SMSInfo();
        info.setSMSId(smsId);
        info.setContent(content);
        info.setSMSId(smsId);
        response.setData(info);

        return response;
    }

    @Override
    public CommonResponse<SMSInfo> getSMSInfo(String SMSId) {
        return null;
    }
}
