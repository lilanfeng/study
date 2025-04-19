package com.lilanfeng2089.study.therad;

import java.util.concurrent.TimeUnit;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ThreadState {

    /**
     * @author lilanfeng2089，微信：lilanfeng2089
     * @description 线程状态
     * @date 2024/3/28 10:08
     * @param args
     */
    public static void main(String[] args){
        new Thread(new TimeWaiting(),"TimeWaitingThread").start();

        new Thread(new Waiting(),"WaitingThread").start();

        //使用两个Blocked线程，一个获取锁成功，另一个被阻塞
        new Thread(new Blocked(),"BlockedThread-1").start();
        new Thread(new Blocked(),"BlockedThread-2").start();
    }

    static class  Waiting implements Runnable{

        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    static class TimeWaiting implements Runnable{

        @Override
        public void run() {
            while (true){
                second(100);
            }

        }
    }

    static class Blocked implements Runnable{

        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    second(100);
                }
            }

        }
    }

    public static final void second(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
