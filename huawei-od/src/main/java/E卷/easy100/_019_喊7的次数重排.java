package E卷.easy100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/4 05:57
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _019_喊7的次数重排 {
    /**
     * 题目描述
     * 喊7是一个传统的聚会游戏，N个人围成一圈，按顺时针从1到N编号。
     * 编号为1的人从1开始喊数，下一个人喊的数字为上一个人的数字加1，但是当将要喊出来的数字是7的倍数或者数字本身含有7的话，不能把这个数字直接喊出来，而是要喊”过”。
     * 假定玩这个游戏的N个人都没有失误地在正确的时机喊了”过”，当喊到数字K时，可以统计每个人喊”过”的次数。
     * 现给定一个长度为N的数组，存储了打乱顺序的每个人喊”过”的次数，请把它还原成正确的顺序，即数组的第i个元素存储编号i的人喊”过”的次数。
     * 输入描述
     * 输入为一行，为空格分隔的喊”过”的次数，注意K并不提供，K不超过200，而数字的个数即为N。
     * 输出描述
     * 输出为一行，为顺序正确的喊”过”的次数，也由空格分隔。
     * 示例1
     * 输入
     * 0 1 0
     * 输出
     * 1 0 0
     * 说明
     * 一共只有一次喊”过”，那只会发生在需要喊7时，按顺序，编号为1的人会遇到7，故输出1 0 0。
     * 示例2
     * 输入
     * 0 2 0 1 0
     * 输出
     * 0 2 0 1 0
     * 说明
     *
     * 一共有三次喊”过”，发生在7 14 17，按顺序，编号为2的人会遇到7 17，编号为4的人会遇到14，故输出0 2 0 1 0。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] split = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        scanner.close();
        System.out.println(restore(split));

    }

    /**
     * 思路：
     * 1. 遍历数组，计算有多少个喊过的次数。
     * @param arr
     * @return
     */
    public static String restore(int[] arr) {
        int length = arr.length;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            count += arr[i];
        }
        int[] result = new int[length];
        // 计算每个位置的喊过次数
        int index = 0;
        int number = 1;
        while (count > index) {
            for (int i = 0; i < length; i++) {
                if (number % 7 == 0 || String.valueOf(number).contains("7")) {
                    index++;
                    result[i] ++;
                }
                number++;
            }
        }
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (int i = 0; i < length; i++) {
            stringJoiner.add(String.valueOf(result[i]));
        }

        return stringJoiner.toString();
    }
}
