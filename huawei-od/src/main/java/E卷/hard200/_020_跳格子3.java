package E卷.hard200;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/15 16:31
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _020_跳格子3 {

    /**
     * 题目描述
     * 小明和朋友们一起玩跳格子游戏，每个格子上有特定的分数 score = [1, -1, -6, 7, -17, 7]，
     * 从起点score[0]开始，每次最大的步长为k，请你返回小明跳到终点 score[n-1] 时，能得到的最大得分。
     *
     * 输入描述
     * 第一行输入总的格子数量 n
     * 第二行输入每个格子的分数 score[i]
     * 第三行输入最大跳的步长 k
     * 备注
     * 格子的总长度 n 和步长 k 的区间在 [1, 100000]
     * 每个格子的分数 score[i] 在 [-10000, 10000] 区间中
     * 输出描述
     * 输出最大得分
     * 示例1
     * 输入
     * 6
     * 1 -1 -6 7 -17 7
     * 2
     * 输出
     * 14
     * 说明
     * 解题思路
     * 在给定的跳格子游戏中，我们使用动态规划方法来计算每个格子可能达到的最大得分。动态规划的核心在于解决子问题并利用这些子问题的解来解决整个问题。
     *
     * 动态规划公式
     * 设 dp[i] 表示到达第 i 个格子时能得到的最大分数，则 dp[i] 可以通过以下方式计算：
     *
     * dp[i] = max(dp[j]) + score[i] for j in range(max(0, i-k), i)
     * 1
     * 这里，max(0, i-k) 到 i-1 表示从当前位置 i 往回看，最远可以从 i-k 跳到 i。如果 k 大于 i，则从 0 开始。换句话说，dp[i] 是当前格子的分数加上能跳到这个格子的最大分数。
     *
     * 使用双端队列优化
     * 因为 ( k ) 可能非常大，直接计算每个 ( dp[i] ) 需要 ( O(k) ) 的时间复杂度，总的时间复杂度是 ( O(nk) )，这可能非常耗时。为了优化这一过程，我们使用一个双端队列来维护 ( dp )
     * 值的索引，并且保持队列中的 ( dp ) 值是单调递减的，这样队列的首元素始终是最大值。
     *
     * 通过这种方法，每个元素最多只被队列添加和删除各一次，因此更新 ( dp ) 数组的过程的时间复杂度降低到 ( O(n) )。
     */

    /**
     * 输入：
     * 6
     * 1 -1 -6 7 -17 7
     * 2
     * 输出：
     * 14
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int n = Integer.parseInt(scanner.nextLine());
            int[] score = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int k = Integer.parseInt(scanner.nextLine());
            System.out.println(findMaxScore(n, score, k));
        }
        scanner.close();
    }

    /**
     * 跳格子
     * @param n
     * @param score
     * @param k
     * @return
     */
    public static int findMaxScore(int n, int[] score, int k) {
        if(n == 1){
            return score[0];
        }
        int[] dp = new int[n];
        dp[0] = score[0];

        Deque<Integer> deque = new LinkedList<>();
        deque.add(0);

        for (int i = 1; i < n; i++) {
            // 如果队列不为空且队列头部的索引已经超出了跳跃范围，从队列中移除头部
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }
            // 计算当前格子的最大分数：当前格子的分数加上可以跳到该格子的最大分数
            dp[i] = score[i] + ( deque.isEmpty() ? 0 : dp[deque.peekFirst()]);

            // 维护队列，保持队列为递减，新的最大值需要添加到队尾
            // 如果队列不为空且当前格子的分数大于队列尾部的分数，从队列中移除尾部
            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }
            // 将当前格子的索引加入队列
            deque.add(i);
        }
        return dp[n - 1];
    }
}
