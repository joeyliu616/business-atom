package com.aoe.service.integration.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by joey on 15-12-27.
 */
@Component
public class CacheClient<T extends Serializable> implements RedisOps<T> {

    @Resource
    RedisTemplate<String, T> redisTemplate;

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
        return;
    }

    /**
     * 获取某个值
     * @param key
     * @return
     */
    public T get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 存储某个值
     * @param key
     * @param value
     * @return
     */
    public void set(String key, T value) {
        redisTemplate.opsForValue().set(key,value);
        return;
    }

    /**
     * 设置某个值. 在指定时间过期
     * @param key
     * @param value
     * @param time
     * @param timeUnit @return
     */
    public void setEx(String key, T value, Long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key,value);
        redisTemplate.expire(key,time, timeUnit);
    }

    /**
     * 获取某个值. 并且让这个值在制定时间后失效
     * @param key
     * @param time
     * @param timeUnit
     * @return
     */
    public T getEx(String key, Long time, TimeUnit timeUnit) {
        T t = redisTemplate.opsForValue().get(key);
        redisTemplate.expire(key,time,timeUnit);
        return t;
    }

    /**
     * 当某个值过期时, 调用Callable
     *
     * @param key
     * @param callable
     */
    public void onValueExpire(String key, Callable callable) {

    }

    /**
     * 当某个key对于的值被改变, 调用callable
     *
     * @param key
     * @param callable
     */
    public void onValueUpdate(String key, Callable callable) {

    }

    /**
     * 当对应的某个key出现. 调用callable
     *
     * @param key
     * @param callable
     */
    public void onKeyDel(String key, Callable callable) {

    }

    /**
     * 当某个key出现, 调用callable
     *
     * @param key
     * @param callable
     */
    public void onKeySet(String key, Callable callable) {

    }


}
