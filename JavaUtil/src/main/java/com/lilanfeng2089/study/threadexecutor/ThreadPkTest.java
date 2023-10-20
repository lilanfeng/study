package com.lilanfeng2089.study.threadexecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ThreadPkTest {

    public static void main(String[] args) throws InterruptedException {

        Long start = System.currentTimeMillis();
        final List<Integer> list = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < 10000000; i++) {
            Thread thread = new  Thread(){
                @Override
                public void run(){
                    list.add(random.nextInt());
                }
            };
            thread.start();
            //thread.join();
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(list.size());

    }
}
