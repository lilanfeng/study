package com.lilanfeng2089.study.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        //1,反射得到对象
        Class<?> clazz = Class.forName("com.lilanfeng2089.study.reflect.User");
        //2，反射得到方法
        Method method = clazz.getDeclaredMethod("publicMethod");
        //3,执行普通方法
        method.invoke(clazz.getDeclaredConstructor().newInstance());

        //得到私有方法
        Method privateMethod = clazz.getDeclaredMethod("privateMethod");
        //设置私有方法可访问
        privateMethod.setAccessible(true);
        //执行私有方法
        privateMethod.invoke(clazz.getDeclaredConstructor().newInstance());

        //得到静态方法
        Method staticMethod = clazz.getDeclaredMethod("staticMethod");
        //执行静态方法
        staticMethod.invoke(clazz);

        //得到公共属性
        Field field = clazz.getDeclaredField("name");

        //得到属性值
        String name = (String) field.get(clazz.getDeclaredConstructor().newInstance());

        System.out.println("name->" + name);

        //得到私有属性
        Field privateField = clazz.getDeclaredField("age");

        privateField.setAccessible(true);
        //得到属性值
        int age = (int) privateField.get(clazz.getDeclaredConstructor().newInstance());

        System.out.println("age->" + age
        );

    }

}

class User{
    public String name = "李四";

    private int age = 18;

    public void publicMethod(){
        System.out.println("do public method");
    }

    private void privateMethod(){
        System.out.println("do private method");
    }

    public static void  staticMethod(){
        System.out.println("do static method");
    }
}
