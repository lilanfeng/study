package E卷.hard200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/7 19:27
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _007_荒岛求生 {
    /**
     * 题目描述
     * 一个荒岛上有若干人，岛上只有一条路通往岛屿两端的港口，大家需要逃往两端的港口才可逃生。
     * 假定每个人移动的速度一样，且只可选择向左或向右逃生。
     * 若两个人相遇，则进行决斗，战斗力强的能够活下来，并损失掉与对方相同的战斗力；若战斗力相同，则两人同归于尽。
     * 输入描述
     * 给定一行非 0 整数数组，元素个数不超过30000；
     * 正负表示逃生方向（正表示向右逃生，负表示向左逃生），绝对值表示战斗力，越左边的数字表示里左边港口越近，逃生方向相同的人永远不会发生决斗。
     * 输出描述
     * 能够逃生的人总数，没有人逃生输出0，输入异常时输出-1。
     * 示例1
     * 输入
     * 5 10 8 -8 -5
     * 输出
     * 2
     * 说明
     *
     * 第3个人和第4个人同归于尽，第2个人杀死第5个人并剩余5战斗力，第1个人没有遇到敌人。
     * 解题思路
     * 原题：[735. 行星碰撞](https://leetcode.cn/problems/asteroid-collision/)
     * 唯一的区别，就是本题会自减对方的战斗力。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] split = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        scanner.close();
        if (split.length > 30000){
            System.out.println("-1");
        }else {
            System.out.println(getAliveCount(split));
        }

    }
    public static int getAliveCount(int[] arr) {
       List<Integer> existList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int cur = arr[i];
            if (cur == 0) {
                return -1;
            }
            boolean isAlive = true;
            while (isAlive && cur < 0 && existList.size() > 0 && existList.get(existList.size() - 1) > 0) {
                int last = existList.get(existList.size() - 1);
                if (last < -cur) {
                    //左边 战斗力 小于  右边  1,去除左边存活的数据 2，更新当前战斗力数据
                    existList.remove(existList.size() - 1);
                    cur = cur + last;
                } if(last == -cur) {
                    //左边 战斗力 等于 右边
                    cur = 0;
                    existList.remove(existList.size() - 1);
                    isAlive = false;
                } else {
                    //左边 战斗力 大于于  右边
                    existList.set(existList.size() -1, last+cur);
                    isAlive = false;
                }
            }

            if (isAlive) {
                existList.add(cur);
            }

        }
       return existList.size();
    }
}
