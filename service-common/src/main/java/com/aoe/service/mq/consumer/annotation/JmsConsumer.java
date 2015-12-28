package com.aoe.service.mq.consumer.annotation;

import java.lang.annotation.*;

/**
 * Mark a method that can consume mq messages.
 * The container will invoke this method after receives a msg.
 * Created by joey on 15-12-24.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JmsConsumer {

    //queue name
    String queue() default "";

}
