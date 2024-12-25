package com.lilanfeng2089.study.interview._2024;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/24 22:59
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _001_printij {

    public static void main(String[] args) {
        int i = 0;
        int j = 0;
        for (i = 1; i < 10; i++) {
            if(i%2 == 0){
                continue;
            }
            for (j = 1; j < 10 ; j++) {
               if( j == 5){
                   break;
               }
            }
        }
        System.out.println("i="+ i + " j = " + j);

    }
}
