package com.lilanfeng2089.study.outofmemory.out;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/6 08:37
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class HeapMemoryOverflow {
    public static void main(String[] args) {
        // 模拟堆内存溢出
//        byte[] bytes = new byte[50 * 1024 * 1024];
//        while (true) {
//            bytes = new byte[50 * 1024 * 1024];
//        }
        /**
         * 在运行上述 HeapMemoryOverflow 示例时，可能需要调整 JVM 参数以较小的堆大小运行，
         * 例如 -Xmx10m，以更快地观察到 OutOfMemoryError。
         */
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
        }
    }
}
