package com.lilanfeng2089.study.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description 场景：Spring、AOP切面、鉴权服务、中间件开发、RPC框架等
 * 点评：CGLIB不同于JDK，它的底层使用ASM字节码框架在类中修改指令码实现代理，所以这种代理方式也就不需要像JDK那样需要接口才能代理。
 *   同时得益于字节码框架的使用，所以这种代理方式也会比使用JDK代理的方式快1.5~2.0倍。
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class CglibProxy implements MethodInterceptor {
    public Object newInstall(Object object){
        return Enhancer.create(object.getClass(),this);
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("我被CglibProxy代理了");
        return methodProxy.invokeSuper(o,objects);
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        UserApi userApi = (UserApi) cglibProxy.newInstall(new UserApi());
        String invoke = userApi.queryUserInfo();
        System.out.println("测试结果："+ invoke);
    }
}
