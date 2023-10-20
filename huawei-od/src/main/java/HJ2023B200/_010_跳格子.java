package HJ2023B200;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 *
 *跳格子
 * 时间限制：1s 空间限制：256MB 限定语言：不限
 * 题目描述：
 * 小明和朋友玩跳格子游戏， 有 n 个连续格子，每个格子有不同的分数，小朋友可以选择从任意格子起跳，但是不能跳连续的格子，也不能回头跳;
 * 给定一个代表每个格子得分的非负整数数组，计算能够得到的最高分数。
 * 输入描述：
 * 给定一个数例，如：
 * 1 2 3 1
 * 输出描述：
 * 输出能够得到的最高分，如：
 * 4
 *
 * 补充说明：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 *
 * 示例1
 * 输入：
 * 1 2 3 1
 * 输出：
 * 4
 *
 * ————————————————
 * 版权声明：本文为CSDN博主「若博豆」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_34465338/article/details/131121004
 *
 */
public class _010_跳格子 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            findMaxPoint(sc.nextLine());
        }
    }

    public static void findMaxPoint(String str){

        int[] points = Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = points.length;
        if (n == 1) {
            System.out.println(points[0]);
            return;
        }
        int[] dp = new int[n];
        //计算不包含最后一个元素的最大分数
        dp[0] = points[0];
        dp[1] = Math.max(points[0],points[1]);
        for (int i = 2; i <  n; i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2] + points[i]);
        }
        int max1 = dp[n-1];

        //计算不包含第一个元素的最大分数
        dp[0] = 0;
        dp[1] = points[1];
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2] + points[i]);
        }
        int max2 = dp[n-1];
        System.out.println(Math.max(max1,max2));
    }

}
