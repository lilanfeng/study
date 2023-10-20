package HJ2023B;

import jdk.nashorn.internal.runtime.regexp.joni.ScanEnvironment;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 题目描述
 * 小牛的孩子生日快要到了，他打算给孩子买蛋糕和小礼物，蛋糕和小礼物各买一个，他的预算不超过x元。蛋糕cake和小礼物gift都有多种价位的可供选择。
 * 请返回小牛共有多少种购买方案。
 * 输入描述
 * 第一行表示cake的单价，以逗号分隔
 * 第二行表示gift的单价，以逗号分隔
 * 第三行表示x预算
 * 输出描述
 * 输出数字表示购买方案的总数
 * 备注
 * 1 ≤ cake.length ≤ 10^5
 * 1 ≤ gift.length ≤10^5
 * 1 ≤ cake[i]，gift[i] ≤ 10^5
 * 1 ≤ X ≤ 2*10^5
 * 用例
 * 输入
 * 10,20,5
 * 5,5,2
 * 15
 * 输出
 * 6
 * 说明
 * 解释: 小牛有6种购买方案，所选蛋糕与所选礼物在数组中对应的下标分别是：
 * 第1种方案: cake [0] + gift [0] = 10 + 5 = 15;
 * 第2种方案: cake [0] + gift [1]= 10 + 5 = 15;
 * 第3种方案: cake [0] + gift [2] = 10 + 2 = 12;
 * 第4种方案: cake [2] + gift [0] = 5 &
 * 文章知识点与官方知识档案匹配，可进一步学习相关知识
 * 算法技能树首页概览52297 人正在系统学习中
 * 版权声明：本文为CSDN博主「程序员阿甘」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_34143141/article/details/131466177
 */
public class _058_生日礼物 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] cakePrice = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] giftPrice = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int totalPrice = Integer.parseInt(sc.nextLine());
        int count = 0;
        for (int i = 0; i < cakePrice.length; i++) {
            int price = cakePrice[i];
            for (int j = 0; j < giftPrice.length; j++) {
                if (totalPrice >= (price + giftPrice[j])) {
                    count++;
                }
            }
        }
        System.out.println(count);

    }

}
