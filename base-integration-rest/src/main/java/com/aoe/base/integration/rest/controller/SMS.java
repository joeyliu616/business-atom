package com.aoe.base.integration.rest.controller;

import com.aoe.base.dto.CommonResponse;
import com.aoe.base.integration.rest.constants.ServerConstants;
import com.aoe.base.util.OrderNoUtil;
import com.aoe.sms.dto.MatchResult;
import com.aoe.sms.dto.SMSInfo;
import com.aoe.sms.service.api.SMSService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by joey on 15-12-19.
 */
@Controller
public class SMS {

    @Resource
    SMSService smsService;

    @RequestMapping(value= "/demo/sms/register_code",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<SMSInfo> getRegisterCode(String mobile){
        String orderNo = OrderNoUtil.uuid();
        String template = "hello " + mobile + ", you are registting AOE service. code is ${code}";
        CommonResponse<SMSInfo> response = smsService.getSMSCode(mobile, template, orderNo, ServerConstants.Name);
        return response;
    }

    @RequestMapping(value= "/demo/sms/verify_code",method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse<MatchResult> verifySMSCode(
            @RequestParam("sms_id") String uuid, @RequestParam("code")String code
    ){
        return smsService.verifySms(uuid, code);
    }

}
