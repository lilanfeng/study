package com.lilanfeng2089.study.config;

import com.lilanfeng2089.study.SimpleBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */

@Configuration
@ConditionalOnClass(SimpleBean.class)  //注解： 当类路径classpath下有指定类的情况下，就会进行自动配置
public class MyAutoConfiguration {

    static {
        System.out.println(" MyAutoConfiguration init.....");
    }


    @Bean
    public SimpleBean simpleBean(){
        return new SimpleBean();
    }
}
