package E卷.easy100;

import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _012_分披萨 {
    /**
     * 题目描述
     * "吃货"和"馋嘴"两人到披萨店点了一份铁盘（圆形）披萨，并嘱咐店员将披萨按放射状切成大小相同的偶数个小块。但是粗心的服务员将披萨切成了每块大小都完全不同奇数块，且肉眼能分辨出大小。
     * 由于两人都想吃到最多的披萨，他们商量了一个他们认为公平的分法：从"吃货"开始，轮流取披萨。除了第一块披萨可以任意选取外，其他都必须从缺口开始选。
     * 他俩选披萨的思路不同。"馋嘴"每次都会选最大块的披萨，而且"吃货"知道"馋嘴"的想法。
     * 已知披萨小块的数量以及每块的大小，求"吃货"能分得的最大的披萨大小的总和。
     * 输入描述
     * 第 1 行为一个正整数奇数 N，表示披萨小块数量。
     * 3 ≤ N < 500
     * 接下来的第 2 行到第 N + 1 行（共 N 行），每行为一个正整数，表示第 i 块披萨的大小
     * 1 ≤ i ≤ N
     * 披萨小块从某一块开始，按照一个方向次序顺序编号为 1 ~ N
     * 每块披萨的大小范围为 [1, 2147483647]
     * 输出描述
     * "吃货"能分得到的最大的披萨大小的总和。
     * 用例
     * 输入
     * 5
     * 8
     * 2
     * 10
     * 5
     * 7
     * 输出
     * 19
     * 说明：
     *
     * 此例子中，有 5 块披萨。每块大小依次为 8、2、10、5、7。
     * 按照如下顺序拿披萨，可以使"吃货"拿到最多披萨：
     * “吃货” 拿大小为 10 的披萨
     * “馋嘴” 拿大小为 5 的披萨
     * “吃货” 拿大小为 7 的披萨
     * “馋嘴” 拿大小为 8 的披萨
     * “吃货” 拿大小为 2 的披萨
     * 至此，披萨瓜分完毕，"吃货"拿到的披萨总大小为 10 + 7 + 2 = 19
     * 可能存在多种拿法，以上只是其中一种。
     *
     * 解题思路
     * 给定一个环形排列的披萨数组，每块披萨有一个美味值，需要计算出从任意位置开始，能够获得的最大美味值总和。
     * 环形处理：由于披萨是环形排列的，所以在选择披萨时需要考虑边界情况，即当选择了最左边或最右边的披萨后，如何循环到另一端。
     * 动态规划：使用一个二维数组 dp 作为记忆化存储，其中 dp[L][R] 表示从左边界 L 到右边界 R 能够获得的最大美味值。如果 dp[L][R] 已经被计算过，则直接返回该值。
     * 递归计算：定义一个递归函数来计算 dp[L][R]。如果 a[L]（左边界的披萨美味值）大于 a[R]（右边界的披萨美味值），则选择 L 并将 L 向右移动一位；否则选择 R 并将 R
     * 向左移动一位。这样递归地选择下一步，直到只剩下一块披萨。
     *
     * 递归基：当左右边界相遇时（即 L == R），说明只剩下一块披萨，直接返回这块披萨的美味值作为递归基。
     *
     * 状态转移：在递归过程中，dp[L][R] 的值是通过比较选择左边界披萨和右边界披萨后，剩下披萨的最大美味值之和来确定的。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            int n = Integer.parseInt(scanner.nextLine());
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(scanner.nextLine());
            }
            int[][] dp = new int[n][n];
            // 初始化 dp 数组
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j]= -1;
                }
            }
            int max = 0;
            for (int i = 0; i < n; i++) {
                max = Math.max(max, dfs(a, (i+1) % n, (i+n -1) % n, dp) + a[i]);
            }
            System.out.println(max);
        }
    }

    /**
     * 递归函数
     * @param a
     * @param left
     * @param right
     * @param dp
     * @return
     */
    public static int dfs(int[] a, int left, int right, int[][] dp) {
        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        // 边界条件
        if (a[left] > a[right]) {
            // 选择左边界
            left = (left + 1) % a.length;
        } else {
            // 选择右边界
            right = (right + a.length - 1) % a.length;
        }

        if (left == right) {
            // 只有一块披萨，直接返回 its value
            dp[left][right] = a[left];
        }else {
            // 否则，递归计算左右边界的披萨美味值之和，取较大值
            dp[left][right] = Math.max(a[left] + dfs(a, (left+1)%a.length, right , dp), a[right] + dfs(a, left , (right + a.length -1) %a.length, dp));
        }
        return dp[left][right];
    }
}
