package 全部;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _1456_定长子串中元音的最大数目 {

    /**
     * 1456. 定长子串中元音的最大数目
     * https://leetcode.cn/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/
     *
     * 给你字符串 s 和整数 k 。
     *
     * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
     *
     * 英文中的 元音字母 为（a, e, i, o, u）。

     * 示例 1：
     *
     * 输入：s = "abciiidef", k = 3
     * 输出：3
     * 解释：子字符串 "iii" 包含 3 个元音字母。
     * 示例 2：
     *
     * 输入：s = "aeiou", k = 2
     * 输出：2
     * 解释：任意长度为 2 的子字符串都包含 2 个元音字母。
     * 示例 3：
     *
     * 输入：s = "leetcode", k = 3
     * 输出：2
     * 解释："lee"、"eet" 和 "ode" 都包含 2 个元音字母。
     * 示例 4：
     *
     * 输入：s = "rhythms", k = 4
     * 输出：0
     * 解释：字符串 s 中不含任何元音字母。
     * 示例 5：
     *
     * 输入：s = "tryhard", k = 4
     * 输出：1

     * 提示：
     *
     * 1 <= s.length <= 10^5
     * s 由小写英文字母组成
     * 1 <= k <= s.length
     *
     */

    private static final HashSet<String> vowelsSet = new HashSet<String>(){
        { add("a"); add("e"); add("i"); add("o");add("u");}
    };
    /**
     * @author lilanfeng2089，微信：lilanfeng2089
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String split = scanner.nextLine();
            int k = Integer.parseInt(scanner.nextLine());
            System.out.println("输出为："+new _1456_定长子串中元音的最大数目().maxVowels(split, k));
        }
    }

    /**
     *  滑动窗口方式解决处理
     * @param s
     * @param k
     * @return
     */
    public int maxVowels(String s, int k) {
        int max = 0;
        for (int i = 0; i < k; i++) {
            max += isVowel(s.charAt(i));
        }
        int resultCount = max;
        for (int i = k; i < s.length(); i++) {
            max = max - isVowel(s.charAt(i - k)) + isVowel(s.charAt(i));
            resultCount = Math.max(resultCount, max);
            if (resultCount == k) {
                return resultCount;
            }
        }
        return resultCount;
    }

    private int isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ? 1 : 0;
    }
}
