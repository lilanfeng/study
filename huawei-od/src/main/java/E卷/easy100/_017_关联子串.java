package E卷.easy100;

import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/3 06:26
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _017_关联子串 {
    /**
     * 题目描述
     * 给定两个字符串str1和str2，如果字符串str1中的字符，经过排列组合后的字符串中，只要有一个字符串是str2的子串，则认为str1是str2的关联子串。
     * 若str1是str2的关联子串，请返回子串在str2的起始位置；
     * 若不是关联子串，则返回-1。
     * 输入描述
     * 输入两个字符串，分别为题目中描述的str1、str2。
     *
     * 备注
     * 输入的字符串只包含小写字母；
     * 两个字符串的长度范围[1, 100000]之间；
     *
     * 输出描述
     * 若str1是str2的关联子串，请返回子串在str2的起始位置；
     *
     * 若不是关联子串，则返回-1。
     *
     * 若str2中有多个str1的组合子串，请返回最小的起始位置。
     *
     * 示例1
     * 输入
     * abc efghicbaiii
     * 输出
     * 5
     * 说明
     * str2包含str1的一种排列组合（“cab”)，此组合在str2的字符串起始位置为5（从0开始计数）
     * 示例2
     * 输入
     *
     * abc efghiccaiii
     * 输出
     * -1
     * 说明
     *
     * “abc”字符串中三个字母的各种组合（abc、acb、bac、bca、cab、cba），str2中均不包含，因此返回-1
     *
     * 解题思路
     * 给定两个字符串 str1 和 str2，需要判断 str1 的任意排列组合是否是 str2 的子串。如果是，则返回这个子串在 str2 中的起始位置；如果不是，则返回 -1。
     *
     * 排列组合：
     * str1 的排列组合是指 str1 的所有字符按不同顺序排列所形成的字符串。比如，如果 str1 = "abc"，它的排列组合包括 abc, acb, bac, bca, cab, cba 等。
     * 关联子串：
     * 如果 str2 包含 str1 的某个排列组合作为其子串，则称 str1 是 str2 的关联子串。例如：
     * 如果 str1 = "abc"，而 str2 = "efghicbaiii"，str2 包含 "cab" 这个子串（"cab" 是 str1 = "abc" 的一个排列组合），因此 str1 是 str2 的关联子串。
     * 起始位置：
     * 如果 str1 是 str2 的关联子串，需要返回这个排列组合在 str2 中的起始位置，从 0 开始计数。
     * 如果有多个排列组合出现在 str2 中，需要返回最小的起始位置。
     * 示例解析：
     * 示例1：
     * 输入：
     * abc efghicbaiii
     * 解释：
     * str1 = "abc"，它的排列组合包括 abc, acb, bac, bca, cab, cba。
     * str2 = "efghicbaiii" 中包含 "cab" 这个子串，它是 str1 的一个排列组合。
     * "cab" 的起始位置是 5，因此输出 5。
     * 输出：
     * 5
     * 总结：
     * 1. 问题核心：
     * 我们需要找到 str1 的某个排列是否是 str2 的子串。由于 str1 的所有排列组合数量是 n!，当字符串长度比较大时，生成和匹配所有的排列组合非常耗时。因此，直接生成所有排列并依次匹配不是一个高效的方法。
     * 2. 优化思路：
     * 通过字符频率统计的方法，将复杂的排列匹配问题转化为子串字符频率匹配问题。具体来说：
     * 两个字符串是排列关系，意味着它们的字符组成相同（即每个字符出现的次数相同，但顺序可能不同）。
     * 因此，只要找到 str2 中长度为 n1 的子串（n1 是 str1 的长度），其字符频率与 str1 的字符频率一致，那么这个子串就可以被认为是 str1 的一个排列组合。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] strs = line.split(" ");
            String str1 = strs[0];
            String str2 = strs[1];
            int result = getIndex(str1, str2);
            System.out.println(result);
        }
    }

    /**
     * 获取 str1 在 str2 中的起始位置
     * @param str1
     * @param str2
     * @return
     */
    public static int getIndex(String str1, String str2) {
        int index = -1;
        int n1 = str1.length();
        int n2 = str2.length();
        if(n2 < n1){
            return index;
        }
        // 统计 str1 中每个字符出现的次数
        for (int i = 0; i < n2; i++) {
            if(checkSubString(str1,str2,i,n1)){
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 检查 str2 中以 start 开始，长度为 length 的子串是否与 str1 的字符频率一致
     * @param str1
     * @param str2
     * @param start
     * @param length
     * @return
     */
    private static boolean checkSubString(String str1, String str2, int start, int length) {
        int count = 0;
        int[] appereance = new int[26];
        for (int i = start; i < start + length; i++) {
            int index = str2.charAt(i) - 'a';
            appereance[index]++;
        }
        for (int i = 0; i < str1.length(); i++) {
            int index = str1.charAt(i) - 'a';
            if (appereance[index] > 0) {
                count++;
                appereance[index]--;
            }
        }
        return count == str1.length();
    }

}
