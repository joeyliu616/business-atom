package com.aoe.service.integration.redis;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by joey on 15-12-27.
 * 需要实现Serializable 接口
 */
public interface RedisOps <T extends Serializable> {

    void delete(String key);

    /**
     * 获取某个值
     * @param key
     * @return
     */
    T get(String key);

    /**
     * 存储某个值
     * @param key
     * @param value
     */
    void set(String key, T value);


    /**
     * 设置某个值. 在指定时间过期
     * @param key
     * @param time
     * @param timeUnit
     */
    void setEx(String key, T value, Long time, TimeUnit timeUnit);


    /**
     * 获取某个值. 并且让这个值在制定时间后失效
     * @param key
     * @param time
     * @return
     */
    T getEx(String key, Long time, TimeUnit timeUnit);
/*

    *//**
     * 当某个key出现, 调用callable
     * @param key
     * @param callable
     *//*
    void onKeySet(String key, Callable<Object> callable);


    *//**
     * 当对应的某个key出现. 调用callable
     * @param key
     * @param callable
     *//*
    void onKeyDel(String key, Callable<Object> callable);


    *//**
     * 当某个key对于的值被改变, 调用callable
     * @param key
     * @param callable
     *//*
    void onValueUpdate(String key, Callable<Object> callable);


    *//**
     * 当某个值过期时, 调用Callable
     * @param key
     * @param callable
     *//*
    void onValueExpire(String key, Callable<Object> callable);*/
}
