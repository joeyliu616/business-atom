package com.aoe.base.constants;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by joey on 15-12-20.
 */
public class ErrorCode {

    static AtomicInteger num = new AtomicInteger(1);

    static int step = 1000;

    public final static Integer SMS_Start = num.getAndIncrement() * step;

    public final static Integer Capatcha_Start = num.getAndIncrement() * step;



}
