package E卷.easy100;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/13 15:37
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _046_星际篮球争霸赛MVP争夺战 {
    /**
     * 题目描述
     * 在星球争霸篮球赛对抗赛中，最大的宇宙战队希望每个人都能拿到 MVP，MVP 的条件是单场最高分得分获得者。
     * 可以并列所以宇宙战队决定在比赛中尽可能让更多队员上场，并且让所有得分的选手得分都相同，然而比赛过程中的每一分钟的得分都只能由某一个人包揽。
     *
     * 输入描述
     * 输入第一行为一个数字 t ，表示为有得分的分钟数
     * 1 ≤ t ≤ 50
     * 第二行为 t 个数字，代表每一分钟的得分 p
     * 1 ≤ p ≤ 50
     * 输出描述
     * 输出有得分的队员都是 MVP 时，最少得 MVP 得分。
     *
     * 示例1
     * 输入
     * 9
     * 5 2 1 5 2 1 5 2 1
     * 输出
     * 6
     * 说明
     *
     * 一共 4 人得分，分别都是 6 分
     *  5 + 1 ， 5 + 1 ， 5 + 1 ， 2 + 2 + 2
     * 解题思路
     * 本题是可以归纳到：将数组划分为k个和相等的子集
     *
     * 可以在lettcode找到最原始的问题：698. 划分为k个相等的子集 - 力扣（LeetCode）
     *
     * 分析
     * 首先第一个目标，将数组拆分，每个子数组的和相等。
     * 比如[2,2,4] 拆分为[2,2] [4]
     * 然后在所有的可能拆分条件下，子数组的和最小。
     * 比如 [1,1,1,1] 可以拆分为[1] [1] [1] [1] 或 [1,1] [1,1]
     * 明显最小的子数组元素之和是1.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int n = Integer.parseInt(scanner.nextLine());
            int[] scoreArr = new int[n];
            scoreArr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(getMinScore(scoreArr));
        }
        scanner.close();

    }

    /**
     * 输入：得分数组
     * @param scoreArr
     * @return
     */
    public static int getMinScore(int[] scoreArr) {

        int number = scoreArr.length;
        int sum = Arrays.stream(scoreArr).sum();
        for(int i = number; i > 0; i--){
            if(sum % i != 0){
                continue;
            }
            int average = sum / i;
            Arrays.sort(scoreArr);
            //最大得分大于 平均分 则跳过
            if(scoreArr[number-1] > average){
                continue;
            }
            // TODO使用动态规划判断是否可以分成i个子集
            int k = scoreArr.length;
            int group = 1 << k;
            boolean[] dp = new boolean[group];
            int[] currentSum = new int[group];
            dp[0] = true;
            for(int m = 0; m < (1<<k); m++){
                if(!dp[m]){
                    continue;
                }
                for(int j = 0; j < k; j++){
                    if((m >> j & 1) != 0){
                        // 已经被选中
                        continue;
                    }

                    //如果当前分组的分组和加上当前元素后大于平均值，则跳过
                    if(currentSum[m] + scoreArr[j] > average){
                        break;
                    }
                    //将当前元素加入 新的分组
                    int newM = m | (1 << j);
                    if(!dp[newM]){
                        currentSum[newM] = (currentSum[m] + scoreArr[j]) % average;
                        dp[newM] = true;
                    }

                }
            }
            if (dp[group - 1]) {
                return average;
            }
        }
        return sum;
    }

}
