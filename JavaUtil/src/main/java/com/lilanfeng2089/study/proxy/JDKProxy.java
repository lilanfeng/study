package com.lilanfeng2089.study.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description  JDK代理方式
 * 场景：中间件开发、设计模式中代理模式和装饰器模式应用
 * 点评：这种JDK自带的类代理方式是非常常用的一种，也是非常简单的一种。基本会在一些中间件代码里看到例如：数据库路由组件、Redis组件等，
 *      同时我们也可以使用这样的方式应用到设计模式中。
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class JDKProxy {

    public static <T> T getProxy(T target){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),new ProxyHandler(target));
    }

    private static class  ProxyHandler<T> implements InvocationHandler{
        private T t;
        ProxyHandler(T t){
            this.t = t;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(method.getName() + "你被代理了，By JDKProxy");
            return method.invoke(t,args);
        }
    }

    public static void main(String[] args) {

        IUserApi userApi = JDKProxy.getProxy(new UserApi());
        String invoke = userApi.queryUserInfo();
        System.out.println("测试结果："+invoke);
    }
}
