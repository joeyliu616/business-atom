package com.aoe.sms.service.api;

import com.aoe.base.dto.CommonResponse;
import com.aoe.sms.dto.MatchResult;
import com.aoe.sms.dto.SMSInfo;

/**
 * Created by joey on 15-12-18.
 */
public interface SMSService {
    /**
     * 向指定手机发送短信验证码
     * @param mobileNo
     * @return
     */
    CommonResponse<SMSInfo> getSMSCode(String mobileNo,String template);


    /**
     * 校验短信内容
     * @param uuid
     * @param code
     * @return
     */
    CommonResponse<MatchResult> verifySms(String uuid,String code);


    /**
     * 根据SMSId 查询短信发送情况.
     * @param SMSId
     * @return
     */
    CommonResponse<SMSInfo> getSMSInfo(String SMSId);

}
