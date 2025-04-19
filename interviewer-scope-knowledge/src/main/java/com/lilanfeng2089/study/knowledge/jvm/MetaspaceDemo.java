package com.lilanfeng2089.study.knowledge.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description 元空间会产生内存溢出么？ 在什么情况下会产生内存溢出？
 *   元空间不会申请内存，而是申请元数据区，元数据区是JVM运行时动态生成的，元数据区是和类相关的， Java8及以后的JVM，元数据区存放在堆外，
 *   堆外内存申请失败，就会发生内存溢出。 Metaspace替代了永久代，元数据区存放了类的元数据信息，
 *   比如虚拟机加载的类信息，常量池，静态变量，即时编译后的代码等。
 *   出现问题的原因： 错误的主要原因，是加载到内存中的class数量过大，导致元数据区申请内存失败。
 *   解决方案： 1.设置Metaspace大小，-XX:MaxMetaspaceSize=512m
 * @date 2025/4/18 06:27
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class MetaspaceDemo {
    static class OOM{}
    public static void main(String[] args) {
        //模拟计数多少次以后发生异常
        int i = 0;
        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOM.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
                            throws Throwable {
                        return methodProxy.invokeSuper(o, objects);
                    }
                });
                enhancer.create();
            }
        } catch(Exception e) {
            System.out.println("**************多少次后发生异常：" + i);
            e.printStackTrace();
        }

    }
}
