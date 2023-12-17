package HW;

import com.sun.xml.internal.stream.Entity;

import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 题目描述
 * 某个充电站，可提供 n 个充电设备，每个充电设备均有对应的输出功率。任意个充电设备组合的输出功率总和，均构成功率集合 P 的 1 个元素。
 * 功率集合 P 的最优元素，表示最接近充电站最大输出功率 p _max 的元素
 * 输入描述
 * 输入为 3 行:
 * 。第 1 行为充电设备个数 n
 * 。第 2 行为每个充电设备的输出功率
 * 。第 3 行为充电站最大输出功率 p_max
 * 输出描述
 *   功率集合 P 的最优元素
 * 备注
 * 。充电设备个数 n>0 。最优元素必须小于或等于充电站最大输出功率 p _max
 * 用例：
 *  输入：4
 *      50 20 20 60
 *      90
 *
 *  输出：90
 *      说明：当充电设备输出功率50，20，20组合时，其输出功率总和为90，最接近充电站最大充电输出功率，
 *      因此最优元素为90
 *  输入：2
 *      50 40
 *      30
 *
 *  输出： 0
 *  说明：所有充电设备的输出功率组合，均大于充电站最大充电输出功率30，此时最优解元素为0
 */
public class _004_查找充电设备组合 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int[] pArray = new int[number];
        for (int i = 0; i < number; i++) {
            pArray[i] = sc.nextInt();
        }
        int max = sc.nextInt();
        System.out.println(getMaxResult(number,pArray,max));
    }

    public static int getMaxResult(int n,int[] pArray,int max){
        int[][] dp = new int[n + 1][max+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= max; j++) {
                if(i == 0|| j == 0) {
                    continue;
                }
                if(pArray[i-1] > j){
                    dp[i][j] = dp[i-1][j];
                } else{
                    dp[i][j] = Math.max(dp[i-1][j],pArray[i-1] + dp[i-1][j-pArray[i-1]]);
                }
            }
        }
        return dp[n][max];
    }
}
