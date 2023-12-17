package com.lilanfeng2089.study.util.jmm;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description  操作系统中缓存行导致执行时间不一致， 缓存行大小64字节
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class Jmm02_CpuCache {
    private static  final  int RUNS = 10;
    private static final  int DIMENSION_1 = 1024*1024;
    private static final int DIMENSION_2 = 6;

    private static long[][] longs;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //初始化数组
        longs = new long[DIMENSION_1][];
        for (int i = 0; i < DIMENSION_1; i++) {
            longs[i] = new long[DIMENSION_2];
            for (int j = 0; j < DIMENSION_2; j++) {
                longs[i][j] = 1L;
            }
        }
        System.out.println("inited ...");

        long sum = 0L;
        long start = System.currentTimeMillis();
        for (int i = 0; i < RUNS; i++) {
            for (int j = 0; j < DIMENSION_1; j++) {
                for (int k = 0; k < DIMENSION_2; k++) {
                    sum += longs[j][k];
                }
            }
        }

        System.out.println("Cost time1:" + (System.currentTimeMillis() - start) );
        System.out.println("sum:" + sum);

        sum = 0L;
        start = System.currentTimeMillis();
        for (int m = 0; m < RUNS; m++) {
            for (int j = 0; j < DIMENSION_2; j++) {
                for (int k = 0; k < DIMENSION_1; k++) {
                    sum += longs[k][j];
                }
            }
        }

        System.out.println("Cost time2:" + (System.currentTimeMillis() - start) );
        System.out.println("sum:" + sum);

        //int i = Runtime.getRuntime().availableProcessors();
    }
}
