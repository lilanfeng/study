package E卷.easy100;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/4 06:26
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _021_恢复数字序列 {
    /**
     * 恢复数字序列
     * 题目描述
     * 对于一个连续正整数组成的序列，可以将其拼接成一个字符串，再将字符串里的部分字符打乱顺序。如序列8 9 10 11 12，
     * 拼接成的字符串为89101112，打乱一部分字符后得到90811211，原来的正整数10就被拆成了0和1。
     * 现给定一个按如上规则得到的打乱字符的字符串，请将其还原成连续正整数序列，并输出序列中最小的数字。
     *
     * 输入描述
     * 输入一行，为打乱字符的字符串和正整数序列的长度，两者间用空格分隔，字符串长度不超过200，正整数不超过1000，保证输入可以还原成唯一序列。
     * 输出描述
     * 输出一个数字，为序列中最小的数字。
     *
     * 示例1
     * 输入
     * 19801211 5
     * 输出
     * 8
     * 说明
     * 示例2
     * 输入
     * 432111111111 4
     * 输出
     * 111
     * 说明
     *
     * 解题思路
     * 题目要求从一个打乱了部分字符的字符串中还原出一个连续的正整数序列，找出其中最小的数字。
     *
     * 题目流程和解释：
     * 输入内容：
     * 字符串部分：表示将某个连续正整数序列拼接后、打乱部分字符得到的字符串。
     * 序列长度部分：表示原来的连续正整数序列中有多少个数字。
     * 任务：
     * 根据打乱后的字符串和给定的序列长度，找出原来连续正整数序列的最小数字。
     * 注意：输入字符串是由原来的一些连续正整数组成的，但某些数字可能被拆分成了单个字符，且这些字符被打乱后可能不再连贯。因此，需要将这些打乱的字符重新组合成连续的正整数。
     * 关键点：
     * 字符打乱：原本连续的数字可能在字符串中被拆分并打乱顺序，因此需要将它们重新组合成完整的数字。
     * 唯一还原性：题目保证每一个输入都能还原成唯一的连续正整数序列，这意味着不存在多个可能的解。
     * 示例解析
     * 示例 1：
     * 输入：19801211 5
     * 输出：8
     * 解释：
     *
     * 打乱后的字符串是 19801211，序列长度是 5。
     * 还原成的连续正整数序列为 8 9 10 11 12，最小的数字是 8。
     * 示例 2：
     *
     * 输入：432111111111 4
     * 输出：111
     * 解释：
     *
     * 打乱后的字符串是 432111111111，序列长度是 4。
     * 还原成的连续正整数序列为 111 112 113 114，最小的数字是 111。
     * 总结
     * 题目的核心是通过排列组合字符找到能组成连续正整数序列的方案，并返回其中最小的数字。
     * 统计打乱字符的字符串中各字符的数量。然后遍历所有可能的连续整数序列，对于每个序列，检查序列中的字符数量是否与打乱字符的字符串中各字符数量一致。如果找到一个匹配的序列，输出序列中最小的数字并结束循环。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split(" ");
            String str = split[0];
            int n = Integer.parseInt(split[1]);
            int result = restore(str, n);
            System.out.println(result);
        }
    }

    /**
     * 恢复数字序列
     * @param str
     * @param n
     * @return
     */
    public static int restore(String str, int n) {

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 1; i <= 1000; i++) {
            HashMap<Character, Integer> tempMap = new HashMap<>();
            for (int j = i; j < i+n; j++) {
                String number = String.valueOf(j);
                for (int k = 0; k < number.length(); k++) {
                    char c = number.charAt(k);
                    tempMap.put(c, tempMap.getOrDefault(c, 0) + 1);
                }
            }
            boolean flag = true;
            for (Character key : map.keySet()) {
                if (!tempMap.containsKey(key) || tempMap.get(key) - map.get(key) != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }


}
