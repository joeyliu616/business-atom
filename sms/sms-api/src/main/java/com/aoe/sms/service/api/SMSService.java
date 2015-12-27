package com.aoe.sms.service.api;

import com.aoe.base.dto.CommonResponse;
import com.aoe.sms.dto.SMSInfo;

/**
 * Created by joey on 15-12-18.
 */
public interface SMSService {
    /**
     * 向指定手机发送短信验证码
     * @param mobileNo
     * @param content
     * @return
     */
    CommonResponse<SMSInfo> send(String mobileNo, String content);


    /**
     * 根据SMSId 查询短信发送情况.
     * @param SMSId
     * @return
     */
    CommonResponse<SMSInfo> getSMSInfo(String SMSId);

}
