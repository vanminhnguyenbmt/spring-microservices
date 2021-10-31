package com.nguyenvm.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ContextUtil {
    private static ApplicationContext applicationContext;

    @Autowired
    protected ContextUtil(ApplicationContext applicationContext) {
        ContextUtil.applicationContext = applicationContext;
    }

    /**
     * Get a bean by Class from application context
     *
     * @param clazz
     * @return bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * Get a bean by bean name from application context
     *
     * @param beanName
     * @return bean
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}