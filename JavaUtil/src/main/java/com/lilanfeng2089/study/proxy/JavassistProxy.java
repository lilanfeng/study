package com.lilanfeng2089.study.proxy;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.IOException;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description Javassist 代理
 * 场景：全链路监控、类代理、AOP
 * 点评：Javassist 是一个使用非常广的字节码插装框架，几乎一大部分非入侵的全链路监控都是会选择使用这个框架。因为它不想ASM
 * 那样操作字节码导致风险，同时它的功能也非常齐全。另外，这个框架即可使用它所提供的方式直接编写插装代码，也可以使用字节码指令进行控制生成代码，所以综合来看也是一个非常不错的字节码框架。
 * #
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class JavassistProxy extends ClassLoader{

    public static <T> T getProxy(Class clazz) throws NotFoundException, CannotCompileException, IOException,
            InstantiationException, IllegalAccessException {
        ClassPool pool = ClassPool.getDefault();
        //获取类
        CtClass ctClass = pool.get(clazz.getName());
        //获取方法
        CtMethod ctMethod = ctClass.getDeclaredMethod("queryUserInfo");
        //方法前加强
        ctMethod.insertBefore("{System.out.println(\"" + ctMethod.getName() +" 你被代理了，By Javassist \");}");
        byte[] bytes = ctClass.toBytecode();
        return (T) new JavassistProxy().defineClass(clazz.getName(),bytes,0,bytes.length).newInstance();
    }

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException,
            InstantiationException, IllegalAccessException {
        IUserApi userApi = JavassistProxy.getProxy(UserApi.class);
        String invoke = userApi.queryUserInfo();
        System.out.println("输出结果：" + invoke);
    }
}
