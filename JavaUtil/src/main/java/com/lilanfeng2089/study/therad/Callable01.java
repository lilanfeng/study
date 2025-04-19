package com.lilanfeng2089.study.therad;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class Callable01 implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "Test";
    }

    /**
     * @description 测试Callable接口
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new Callable01());
        new Thread(task).start();

        // 获取线程执行结果 阻塞获取方式
        System.out.println(task.get());
    }
}
