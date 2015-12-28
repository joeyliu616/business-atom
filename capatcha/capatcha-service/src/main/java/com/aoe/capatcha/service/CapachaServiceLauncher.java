package com.aoe.capatcha.service;

import com.aoe.service.common.ServiceLauncherTemplate;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by joey on 15-12-20.
 */
@SpringBootApplication
public class CapachaServiceLauncher extends ServiceLauncherTemplate {

    public static void main(String[] args) {
        CapachaServiceLauncher.startService(CapachaServiceLauncher.class,args);
    }
}
