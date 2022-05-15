package com.turtle.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * ApplicationContext：ApplicationContext的工厂类实现
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * 根据bean名字获取工厂中指定bean 对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        Object object = context.getBean(beanName);
        return context.getBean(beanName);
    }

}
