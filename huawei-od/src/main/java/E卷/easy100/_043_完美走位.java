package E卷.easy100;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/12 23:25
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _043_完美走位 {

    /**
     *题目描述
     * 在第一人称射击游戏中，玩家通过键盘的A、S、D、W四个按键控制游戏人物分别向左、向后、向右、向前进行移动，从而完成走位。
     * 假设玩家每按动一次键盘，游戏任务会向某个方向移动一步，如果玩家在操作一定次数的键盘并且各个方向的步数相同时，
     * 此时游戏任务必定会回到原点，则称此次走位为完美走位。
     *
     * 现给定玩家的走位（例如：ASDA），请通过更换其中一段连续走位的方式使得原走位能够变成一个完美走位。其中待更换的连续走位可以是相同长度的任何走位。
     * 请返回待更换的连续走位的最小可能长度。
     * 如果原走位本身是一个完美走位，则返回0。
     * 输入描述
     * 输入为由键盘字母表示的走位s，例如：ASDA
     *
     * 说明：
     * ​ 1、走位长度 1 ≤ s.length ≤ 100000（也就是长度不一定是偶数）
     * ​ 2、s.length 是 4 的倍数
     * ​ 3、s中只含有’A’, ‘S’, ‘D’, ‘W’ 四种字符
     *
     * 输出描述
     * 输出为待更换的连续走位的最小可能长度。
     *
     * 示例1
     * 输入
     * WASDAASD
     * 输出
     * 1
     * 说明
     *
     * 将第二个A替换为W，即可得到完美走位
     *
     * 示例2
     * 输入
     * AAAA
     * 输出
     * 3
     * 说明
     *
     * 将其中三个连续的A替换为WSD，即可得到完美走位
     *
     * 解题思路## 分析
     * 完美走位是上下左右的次数一样的。如果不一样，我们需要在给定的走位中，找到一个连续的字串，替换走位，是的走位完美。
     *
     * 题目要求，保持W,A,S,D字母个数平衡，即相等，如果不相等，可以从字符串中选取一段连续子串替换，来让字符串平衡。
     *
     * 比如：WWWWAAAASSSS
     *
     * 字符串长度12，W,A,S,D平衡的话，则每个字母个数应该是3个，而现在W,A,S各有4个，也就是说各超了1个。
     *
     * 因此我们应该从字符串中，选取一段包含1个W，1个A，1个S的子串，来替换为D。
     *
     * WWWWAAAASSSS
     *
     * 而符合这种要求的子串可能很多，我们需要找出其中最短的，即**WAAAAS**。
     *
     * 代码思路
     * 首先，我们需要统计输入字符串中每个字符的出现次数。这可以通过遍历字符串并使用一个哈希表（字典）来实现。
     *
     * 然后，我们需要确定正整数序列的起始值。我们可以通过计算字符串长度除以序列长度来得到每个正整数的平均长度。然后，我们可以从10的（平均长度-1）次方开始，作为起始值。
     * 接下来，我们需要使用滑动窗口的方法来寻找还原后的连续正整数序列。我们可以初始化两个指针，分别表示滑动窗口的左边界和右边界。同时，我们需要一个临时哈希表（字典）来存储当前滑动窗口内的字符出现次数。
     * 在滑动窗口的过程中，我们需要不断更新左右边界的字符出现次数，并检查当前滑动窗口内的字符出现次数是否与输入字符串的字符出现次数匹配。如果匹配，说明我们找到了一个可能的连续正整数序列，此时我们可以更新结果变量。
     * 当右边界超过字符串长度时，滑动窗口的过程结束。此时，结果变量中存储的就是还原后的连续正整数序列中的最小数字。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            System.out.println(getMinReplaceNumber(str));
        }
    }

    /**
     * 获取最小替换次数
     * @param str str中只含有’A’, ‘S’, ‘D’, ‘W’ 四种字符
     * @return
     */
    public static int getMinReplaceNumber(String str) {
        if (str.length() % 4 != 0) {
            return -1;
        }

        HashMap<Character, Integer> directionCountMap = new HashMap<>();
        directionCountMap.put('W', 0);
        directionCountMap.put('A', 0);
        directionCountMap.put('S', 0);
        directionCountMap.put('D', 0);
        for (int i = 0; i < str.length(); i++) {
            directionCountMap.put(str.charAt(i), directionCountMap.get(str.charAt(i)) + 1);
        }
        int start = 0;
        int end = 0;
        directionCountMap.put(str.charAt(0), directionCountMap.get(str.charAt(0))-1);
        int minReplaceNum = str.length();

        while (true) {
            int maxLength = 0;
            for (int count : directionCountMap.values()) {
                maxLength = Math.max(maxLength, count);
            }
            int windowLength = end - start + 1;
            int replaceNum = windowLength;
            for (int count : directionCountMap.values()) {
                replaceNum -= maxLength - count;
            }
            if (replaceNum >= 0 && replaceNum % 4 == 0) {
                minReplaceNum = Math.min(minReplaceNum, windowLength);
                if (start < str.length()) {
                    directionCountMap.put(str.charAt(start), directionCountMap.get(str.charAt(start)) + 1);
                    start++;
                } else {
                    break;
                }

            } else {
                end++;
                if (end < str.length()) {
                    directionCountMap.put(str.charAt(end), directionCountMap.getOrDefault(str.charAt(end), 0) - 1);
                } else {
                    break;
                }
            }
        }
        return minReplaceNum;
    }
}
