package com.lilanfeng2089.study.threadexecutor;

import javafx.beans.property.ReadOnlyDoubleProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class ThreadPoolPkTest {

    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        final List<Integer> list = new ArrayList<>();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Random random = new Random();
        for (int i = 0; i < 10000000; i++) {
            final  int j = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(j);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1,TimeUnit.DAYS);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(list.size());
    }
}
