package 动态规划;

import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description 打家劫舍
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _0198_打家劫舍 {
    /**
     * https://leetcode.cn/problems/house-robber/description/
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * 示例 1：
     *
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 2：
     *
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 400
     */

    public static void main(String[] args) {
        //int[] nums = {2,7,9,3,1};
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String n = input.replace("[","").replace("]","");
        String[] split = n.split(",");
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        System.out.println(rob(nums));
    }

    /**
     * 思路： 动态规划 一维空间的动态规划
     * 1. 状态转移方程：dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1])
     * 2. 初始化：dp[0] = 0, dp[1] = nums[0]
     * 3. 遍历数组，计算dp[i]
     * @author lilanfeng2089，微信：lilanfeng2089
     * @date 2024/11/29
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        int n = nums.length;
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return nums[0];
        }
        if(n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        //int[] dp = new int[n];
        //dp[0] = nums[0];
        //dp[1] = Math.max(nums[0], nums[1]);
        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            //dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        //return dp[n-1];
        return second;
    }
}
