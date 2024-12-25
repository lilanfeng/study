package E卷.hard200;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/7 19:12
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _006_观看文艺汇演问题 {
    /**
     * 题目描述
     * 为了庆祝中国共产党成立100周年，某公园将举行多场文艺表演，很多演出都是同时进行，一个人只能同时观看一场演出，且不能迟到早退，
     * 由于演出分布在不同的演出场地，所以连续观看的演出最少有15分钟的时间间隔，
     *
     * 小明是一个狂热的文艺迷，想观看尽可能多的演出， 现给出演出时间表，请帮小明计算他最多能观看几场演出。
     *
     * 输入描述
     * 第一行为一个数 N，表示演出场数
     * 1 ≤ N ≤ 1000
     * 接下来 N 行，每行有被空格分割的整数，第一个整数 T 表示演出的开始时间，第二个整数 L 表示演出的持续时间
     * T 和 L 的单位为分钟
     * 0 ≤ T ≤ 1440
     * 0 < L ≤ 180
     * 输出描述
     * 输出最多能观看的演出场数。
     * 示例1
     * 输入
     * 2
     * 720 120
     * 840 120
     * 输出
     * 1
     * 说明
     *
     * 示例2
     * 输入
     * 2
     * 0 60
     * 90 60
     * 输出
     * 2
     * 说明
     *
     * 解题思路
     * 题目理解：
     * 这道题目要求我们帮小明计算他最多可以观看的文艺演出场数，给定多个演出时间表，每个演出有开始时间和持续时间，但小明有两个限制条件：
     * 小明不能迟到或早退，意味着他必须完整观看演出。
     * 连续观看的演出之间至少需要有 15 分钟的间隔，因为演出场地分布在不同地方，前往下一个演出场地需要时间。
     * 这道题本质上是一个「区间调度问题」，需要我们在多个重叠的区间中，尽可能选择最多的不冲突区间。
     *
     * 示例解析：
     * 示例1：
     * 输入:
     * 2
     * 720 120
     * 840 120
     * 两场演出：
     * 第一场：720 分钟开始 ，持续 120 分钟，到 840 分钟结束 。
     * 第二场：840 分钟开始 ），持续 120 分钟，到 960 分钟结束 。
     * 问题是：小明观看完第一场演出是 2:00 PM，但第二场演出也是 2:00 PM 开始，所以没有足够的时间移动到下一个场地（需要至少 15 分钟的间隔）。因此，小明只能观看 1 场演出。
     * 示例2：
     * 输入:
     * 2
     * 0 60
     * 90 60
     * 两场演出：
     * 第一场：0 分钟开始（即 0:00 AM），持续 60 分钟，到 60 分钟结束（即 1:00 AM）。
     * 第二场：90 分钟开始（即 1:30 AM），持续 60 分钟，到 150 分钟结束（即 2:30 AM）。
     * 小明可以观看第一场演出结束后（1:00 AM），在 1:30 AM 前往下一个场地（刚好有 30 分钟的间隔）。因此，小明可以观看两场演出。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] split = scanner.nextLine().split(" ");
            arr[i][0] = Integer.parseInt(split[0]);
            arr[i][1] = Integer.parseInt(split[1]);
        }
        scanner.close();
        System.out.println(getMaxCount(arr));
    }

    /**
     * 获取最大观看场数
     * @param arr
     * @return
     */
    public static int getMaxCount(int[][] arr) {

        List<int[]> sortWatchList = new ArrayList<>();
        //1, 构建顺序观看时间表
        for (int[] ints : arr) {
            int startTime = ints[0];
            int endTime = ints[0] + ints[1];
            sortWatchList.add(new int[]{startTime,endTime});
        }
        //2, 排序
        sortWatchList.sort(Comparator.comparingInt(o -> o[0]));

        //3，遍历查找符合条件的观看时间
        int[] ints = sortWatchList.get(0);
        int watchEndTime = ints[1];
        int maxCount = 1;
        for (int j = 1; j < sortWatchList.size(); j++) {
            int[] ints1 = sortWatchList.get(j);
            int startTime = ints1[0];
            if (startTime >= watchEndTime + 15) {
                maxCount++;
                watchEndTime = ints1[0];
            }
        }
        return maxCount;
    }
}
