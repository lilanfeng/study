package com.lilanfeng2089.study.interview._2024;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/24 23:02
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _002_printInteger {

    public static void main(String[] args) {
        Integer  i1 = new Integer(1);
        Integer i2 = new Integer(1);
        /**
         * Integer.valueOf(1)  -128 - 127 范围是缓存的，不会创建新的对象
         */
        Integer i3 = Integer.valueOf(1);
        Integer i4 = Integer.valueOf(1);

        System.out.println("i1==i2:"+(i1 == i2));
        System.out.println("i1==i3:"+(i1 == i3));
        System.out.println("i3==i4:"+(i3 == i4));
        Integer a = 49;
        int b = 49;
        Integer c = Integer.valueOf(49);
        Integer d = new Integer(49);
        System.out.println("a==b:"+(a == b));
        System.out.println("a==c:"+(a == c));
        System.out.println("b==c:"+(b == c));
        System.out.println("c==d:"+(c == d));

    }
}
