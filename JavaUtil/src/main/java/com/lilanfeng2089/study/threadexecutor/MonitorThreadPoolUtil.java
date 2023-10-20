package com.lilanfeng2089.study.threadexecutor;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description 线程监控帮助类 查看线程池的情况
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class MonitorThreadPoolUtil implements Runnable {
    private ThreadPoolExecutor executor;
    private int seconds;
    private boolean run = true;

    public MonitorThreadPoolUtil(ThreadPoolExecutor executor,int delay){
        this.executor = executor;
        this.seconds = delay;
    }

    public void shutdown(){
        this.run = false;
    }

    @Override
    public void run() {
        while (run){
            if(this.executor.isTerminated()){
                System.out.println("任务执行完成");
                break;
            }
            System.out.println(String.format("[monitor]线程池大小：%d,核心数：%d，活跃数：%d，完成数：%d，任务数：%d,线程结束没：%s,任务结束没：%s",
                    this.executor.getPoolSize(),
                    this.executor.getCorePoolSize(),
                    this.executor.getActiveCount(),
                    this.executor.getCompletedTaskCount(),
                    this.executor.getTaskCount(),
                    this.executor.isShutdown(),
                    this.executor.isTerminated()));
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
