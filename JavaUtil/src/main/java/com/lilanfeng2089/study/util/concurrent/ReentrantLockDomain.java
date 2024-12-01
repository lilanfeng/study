package com.lilanfeng2089.study.util.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ReentrantLockDomain {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        new  Thread(reentrantLockTest).start();
        new  Thread(reentrantLockTest).start();
        new  Thread(reentrantLockTest).start();

    }


}

class ReentrantLockTest implements  Runnable {
    ReentrantLock lock = new ReentrantLock();

    int t = 2;

    @Override
    public void run() {
        System.out.println("Thread.currentThread().getName()=> "+ Thread.currentThread().getName() + "在循环外准备开始循环");
        while (true){
            System.out.println("Thread.currentThread().getName()=> "+ Thread.currentThread().getName() + "准备手动拿锁");
            lock.lock();
            System.out.println("Thread.currentThread().getName()=> "+ Thread.currentThread().getName() + "拿到了锁");
            if( t > 0) {
                System.out.println("Thread.currentThread().getName()=> "+ Thread.currentThread().getName() + "在执行");
                t -= 1;
            } else {
                System.out.println("Thread.currentThread().getName()=> "+ Thread.currentThread().getName() + "解锁，退出循环");
                lock.unlock();
                break;
            }
        }
        System.out.println("Thread.currentThread().getName()=> "+ Thread.currentThread().getName() + "执行完毕");
    }
}
