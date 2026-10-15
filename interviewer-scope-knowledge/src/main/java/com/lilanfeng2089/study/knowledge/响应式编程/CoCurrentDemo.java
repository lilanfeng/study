package com.lilanfeng2089.study.knowledge.响应式编程;

import com.lilanfeng2089.study.knowledge.Utils.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2025/5/20 07:02
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class CoCurrentDemo {

    /**
     *  使用CompletableFuture 和 CountDownLatch 进行并发回调
     * @param args
     */
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        //批量异步操作处理
        ExecutorService executorService = ThreadUtils.getIoIntenseTargetThreadPool();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
                long tid = ThreadUtils.getCurrentThreadId();
                
            }, executorService);
        }

    }
}
