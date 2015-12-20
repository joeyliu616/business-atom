package com.aoe.sms.client.impl.emay;

import com.aoe.sms.client.api.SMSClient;
import com.aoe.sms.client.impl.emay.sp.EmayClient;
import com.aoe.sms.client.impl.emay.sp.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 亿美
 * Created by joey on 15-12-19.
 */
@Profile("emay")
@Service
public class EmaySMSClientImpl implements SMSClient {

    private static Logger logger = LoggerFactory.getLogger(EmaySMSClientImpl.class);

    @Resource
    EmayClient emayClient;

    @Override
    public Boolean send(String mobile, String content) {
        ResultInfo info = emayClient.sendSMS(mobile, content, "", 5);
        if(null != info){
            if(info.getCode().equals("0")){
                logger.info("emay client send :  mobile {} content {}", mobile,content);
                return true;
            }else {
                logger.warn("emay client reponse fail,eror code {}, error msg {}. send msg fail : mobile {} content {}",
                        info.getCode(), info.getMessage()  ,mobile,content);
                return false;
            }
        }else {
            logger.error("emay client not response, send msg fail  :  mobile {} content {}", mobile,content);
            return false;
        }
    }

    @Override
    public String getSP() {
        return "emay";
    }
}
