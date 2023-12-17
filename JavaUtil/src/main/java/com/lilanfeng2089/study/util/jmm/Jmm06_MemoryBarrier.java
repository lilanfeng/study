package com.lilanfeng2089.study.util.jmm;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class Jmm06_MemoryBarrier {
    int a;
    public volatile int m1 = 1;
    public volatile int m2 = 2;

    public void readAndWrite(){
        //第一个volatile读
        int i = m1;
        //第二个volatile读
        int j = m2;

        //普通写
        a = i + j;

        //第一个volatile写
        m1 = i + 1;
        //第二个volatile写
        m2 = j * 2;
    }
}
