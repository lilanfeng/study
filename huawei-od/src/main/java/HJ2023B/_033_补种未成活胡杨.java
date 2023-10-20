package HJ2023B;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 题目描述
 * 近些年来，我国防沙治沙取得显著成果。某沙漠新种植 N 棵胡杨(编号 1-N)，排成一排 一个月后，有 M 棵胡杨未能成活
 * 现可补种胡杨 K 棵，请问如何补种 (只能补种，不能新种)，可以得到最多的连续胡杨
 * 树?
 * 输入描述
 * N 总种植数量，1 <= N <= 100000
 * M 未成活胡杨数量，M 个空格分隔的数，按编号从小到大排列，1 <= M = N K 最多可以补种的数量，0 <= K <= M
 * 输出描述
 * 最多的连续胡杨棵树
 * 用例
 *  输入：5
 *       2
 *       2 4
 *       1
 *  输出：3
 *  说明
 *
 *
 *  输入：10
 *       3
 *       2 4 7
 *       1
 *   输出：6
 *
 *
 *
 */
public class _033_补种未成活胡杨 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int aliveCount = n - m;
        int[] aliveArr = new int[aliveCount];
        for (int i = 0; i < aliveCount ;i++) {
            aliveArr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        solutionAliveCount(aliveArr,n,m,k);
    }

    public static void solutionAliveCount(int[] aliveArr,int n,int m,int k){

        int[] totalArr = new int[n];
        Arrays.fill(totalArr,1);
        for (int i = 0; i < aliveArr.length; i++) {
            totalArr[aliveArr[i]] = 0;
        }

        int left = 0;
        LinkedList<Integer> usedList = new LinkedList<>();
        int maxLen = 0;
        for (int right = 0; right < totalArr.length; right++) {
            if(totalArr[right] == 0){
                usedList.addLast(right);
                if(usedList.size() > k){
                    maxLen = Math.max(maxLen,right - left);
                    left = usedList.removeFirst() + 1;
                    continue;
                }
            }
            maxLen = Math.max(maxLen,right - left + 1);
        }

        System.out.println(maxLen);
    }


}
