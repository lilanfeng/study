package com.lilanfeng2089.study.classloader;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class LifeCycle {

        // 静态属性
        private static String staticField = getStaticField();
        //静态方法块
        static {
            System.out.println(staticField);
            System.out.println("静态方法块初始化"); }
        // 普通属性
        private String field = getField();
        {
            System.out.println(field);
        }
        // 构造函数
        public LifeCycle() {
            System.out.println("构造函数初始化");
        }
        //静态方法
        public static String getStaticField() {
            String statiFiled = "Static Field Initial";
            return statiFiled;
        }

        //
        public static String getField() {
            String filed = "Field Initial";
            return filed;
        }
        // 主函数

    /**
     *   1，静态属性初始化；
     *   2，静态方法块初始化；
     *   3，普通属性初始化；
     *   4，普通方法块初始化；
     *   5，构造函数初始化；
     * @param argc
     */
    public static void main(String[] argc) {
            new LifeCycle();

            /**
             * 顺序如下：
             * Static Field Initial
             * 静态方法块初始化
             * Field Initial
             * 构造函数初始化
             */
        }
}
