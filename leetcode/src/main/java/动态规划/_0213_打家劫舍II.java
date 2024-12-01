package 动态规划;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _0213_打家劫舍II {
    /**
     * https://leetcode.cn/problems/house-robber-ii/description/
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈
     * ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     * 示例 1：
     *
     * 输入：nums = [2,3,2]
     * 输出：3
     * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     * 示例 2：
     *
     * 输入：nums = [1,2,3,1,4]
     * 输出：7
     * 解释：你可以先偷窃 3 号房屋（金额 = 3），然后偷窃 5 号房屋（金额 = 7）。
     *      偷窃到的最高金额 = 3 + 4 = 7 。
     * 示例 3：
     *
     * 输入：nums = [1,2,3]
     * 输出：3
     * 提示：
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 1000
     */
    public static void main(String[] args) {
        int[] nums = {1,2,3,1,4};
        System.out.println(rob(nums));
    }

    /**
     * @date 2024/11/29
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) {
            return nums[0];
        }
        if(n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, n - 2), robRange(nums, 1, n - 1));
    }

    /**
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private static int robRange(int[] nums, int start, int end) {

        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}
