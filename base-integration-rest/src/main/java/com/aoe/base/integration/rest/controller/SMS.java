package com.aoe.base.integration.rest.controller;

import com.aoe.base.dto.CommonResponse;
import com.aoe.base.dto.EmptyData;
import com.aoe.base.integration.rest.constants.URLConstants;
import com.aoe.sms.service.api.SMSService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by joey on 15-12-19.
 */
@RestController
public class SMS {

    @Resource
    SMSService smsService;

    @RequestMapping(name= URLConstants.SMS.REGISTER_CODE,method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<EmptyData> getRegisterCode(String mobile){
        String content = "hello " + mobile + ", you are registting AOE service. code is 1234";
        smsService.send(mobile, content);
        return new CommonResponse<EmptyData>();
    }

}
