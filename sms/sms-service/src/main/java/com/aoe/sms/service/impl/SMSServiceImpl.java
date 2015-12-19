package com.aoe.sms.service.impl;

import com.aoe.base.dto.CommonResponse;
import com.aoe.sms.dto.SMSInfo;
import com.aoe.sms.entity.SMS;
import com.aoe.sms.repository.SMSRepository;
import com.aoe.sms.service.api.SMSService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by joey on 15-12-19.
 */
@Service
public class SMSServiceImpl implements SMSService {
    @Resource
    SMSRepository smsRepository;

    @Override
    public CommonResponse<SMSInfo> send(String mobileNo, String content) {
        SMS sms = new SMS();
        sms.setContent(content);
        sms.setMobile(mobileNo);

        return null;
    }

    @Override
    public CommonResponse<SMSInfo> getSMSInfo(String SMSId) {
        return null;
    }
}
