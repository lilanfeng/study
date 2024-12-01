package com.lilanfeng2089.study.base;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class SwitchCase {

    public static void main(String[] args) {
        int n = 0;
        while ( n < 5){
            switch (n){
                case 0:
                case 3:
                    n = n + 3;
                case 1:
                case 2:
                    n = n + 2;
                default:
                    n = n + 10;
            }
        }

        System.out.println(n);
    }
}
