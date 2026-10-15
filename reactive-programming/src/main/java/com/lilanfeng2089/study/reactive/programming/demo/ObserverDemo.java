package com.lilanfeng2089.study.reactive.programming.demo;

import java.util.Observable;
import java.util.Observer;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2025/5/2 06:22
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ObserverDemo extends Observable {

    public static void main(String[] args) {
        ObserverDemo observerDemo = new ObserverDemo();
        // 添加观察者
        observerDemo.addObserver((o, arg)->{
            System.out.println("数据发生变化A");
        });

        observerDemo.addObserver((o, arg)->{
            System.out.println("数据发生变化B");
        });

        observerDemo.setChanged();
        observerDemo.notifyObservers();
    }

}
