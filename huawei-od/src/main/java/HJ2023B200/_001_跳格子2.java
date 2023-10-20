package HJ2023B200;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  题目描述
 * 小明和朋友玩跳格子游戏，有 n 个连续格子组成的圆圈，每个格子有不同的分数，小朋友可以选择以任意格子起跳，但是不能跳连续的格子，不能回头跳，也不能超过一圈;
 * 给定一个代表每个格子得分的非负整数数组，计算能够得到的最高分数。
 *
 * 输入描述
 * 给定一个数例，第一个格子和最后一个格子首尾相连，如: 2 3 2
 *
 * 输出描述
 * 输出能够得到的最高分，如: 3
 *
 * 备注
 * 1 ≤ nums.length ≤ 100
 * 1 ≤ nums[i] ≤ 1000
 * 用例
 * 输入	2 3 2
 * 输出	3
 * 说明	只能跳3这个格子，因为第一个格子和第三个格子首尾相连
 * 输入	1 2 3 1
 * 输出	4
 * 说明	1 + 3 = 4
 * 解题思路
 * 在这个问题中，我们需要计算在不跳连续格子的情况下，能够得到的最高分数。
 *
 * 题目中的格子是首尾相连的，因此我们需要考虑两种情况：
 * 不包含最后一个元素的最大分数：在这种情况下，我们可以从第一个格子开始跳，但不能跳到最后一个格子。我们使用动态规划数组 dp
 * 来存储每个状态的最大分数。对于每个状态，我们可以选择跳过当前格子（保持前一个状态的最大分数）或跳到当前格子（前一个状态的最大分数加上当前格子的分数）。我们从第一个格子开始，直到倒数第二个格子，计算这种情况下的最大分数 max1。
 * 不包含第一个元素的最大分数：在这种情况下，我们不能从第一个格子开始跳，而是从第二个格子开始。我们同样使用动态规划数组 dp
 * 来存储每个状态的最大分数。对于每个状态，我们可以选择跳过当前格子（保持前一个状态的最大分数）或跳到当前格子（前一个状态的最大分数加上当前格子的分数）。我们从第二个格子开始，直到最后一个格子，计算这种情况下的最大分数 max2。
 * 最后，我们比较这两种情况下的最大分数，输出较大值作为结果。
 *
 * 之所以要分两种情况计算，是因为题目中的格子是首尾相连的，这意味着我们不能同时跳到第一个格子和最后一个格子。因此，我们需要分别计算不包含第一个元素和不包含最后一个元素的最大分数，然后取较大值作为结果。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/132378486
 */
public class _001_跳格子2 {
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
        for (int i = 2; i <  n-1; i++) {
           dp[i] = Math.max(dp[i-1],dp[i-2] + points[i]);
        }
        int max1 = dp[n-2];

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
