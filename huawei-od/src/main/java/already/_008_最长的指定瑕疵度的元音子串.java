package already;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2025/1/7 06:36
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _008_最长的指定瑕疵度的元音子串 {

    /**
     * 一、题目
     * 开头和结尾都是元音字母（aeiouAEIOU）的字符串为元音字符串，其中混杂的非元音字母数量为其瑕疵度。比如:
     *
     * “a”、“aa”是元音字符串，其瑕疵度都为0 “aiur”不是元音字符串（结尾不是元音字符） “abira”是元音字符串，其瑕疵度为2
     * 给定一个字符串，请找出指定瑕疵度的最长元音字符子串，并输出其长度，如果找不到满足条件的元音字符子串，输出0。
     *
     * 子串：字符串中任意个连续的字符组成的子序列称为该字符串的子串。
     *
     * 二、输入
     * 首行输入是一个整数，表示预期的瑕疵度flaw，取值范围[0, 65535]。
     *
     * 接下来一行是一个仅由字符a-z和A-Z组成的字符串，字符串长度(0, 65535]。
     *
     * 三、输出
     * 输出为一个整数，代表满足条件的元音字符子串的长度。
     *
     * 四、示例
     * 输入：
     * 0
     * asdbuiodevauufgh
     *
     * 输出：
     * 3
     * 五、题解
     * 本地很容易想到滑动窗口的解法。但是如何处理左右指针的移动和解的更新比较纠结
     * 对原字符串进行预处理，只记录元音字符出现的位置，针对新的位置数组做滑动窗口更高效一些。注意左右指针移动选择的原因是因为 postions[i]的变化比 i 的变化快
     * 针对原字符串，使用一个前缀数组记录当前位置元音字符的个数，在原字符串上做滑动窗口应该也是可以的，只是处理起来啰嗦一些
     */

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 0 输入瑕疵度
        int num = sc.nextInt();
        sc.nextLine();
        // asdbuiodevauufgh  输入字符串
        String str = sc.nextLine();
        // 3
        int ans = getMaxLengthWithConstraint(str, num);
        System.out.println(ans);
    }

    /**
     *  获取满足瑕疵度条件的最长元音子串长度
     * @param str
     * @param num
     * @return
     */
    private static int getMaxLengthWithConstraint(String str, int num) {
        // 元音字符集合
        Set<Character> vowelSet = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        // 记录所有元音字符出现的位置
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (vowelSet.contains(str.charAt(i))) {
                positions.add(i);
            }
        }

        int res = 0;
        // 注意：滑动窗口处理的是 元音字符位置数组，不是原字符串
        int n = positions.size();

        // 滑动窗口
        int left = 0;
        int right = 0;
        while (right < n) {
            // 瑕疵度计算, 满足条件的 子串长度 - 元音字符个数 （注意两个长度都比实际值小 1，所有都不用写了）
            int diff = positions.get(right) - positions.get(left) - (right - left);
            // positions[i]值的变化一定比 i 的变化大，所以 diff 一定是单调递增的
            // 保证下面left和right指针的移动是正确的
            if (diff > num) {
                left++;
            } else if (diff < num) {
                right++;
            } else {
                // 更新答案
                res = Math.max(res, positions.get(right) - positions.get(left) + 1);
                right++;
            }
        }
        return res;
    }
}
