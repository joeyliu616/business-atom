package com.aoe.base.util;

import java.util.UUID;

/**
 * Created by joey on 15-12-20.
 */
public class OrderNoUtil {

    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
