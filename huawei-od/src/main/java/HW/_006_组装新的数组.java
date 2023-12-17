package HW;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 *
 * 题目描述
 * 给你一个整数 M 和数组 N，N 中的元素为连续整数，要求根据 N 中的元素组装成新的 数组 R，组装规则:
 * 1.R 中元素总和加起来等于 M
 * 2.R 中的元素可以从 N 中重复选取
 * 3.R 中的元素最多只能有 1 个不在 N 中，且比 N 中的数字都要小 (不能为负数)
 * 输入描述
 * 第一行输入是连续数组 N，采用空格分隔
 * 第二行输入数字 M 输出描述
 * 输出的是组装办法数量，int 类型
 * 备注
 * 。1≤M≤30
 * 。1≤ N.length ≤ 1000 用例
 * 用例：
 *  输入：2
 *      5
 *   输出：1
 *  说明：只有2，2，1
 *
 *  输入：2 3
 *       5
 *
 *  输出：2
 *  说明： 2 2 1；2，3
 *
 * 题目解析
 *   我们可以换个说法来看本题，
 * 在数组 N 任意选取多个元素，且同一个元素可以重复选取，只要最终选取的所有元素 之和等于 m，或者:小于 m 但是差值不超过 N.min()。
 *   现在是不是感觉本题简单多了
 * 我们可以使用 回溯算法来在 N 中选取多个元素(同一个元素可以重复选取》，这个逻辑 其实就是求可重复元素组合情况，每得到一个组合就看其和 sum 是否等于 m，或者 m -sum 是否已经小于 N
 * .min，若是，则该组合是符合要求的，count++，否则不符合要求，继续找。 当 sum> m 时，则说明往后的组合已经无法符合要求，此时需要进行回溯了。
 */
public class _006_组装新的数组 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Integer[] arr = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        int m = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(arr, m));
    }

    public static int getResult(Integer[] arr, int m) {
        Integer[] newArr = Arrays.stream(arr).filter(val -> val <= m).toArray(Integer[]::new);
        int min = newArr[0];
        return dfs(newArr, 0, 0, min, m, 0);
    }

    /**
     *
     * @param arr
     * @param index
     * @param sum
     * @param min
     * @param m
     * @param count
     * @return
     */
    public static int dfs(Integer[] arr, int index, int sum, int min, int m, int count) {
        if (sum > m) {
            return count;
        }
        if (sum == m || (m - sum < min && m - sum > 0)) {
            return count + 1;
        }
        for (int i = index; i < arr.length; i++) {
            count = dfs(arr, i, sum + arr[i], min, m, count);
        }
        return count; }
}
