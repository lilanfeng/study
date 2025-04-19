package com.lilanfeng2089.study.therad;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2025/1/6 06:07
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class PrintABC {
    /**
     *创建三个线程，分别打印A、B、C，要求线程间交替打印，且每个线程打印100次。
     *  解题思路：
     *  1，使用synchronized 加 wait/notify实现
     *  2，使用ReentrantLock 加 Condition
     *  3，使用Semaphore信号量实现；
     *  4，使用AtomicInteger 和 CAS；
     *  5，使用CyclicBarrier实现；
     *
     * */
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A");
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("B");
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("C");
            }
        }).start();
        ThreadState.second(1);
        ThreadState.second(1);
        ThreadState.second(1);
        ThreadState.second(1);
    }
}
