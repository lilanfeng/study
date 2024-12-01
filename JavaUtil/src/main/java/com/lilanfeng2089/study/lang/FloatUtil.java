package com.lilanfeng2089.study.lang;

/**
 * @program: com.lilanfeng2089.study.lang
 * @description: float数据类型使用案例
 * @author: lilf@bwoil.com
 * @create: 2024-02-01 17:26
 **/
public class FloatUtil {
    public static void main(String[] args) {
        float a = 1f;
        float b = 0.9f;
        float f = a - b;
        /**
         * 实际结果是：0.100000024
         * 科学计数法
         *
         */
        System.out.println(f);

        Float aa = new Float(1f);
        Float bb = new Float(0.9f);
        Float ff = aa - bb;
        System.out.println(ff);

        float fff = 0.9f;
        double ddd = 0.9d;
        System.out.println(fff/1.0);
        System.out.println(ddd/1.0);
        System.out.println(new Float(0.0f));
        System.out.println(Float.NaN);

    }
}
