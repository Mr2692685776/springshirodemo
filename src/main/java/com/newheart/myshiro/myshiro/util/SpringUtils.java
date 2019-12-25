package com.newheart.myshiro.myshiro.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author hanjie
 * @date 2019/12/23 9:11
 */
@Configuration
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext context ;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public  static  <T> T getbean(Class<T> beanclass){
        return context.getBean(beanclass);
    }
}
