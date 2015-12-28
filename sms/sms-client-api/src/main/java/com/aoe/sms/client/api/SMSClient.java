package com.aoe.sms.client.api;

/**
 * Created by joey on 15-12-19.
 */
public interface SMSClient {
    Boolean send(String mobile, String content);
    String getSP();
}
