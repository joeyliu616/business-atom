package com.aoe.sms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by joey on 15-12-18.
 */
public class SMSInfo {

    //短信内容. 不带签名.
    @JsonProperty
    private String content;

    //手机号
    @JsonProperty
    private String mobileNo;

    //短信ID
    @JsonProperty("sms_id")
    private String SMSId;

    //发送时间
    @JsonProperty("send_time")
    private String sendTime;

    //用户是否已经收到
    @JsonProperty("is_received")
    private Boolean isReceived;
}
