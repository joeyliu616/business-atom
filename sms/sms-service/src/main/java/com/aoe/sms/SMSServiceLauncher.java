package com.aoe.sms;


import com.aoe.service.common.ServiceLauncherTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.SocketException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by joey on 15-12-18.
 */
@SpringBootApplication
public class SMSServiceLauncher extends ServiceLauncherTemplate {


    private static Map<String,String> map = new ConcurrentHashMap<String, String>();
    private static Logger logger = LoggerFactory.getLogger(SMSServiceLauncher.class);

    public static void main(String[] args) throws SocketException, InterruptedException {
        SMSServiceLauncher.startService(SMSServiceLauncher.class,args);
    }


}
