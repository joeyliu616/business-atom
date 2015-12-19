package com.aoe.base.integration.rest;

import com.aoe.service.common.ServiceLauncherTemplate;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by joey on 15-12-19.
 */
@SpringBootApplication
public class BaseLauncher extends ServiceLauncherTemplate{
    public static void main(String[] args) {
        BaseLauncher.startService(BaseLauncher.class,args);
    }
}
