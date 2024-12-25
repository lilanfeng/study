package com.lilanfeng2089.study.outofmemory.out;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/6 08:46
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class MetaspaceOverflow {

    public static void main(String[] args) {
        /**
         * 运行 MetaspaceOverflow 示例时，可以使用 JVM 参数
         *  -XX:MaxMetaspaceSize=10m 来限制元空间大小，以更快地观察到溢出。
         */
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DummyClass.class);
        enhancer.setUseCache(false);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                return methodProxy.invokeSuper(o, objects);
            }
        });
        enhancer.create();

    }

    static class DummyClass {

    }
}
