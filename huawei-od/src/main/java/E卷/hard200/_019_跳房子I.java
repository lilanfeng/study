package E卷.hard200;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/15 16:21
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _019_跳房子I {
    /**
     * 题目描述
     * 跳房子，也叫跳飞机，是一种世界性的儿童游戏。
     * 游戏参与者需要分多个回合按顺序跳到第1格直到房子的最后一格。
     * 跳房子的过程中，可以向前跳，也可以向后跳。
     * 假设房子的总格数是count，小红每回合可能连续跳的步教都放在数组steps中，请问数组中是否有一种步数的组合，可以让小红两个回合跳到量后一格?
     * 如果有，请输出索引和最小的步数组合。
     *
     * 注意：
     *
     * 数组中的步数可以重复，但数组中的元素不能重复使用。
     * 提供的数据保证存在满足题目要求的组合，且索引和最小的步数组合是唯一的。
     * 输入描述
     * 第一行输入为每回合可能连续跳的步数，它是int整数数组类型。
     * 第二行输入为房子总格数count，它是int整数类型。
     * 备注
     * count ≤ 1000
     * 0 ≤ steps.length ≤ 5000
     * -100000000 ≤ steps ≤ 100000000
     * 输出描述
     * 返回索引和最小的满足要求的步数组合（顺序保持steps中原有顺序）
     *
     * 示例1
     * 输入
     * [1,4,5,2,2]
     * 7
     * 输出
     * [5, 2]
     * 说明
     *
     * 示例2
     * 输入
     * [-1,2,4,9,6]
     * 8
     * 输出
     * [-1, 9]
     * 说明
     *
     * 此样例有多种组合满足两回合跳到最后，譬如：[-1,9]，[2,6]，其中[-1,9]的索引和为0+3=3，[2,6]的索和为1+4=5，所以索引和最小的步数组合[-1,9]
     *
     * 解题思路
     * 题意
     * 这道题目要求从一个给定的步数数组中找到一个步数组合，使得小红能够通过两次跳跃从第1格跳到第count格，并且这个组合在原数组中的索引和是最小的。输出是该步数组合中的两个步数，顺序保持与steps数组中的顺序一致。
     * 再说的明白一点，在steps数组中选两个数，使其之和等于count，并且这两个数在原数组中的索引和是最小的
     *
     * 与下面这题基本一致：
     *
     * https://leetcode.cn/problems/two-sum/description/
     *
     * 示例解释
     * 示例1：
     * 输入 [1, 4, 5, 2, 2] 和 7：
     * 一共有7个格子。步数组合中 [5, 2]（5 + 2 =7） 是满足条件的组合。
     * 示例2：
     * 输入 [-1, 2, 4, 9, 6] 和 8：
     * 一共有8个格子。步数组合中 [-1, 9] 和 [2, 6] 都满足条件。
     * 但是，[-1, 9]的索引和为0 + 3 = 3，而 [2, 6]的索引和为1 + 4 = 5，所以选择 [-1, 9]。
     */
    public static void main(String[] args) {
        int[] steps = {1,4,5,2,2};
        int count = 7;
        int[] result = findSteps(steps, count);
        System.out.println(result[0] + " " + result[1]);
    }

    /**
     * 寻找满足条件的步数组合 选择两个步数满足目标和count，并且索引和最小
     * @param steps
     * @param count
     * @return
     */
    public static int[] findSteps(int[] steps, int count) {
        int minIndexSum = Integer.MAX_VALUE;
        int[] result = new int[2];
        for (int i = 0; i < steps.length; i++) {
            for (int j = i + 1; j < steps.length; j++) {
                if (steps[i] + steps[j] == count) {
                    if (i + j < minIndexSum) {
                        minIndexSum = i + j;
                        result[0] = steps[i];
                        result[1] = steps[j];
                    }
                }
            }
        }
        return result;
    }

}
