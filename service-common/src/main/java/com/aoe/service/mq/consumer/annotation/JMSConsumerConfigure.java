package com.aoe.service.mq.consumer.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by joey on 15-12-24.
 */
@Component
public class JMSConsumerConfigure implements BeanPostProcessor{

    private static Logger logger = LoggerFactory.getLogger(JMSConsumerConfigure.class);

    class InvocationInfo{
        Object bean;
        Method method;
        Class[] params;

        public Object getBean() {
            return bean;
        }

        public void setBean(Object bean) {
            this.bean = bean;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public Class[] getParams() {
            return params;
        }

        public void setParams(Class[] params) {
            this.params = params;
        }

        public InvocationInfo(Object bean, Method method, Class[] params) {
            this.bean = bean;
            this.method = method;
            this.params = params;
        }
    }

    Set<InvocationInfo> invocationSet = new HashSet<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopUtils.getTargetClass(bean);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            JmsConsumer annotation = AnnotationUtils.getAnnotation(method, JmsConsumer.class);
            if(null != annotation){
                logger.info("find jms consumer... bean {} method {}", bean.getClass(), method.getName());
                method.setAccessible(true);
                Class<?>[] parameterTypes = method.getParameterTypes();
                invocationSet.add(new InvocationInfo(bean, method,parameterTypes));
            }
        }

        return bean;
    }

    public Object processMessageConsume(Object... objects){
        Iterator<InvocationInfo> iterator = invocationSet.iterator();
        while (iterator.hasNext()){
            boolean match = true;
            InvocationInfo info = iterator.next();
            Method method = info.getMethod();
            Class[] params = info.getParams();
            Object bean = info.getBean();
            if(objects.length != params.length){
                continue;
            }
            for (int i = 0 ; i < params.length; i++) {
                for (Object object : objects) {
                    if(object.getClass() != params[i]){
                        match = false;
                        break;
                    }
                }
            }
            if(match == true){
                try {
                    Object result = method.invoke(bean, objects);
                    return result;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}
