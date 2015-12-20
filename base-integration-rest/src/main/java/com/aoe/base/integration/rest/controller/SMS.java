package com.aoe.base.integration.rest.controller;

import com.aoe.base.dto.CommonResponse;
import com.aoe.base.integration.rest.constants.URLConstants;
import com.aoe.base.util.OrderNoUtil;
import com.aoe.sms.dto.MatchResult;
import com.aoe.sms.dto.SMSInfo;
import com.aoe.sms.service.api.SMSService;
import org.springframework.web.bind.annotation.*;

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
    public CommonResponse<SMSInfo> getRegisterCode(String mobile){
        String orderNo = OrderNoUtil.uuid();
        String template = "hello " + mobile + ", you are registting AOE service. code is ${code}";
        CommonResponse<SMSInfo> response = smsService.getSMSCode(mobile, template, orderNo,"base-integration-test");
        return response;
    }

    @RequestMapping(name= URLConstants.SMS.VERIFY_CODE,method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse<MatchResult> verifySMSCode(
            @RequestParam("sms_id") String uuid, @RequestParam("code")String code
    ){
        return smsService.verifySms(uuid, code);
    }

}
