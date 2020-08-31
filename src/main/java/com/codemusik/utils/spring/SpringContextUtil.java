package com.codemusik.utils.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: thinkit
 * @date: 2020/4/14 15:01
 * @description: Spring上下文工具类，用于在多线程和静态变量中使用bean
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private SpringContextUtil(){}

    private static ApplicationContext applicationContext;

    /**
     * Sets application context.
     *
     * @param context the context
     */
    @Override
    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    /**
     * Gets bean.
     *
     * @param beanId the bean id
     * @return the bean
     */
    public static Object getBean(String beanId) {
        return applicationContext.getBean(beanId);
    }
}
