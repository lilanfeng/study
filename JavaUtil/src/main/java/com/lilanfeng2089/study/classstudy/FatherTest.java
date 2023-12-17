package com.lilanfeng2089.study.classstudy;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class FatherTest extends Father{
    private String name = " test";

    public static void main(String[] args) {
        FatherTest fatherTest = new FatherTest();
        System.out.println(fatherTest.getName());
        //System.out.println(fatherTest.oldName);
    }
}

class Father{
    private String  name = "father";
    private String oldName = "oldFather";
    public String getName(){
        return name;
    }

}

