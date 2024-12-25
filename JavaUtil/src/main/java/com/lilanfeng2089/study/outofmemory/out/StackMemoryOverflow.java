package com.lilanfeng2089.study.outofmemory.out;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/6 08:42
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class StackMemoryOverflow {

    public static void main(String[] args) {
        recursiveMethod();
    }

    public static void recursiveMethod(){
        recursiveMethod();
    }
}
