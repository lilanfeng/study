package E卷.easy100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/11 05:24
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _039_数组组成的最小数字 {
    /**
     *  题目描述
     * 给定一个整型数组，请从该数组中选择3个元素组成最小数字并输出
     * （如果数组长度小于3，则选择数组中所有元素来组成最小数字）。
     * 输入描述
     * 一行用半角逗号分割的字符串记录的整型数组，0 < 数组长度 <= 100，0 < 整数的取值范围 <= 10000。
     * 输出描述
     * 由3个元素组成的最小数字，如果数组长度小于3，则选择数组中所有元素来组成最小数字。
     * 示例1
     * 输入
     * 21,30,62,5,31
     * 输出
     * 21305
     * 说明
     *
     * 数组长度超过3，需要选3个元素组成最小数字，21305由21,30,5三个元素组成的数字，为所有组合中最小的数字。
     *
     * 示例2
     * 输入
     * 5,21
     * 输出
     * 215
     * 说明
     * 数组长度小于3， 选择所有元素来组成最小值，215为最小值。
     * 解题思路
     * 1. 最小数字组合的核心思想：字典序排列
     * 我们要从给定的数字列表中选择三个数字，并将它们拼接成一个字符串，要求这个字符串尽可能小。
     * 直接按数值大小排序只能确保我们选择的是数值最小的数字，但这并不能保证拼接后的数字最小。原因是字符串拼接顺序对最终的结果有很大影响。
     * 例如，"30" 和 "3"，虽然数值上 "3" 小于 "30"，但拼接时，"303" 比 "330" 小。因此，不能简单依赖数值大小。
     * 字典序比较则是为了解决这个问题。通过将两个数字的所有可能拼接顺序进行比较，例如比较 a + b 和 b + a，来确定哪种拼接顺序能产生更小的数字。这种比较方式确保最终拼接的数字是最小的。
     *
     * 2. 两次排序保证了最优选择：
     * 第一步：按数值大小排序：
     *
     * 首先，我们需要确定要从哪些数字中选择三个。为了尽量选择数值小的数字，我们先按数值大小对输入的字符串数组进行排序。这样确保我们选取的三个数字在数值上是最小的。
     * 这一步骤减少了拼接组合的候选集，因为数值大的数字无论怎么拼接，都会比数值小的数字拼接出的结果大。
     * 第二步：字典序排序：
     *
     * 接下来，为了确保选择的数字在拼接后能产生最小的数字，我们使用字典序排序的策略。这种排序会比较 (a + b) 和 (b + a) 的字典顺序，选择拼接后形成的字符串较小的排列方式。
     * 通过这样比较字典序，可以确保数字的排列方式最优。
     * 3. 选择前3个数字的合理性：
     * 题目要求拼接三个数字来得到最小值。通过先对整个列表按数值大小排序，再取前3个数字，我们保证了选取的数字是数值上最小的，从而为之后的字典序拼接提供了最好的起点。
     *
     * 如果输入的数字不足3个，代码逻辑也能处理这种情况：会直接将所有的数字拼接在一起。对于这种情况，少于3个的数字直接拼接，字典序依然保证了最小的拼接结果。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getMinNumber(nums));
    }

    /**
     * 获取最小数字
     * @param nums
     * @return
     */
    public static String getMinNumber(int[] nums) {
        Arrays.sort(nums);
        int sortSize = Math.min(nums.length, 3);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < sortSize; i++) {
            list.add(String.valueOf(nums[i]));
        }

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1+o2).compareTo (o2+o1);
            }
        });
        // 如果数组长度小于3，拼接已经排好顺序的数组，拼接成最小的数字
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }
}
