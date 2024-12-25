package E卷.easy100;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _011_分割数组的最大差值 {

    /**
     * 题目描述
     * 给定一个由若干整数组成的数组nums ，可以在数组内的任意位置进行分割，将该数组分割成两个非空子数组（即左数组和右数组），
     * 分别对子数组求和得到两个值，计算这两个值的差值，请输出所有分割方案中，差值最大的值。
     * 输入描述
     * 第一行输入数组中元素个数n，1 < n ≤ 100000
     * 第二行输入数字序列，以空格进行分隔，数字取值为4字节整数
     *
     * 输出描述
     * 输出差值的最大取值
     *
     * 示例1
     * 输入
     *
     * 6
     * 1 -2 3 4 -9 7
     * 输出
     * 10
     * 说明
     *
     * 将数组 nums 划分为两个非空数组的可行方案有:
     *
     * 左数组 = [1] 且 右数组 = [-2,3,4,-9,7]，和的差值 = | 1 - 3 | = 2
     * 左数组 = [1,-2] 且 右数组 = [3,4,-9,7]，和的差值 = | -1 - 5 | =6
     * 左数组 = [1,-2,3] 且 右数组 = [4,-9,7]，和的差值 = | 2 - 2 | = 0
     * 左数组 = [1,-2,3,4] 且右数组=[-9,7]，和的差值 = | 6 - (-2) | = 8，
     *
     * 解题思路
     * 首先，定义两个变量leftSum和rightSum，分别表示左数组的和和右数组的和。初始化时，leftSum为0，rightSum为整个数组的和。
     *
     * 然后，定义一个变量maxDiff，表示差值的最大取值，初始化为0。
     *
     * 接下来，使用一个循环遍历数组，从第一个元素开始到倒数第二个元素。在每次循环中，更新leftSum和rightSum的值，并计算当前分割方案的差值。
     *
     * 具体的步骤如下：
     *
     * 将当前元素加到leftSum中。
     * 将当前元素从rightSum中减去。
     * 计算当前分割方案的差值，即Math.abs(leftSum - rightSum)。
     * 更新maxDiff的值，取当前差值和maxDiff中的较大值。
     * 最后，输出maxDiff的值。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int n = Integer.parseInt(scanner.nextLine());
            int[] nums = new int[n];
            nums = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int maxDiff = findMaxDiff(nums);
            System.out.println(maxDiff);
        }
    }

    public static int findMaxDiff(int[] nums) {
        int leftSum = 0;
        int rightSum = 0;
        for (int num : nums) {
            rightSum += num;
        }

        int maxDiff = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            leftSum += nums[i];
            rightSum -= nums[i];
            int diff = Math.abs(leftSum - rightSum);
            maxDiff =Math.max(maxDiff, diff);
        }

        return maxDiff;
    }
}
