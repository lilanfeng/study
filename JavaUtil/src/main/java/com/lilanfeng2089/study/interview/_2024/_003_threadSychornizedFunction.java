package com.lilanfeng2089.study.interview._2024;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/24 23:06
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _003_threadSychornizedFunction extends Thread{

    private  static int a = 0;

//    @Override
//    public synchronized  void run() {
//
//        for (int i = 0; i < 100; i++) {
//            a++;
//            System.out.println(Thread.currentThread().getName() + "---" + a);
//        }
//    }
    @Override
    public synchronized  void run() {

        synchronized (this){
            for (int i = 0; i < 100; i++) {
                a++;
                System.out.println(Thread.currentThread().getName() + "---" + a);
            }
        }
    }


    public static void main(String[] args) {
        _003_threadSychornizedFunction t1 = new _003_threadSychornizedFunction();
        t1.start();
        _003_threadSychornizedFunction t2 = new _003_threadSychornizedFunction();
        t2.start();
    }


}
