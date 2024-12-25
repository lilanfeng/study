package com.lilanfeng2089.study.arthas;

import java.util.HashSet;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/13 22:26
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class Arthas {

    private static HashSet hashSet = new HashSet();

    public static void main(String[] args) {
        System.out.println("hello world");
        //  CPU过高
        cpuHigh();

        //模拟线程死锁
        deadThread();

        //不断的向HashSet增加数据
        addHashSetThread();

    }

    public static void cpuHigh() {
        new Thread(()->{
            while (true){
               ;
            }
        }).start();
    }

    public static void deadThread(){
        Object resourceA = new Object();
        Object resourceB = new Object();
        Thread threadA = new Thread(()->{
            synchronized (resourceA){
                System.out.println(Thread.currentThread()+"获取了resourceA");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resourceB){
                    System.out.println(Thread.currentThread()+"获取了resourceB");
                }
            }
        });
        threadA.start();
        Thread threadB = new Thread(()->{
            synchronized (resourceB){
                System.out.println(Thread.currentThread()+"获取了resourceB");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resourceA){
                    System.out.println(Thread.currentThread()+"获取了resourceA");
                }
            }
        });
        threadB.start();
    }

    public static void addHashSetThread(){
        new Thread(()->{
            int count = 0;
            while (true){
                try {
                    hashSet.add("count:"+count);
                    Thread.sleep(1000);
                    count++;
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();

    }


}
