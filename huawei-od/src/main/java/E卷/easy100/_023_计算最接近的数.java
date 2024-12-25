package E卷.easy100;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/5 15:31
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _023_计算最接近的数 {
    /**
     * 题目描述
     * 给定一个数组X和正整数K，请找出使表达式X[i] - x[i +1] … - X[i + K 1]，结果最接近于数组中位数的下标i，如果有多个i满足条件，请返回最大的i。
     * 其中，数组中位数:长度为N的数组，按照元素的值大小升序排列后，下标为N/2元素的值
     * 补充说明:
     * 数组X的元素均为正整数;
     * X的长度n取值范围: 2<= n <= 1000;
     * K大于0且小于数组的大小;
     * i的取值范围: 0 <=i < 1000;
     * 题目的排序数组X[N]的中位数是X[N/2].
     * 输入描述
     * 无
     *
     * 输出描述
     * 无
     *
     * 示例1
     * 输入
     * [50,50,2,3],2
     * 输出
     *  1
     * 说明
     *
     * 说明:
     * 1、中位数为50: [50,50,2,3]升序排序后变成[2,3,50,50]，中位数为下标4/2=2的元素50;
     * 2、计算结果为1: X[50,50,2,3]根据题目计算X[i] - …- X[i + K- 1]得出三个数
     * 0 (X[0]-X[1]= 50 -50) 、
     * 48 (X[1]-X[2] = 50 -2)
     * -1 (X[2]-X[3]= 2-3) ，
     * 其中48最接近50，因此返回下标1
     *
     * 解题思路
     * 这道题目要求我们找到一个数组中某个范围内的下标，使得该下标对应的计算结果最接近于数组的中位数。以下是题目的具体解释：
     *
     * 题目要点
     * 输入与输出:
     * 输入是一个数组 X 和一个正整数 K。
     * 输出是一个下标 i，该下标对应的计算结果最接近数组的中位数。
     * 中位数的定义:
     * 中位数是将数组元素按大小排序后，位于中间位置的元素。
     * 对于长度为 N 的数组，中位数是下标为 N/2 的元素（整除）。
     * 计算表达式:
     * 对于每个可能的下标 i，需要计算表达式：
     * result = X [ i ] − X [ i + 1 ] − . . . − X [ i + K − 1 ] \text{result} = X[i] - X[i+1] - ... - X[i+K-1]
     * result=X[i]−X[i+1]−...−X[i+K−1]
     * 这个表达式计算的是从下标 i 开始的 K 个元素的差值。
     * 寻找最接近中位数的结果:
     *
     * 对于每个计算出的结果，求出它与中位数的差的绝对值。
     * 找到差的绝对值最小的下标 i，如果有多个 i，则返回最大的 i。
     * 示例分析
     * 以示例 X = [50, 50, 2, 3] 和 K = 2 为例：
     *
     * 计算中位数:
     *
     * 排序后： [2, 3, 50, 50]，中位数为 50（下标为 2 的元素）。
     * 计算结果:
     *
     * 对于 i = 0：
     * result = X [ 0 ] − X [ 1 ] = 50 − 50 = 0 \text{result} = X[0] - X[1] = 50 - 50 = 0
     * result=X[0]−X[1]=50−50=0
     * 对于 i = 1：
     * result = X [ 1 ] − X [ 2 ] = 50 − 2 = 48 \text{result} = X[1] - X[2] = 50 - 2 = 48
     * result=X[1]−X[2]=50−2=48
     * 对于 i = 2：
     * result = X [ 2 ] − X [ 3 ] = 2 − 3 = − 1 \text{result} = X[2] - X[3] = 2 - 3 = -1
     * result=X[2]−X[3]=2−3=−1
     * 结果与中位数的比较:
     *
     * 计算结果 0、48、-1 与中位数 50 的差：
     * |0 - 50| = 50
     * |48 - 50| = 2
     * |-1 - 50| = 51
     * 48 是最接近 50 的，因此返回下标 1
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().replace("[","")
                .replace("]","").split(",");
        scanner.close();
        int[] nums = new int[input.length-1];
        int k = Integer.parseInt(input[input.length - 1]);
        for (int i = 0; i < input.length-1; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }
        System.out.println(findClosestIndex(nums, k));
    }

    public static int findClosestIndex(int[] nums, int k) {
        int[] sorted_nums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted_nums);
        // 计算中位数
        int median = sorted_nums[nums.length/2];

        // 初始化最小差值为最大整数
        int minDiff = Integer.MAX_VALUE;
        int result = -1;
        // 初始化结果为-1
        for (int i = 0; i <= nums.length - k; i++) {
            // 遍历数组
            int count = nums[i];
            // 初始化计数器为当前元素
            for(int j=i+1; j<i+k; j++){
                // 计算表达式X[i] - x[i +1] ... - X[i + K  1]
                count -= nums[j];
            }
            int diff = Math.abs(count - median);
            // 计算当前差值
            if(diff < minDiff){
                // 如果当前差值小于最小差值
                minDiff = diff;
                // 更新最小差值
                result = i;
                // 更新结果为当前下标
            } else if (diff == minDiff) {
                // 如果当前差值等于最小差值
                result = Math.max(result, i);
                // 更新结果为最大的下标
            }
        }
        return result;
    }
}
