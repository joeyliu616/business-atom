package com.aoe.sms.client.impl.twilio;

import com.aoe.sms.client.api.SMSClient;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 全球级短信服务提供商.
 * Created by joey on 15-12-19.
 */
@Profile("twilio")
@Service
public class TwilioSMSClientImpl implements SMSClient {

    private static Logger logger = LoggerFactory.getLogger(TwilioSMSClientImpl.class);

    @Value("${sms_twilio_sid}")
    private String sid;

    @Value("${sms_twilio_token}")
    private String token;

    @Value("${sms_twilio_from}")
    private String smsFrom;

    private TwilioRestClient client = null;

    @PostConstruct
    public void initClient(){
        client = new TwilioRestClient(sid,token);
    }

    public Boolean send(String mobile, String content) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("To", "+86"+mobile));
        params.add(new BasicNameValuePair("From", smsFrom));
        params.add(new BasicNameValuePair("Body", content));
        MessageFactory messageFactory = client.getAccount().getMessageFactory();

        try {
            Message message = messageFactory.create(params);

            if(null != message){
                if(null != message.getProperty("error_code")){
                    logger.error("send sms fail. mobile {}, error_code {}",message.getProperty("error_code"));
                    return false;
                }
                logger.debug("twillio message : s " + message.getProperty("error_code"));
                return true;
            }else{
                logger.error("twillio send msg failed.");
                return false;
            }
        } catch (TwilioRestException e) {
            logger.error("",e);
            return false;
        }

    }

    @Override
    public String getSP() {
        return "twilio";
    }
}
