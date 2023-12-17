package com.lilanfeng2089.study.util.jmm;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class Jmm03_CodeVisibility {

    private static boolean initFlag = false;
    //private static volatile boolean initFlag = false;

    private static int count = 0;
    //private static Integer count = 0;

    public static void refresh(){
        System.out.println("refresh data......");
        initFlag = true;
        System.out.println("refresh data success......");
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(()->{
            while (!initFlag){
                count++;
            }
            System.out.println("线程：" + Thread.currentThread().getName() + "当前线程嗅探到initFlag的状态改变");
        });
        threadA.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread threadB = new Thread(()->{
            refresh();
        });
        threadB.start();
    }
}
