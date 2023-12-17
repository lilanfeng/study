package com.lilanfeng2089.study.util.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class SynchronizedTest {

    private static ExecutorService 丽春院 = Executors.newFixedThreadPool(10);

    private static volatile boolean 老鸨 = false;

    public static class 客官 implements Runnable {

        private String 姓名;

        public 客官(String 姓名) {
            this.姓名 = 姓名;
        }

        @Override
        public void run() {
            try {
                清倌(姓名);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static synchronized void 清倌(String 姓名) throws InterruptedException {
        while (true){
            System.out.println("韦春花与" + 姓名 + "喝茶、吟诗、做对、聊风月！");
            if (老鸨){
                System.out.println("老鸨敲门：时间到啦！\r\n");
                老鸨 = false;
                break;
            }
            Thread.sleep(1000);
        }

    }

    private static List<String> list = Arrays.asList("鳌大人", "陈近南", "海大富");

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            int index = i %  list.size();
            丽春院.execute(new 客官(list.get(index)));
            Thread.sleep(3000);
            老鸨 = true;
        }
    }
}
