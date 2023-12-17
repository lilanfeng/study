package com.lilanfeng2089.study.classstudy;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */


public class Person {
    private String name = "Person";
    int age = 0;

}

class Child extends Person{
    public String grade;

    public static void main(String[] args) {
        Person p = new Child();
        //System.out.println(p.name);//编译出错
    }
}

