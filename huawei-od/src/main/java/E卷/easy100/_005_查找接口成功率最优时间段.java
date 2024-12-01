package E卷.easy100;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _005_查找接口成功率最优时间段 {
    /**
     * 题目描述
     * 服务之间交换的接口成功率作为服务调用关键质量特性，某个时间段内的接口失败率使用一个数组表示，
     *
     * 数组中每个元素都是单位时间内失败率数值，数组中的数值为0~100的整数，
     *
     * 给定一个数值(minAverageLost)表示某个时间段内平均失败率容忍值，即平均失败率小于等于minAverageLost，
     *
     * 找出数组中最长时间段，如果未找到则直接返回NULL。
     *
     * 输入描述
     * 输入有两行内容，第一行为{minAverageLost}，第二行为{数组}，数组元素通过空格(” “)分隔，
     *
     * minAverageLost及数组中元素取值范围为0~100的整数，数组元素的个数不会超过100个。
     *
     * 输出描述
     * 找出平均值小于等于minAverageLost的最长时间段，输出数组下标对，格式{beginIndex}-{endIndx}(下标从0开始)，
     *
     * 如果同时存在多个最长时间段，则输出多个下标对且下标对之间使用空格(” “)拼接，多个下标对按下标从小到大排序。
     *
     * 用例
     * 输入	1
     * 0 1 2 3 4
     * 输出	0-2
     * 说明
     * 输入解释：minAverageLost=1，数组[0, 1, 2, 3, 4]
     *
     * 前3个元素的平均值为1，因此数组第一个至第三个数组下标，即0-2
     *
     * 输入	2
     * 0 0 100 2 2 99 0 2
     * 输出	0-1 3-4 6-7
     * 说明
     * 输入解释：minAverageLost=2，数组[0, 0, 100, 2, 2, 99, 0, 2]
     *
     * 通过计算小于等于2的最长时间段为：
     *
     * 数组下标为0-1即[0, 0]，数组下标为3-4即[2, 2]，数组下标为6-7即[0, 2]，这三个部分都满足平均值小于等于2的要求，
     *
     * 因此输出0-1 3-4 6-7
     *
     * 题目解析
     * 解题思路如下：
     *
     * 首先，我们需要读取输入的数据，包括容忍的平均失败率和失败率数组。
     *
     * 然后，我们创建一个累积和数组，用于快速计算任意时间段的失败率总和。这个数组的每个元素都是从数组开始到当前位置的失败率的总和。
     *
     * 接下来，我们遍历所有可能的时间段，包括所有可能的开始和结束索引。对于每个时间段，我们计算其失败率总和，然后计算其平均失败率。我们可以通过查找累积和数组来快速计算失败率总和。
     *
     * 对于每个时间段，我们检查其平均失败率是否小于等于容忍的平均失败率。如果是，我们就找到了一个满足条件的时间段。
     *
     * 我们需要找到最长的满足条件的时间段。因此，我们需要跟踪找到的最长时间段的长度。如果我们找到一个比当前最长时间段更长的时间段，我们就更新最长时间段的长度，并清空结果列表，然后将新的时间段添加到结果列表中。如果我们找到一个和当前最长时间段一样长的时间段，我们就将它添加到结果列表中。
     *
     * 最后，我们检查结果列表。如果结果列表为空，说明我们没有找到任何满足条件的时间段，我们就输出"NULL"。否则，我们输出所有满足条件的时间段。如果有多个时间段，我们需要按照开始索引从小到大的顺序输出。
     *
     * 这个解题思路的关键是使用累积和数组来快速计算任意时间段的失败率总和，以及使用一个结果列表来跟踪所有满足条件的时间段。这样，我们可以在一次遍历中找到所有满足条件的时间段，并且可以快速找到最长的时间段。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int minAverageLost = Integer.parseInt(scanner.nextLine());
            String str = scanner.nextLine();
            String[] split = str.split(" ");
            int[] arr = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                arr[i] = Integer.parseInt(split[i]);
            }
            String result = findLongestSequence(arr, minAverageLost);
            System.out.println(result);
        }
    }

    /**
     *  快慢指针计算中间平均值
     * @param arr
     * @param minAverageLost
     * @return
     */
    public static String findLongestSequence(int[] arr, int minAverageLost) {
        //累计和的数组
        int[] sumArr = new int[arr.length];
        sumArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i];
        }

        StringJoiner sj = new StringJoiner(" ");
        int maxLength = 0;
        ArrayList<Integer[]> list = new ArrayList<>();
        for (int left = 0; left < arr.length; left++) {

            for (int right = left; right < arr.length; right++) {
                int sum = left == 0 ? sumArr[right] : sumArr[right] - sumArr[left - 1];
                int length = right - left + 1;
                int averageSumMax = length * minAverageLost;
                if (sum <= averageSumMax) {

                    if (length > maxLength) {
                        //找到更大的区间 需要清空以前数据
                        list = new ArrayList<>();
                        list.add(new Integer[]{left, right});
                        maxLength = length;
                    } else if (length == maxLength) {
                        list.add(new Integer[]{left, right});
                    }
                }
            }
        }

        if (list.isEmpty()) {
            return "NULL";
        } else {
            list.sort((o1, o2) -> o1[0] - o2[0]);
            for (Integer[] integers : list) {
                sj.add(integers[0] + "-" + integers[1]);
            }
        }
        return sj.toString();
    }
}
