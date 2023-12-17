package com.lilanfeng2089.study.lang;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class StringTest {

    public static void main(String[] args) {

        String str_1 = new String("ab");
        String str_2 = new String("ab");
        String str_3 = "ab";

        /**
         * 看图说话，如下；
         *
         * 1,先说 ==，基础类型比对的是值，引用类型比对的是地址。另外，equal 比对的是哈希值。
         * 2,两个new出来的对象，地址肯定不同，所以是false。
         * 3,intern()，直接把值推进了常量池，所以两个对象都做了 intern() 操作后，比对是常量池里的值。
         * 4,str_3 = "ab"，赋值，JVM编译器做了优化，不会重新创建对象，直接引用常量池里的值。所以str_1.intern() == str_3，比对结果是true。
         */

        //false
        System.out.println(str_1 == str_2);
        //false
        System.out.println(str_1 == str_2.intern());
        //true
        System.out.println(str_1.intern() == str_2.intern());
        //false
        System.out.println(str_1 == str_3);
        //true
        System.out.println(str_1.intern() == str_3);
    }
}
