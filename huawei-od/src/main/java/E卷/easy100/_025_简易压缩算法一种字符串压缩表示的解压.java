package E卷.easy100;

import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/5 16:27
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _025_简易压缩算法一种字符串压缩表示的解压 {
    /**
     * 压缩字符串
     * 题目描述
     * 有一种简易压缩算法：针对全部为小写英文字母组成的字符串， 将其中连续超过两个相同字母的部分压缩为连续个数加该字母 其他部分保持原样不变.
     * 例如字符串aaabbccccd 经过压缩变成字符串 3abb4cd
     * 请您编写解压函数,根据输入的字符串,判断其是否为合法压缩过的字符串
     * 若输入合法则输出解压缩后的字符串
     * 否则输出字符串!error来报告错误
     * 输入描述
     * 输入一行，为一个 ASCII 字符串
     * 长度不超过100字符
     * 用例保证输出的字符串长度也不会超过100字符串
     * 输出描述
     * 若判断输入为合法的经过压缩后的字符串
     * 则输出压缩前的字符串
     * 若输入不合法 则输出字符串!error
     * 示例1
     * 输入
     * 4dff
     * 输出
     * ddddff
     * 说明
     * 4d 扩展为 4 个 d ，故解压后的字符串为 ddddff
     *
     * 示例2
     * 输入
     * 2dff
     * 输出
     * !error
     * 说明
     * 2 个 d 不需要压缩 故输入不合法
     * 示例3
     * 输入
     * 4d@A
     * 输出
     * !error
     * 说明
     * 全部由小写英文字母做成的字符串，压缩后不会出现特殊字符@和大写字母 A
     * 故输入不合法
     * 解题思路
     * 题目要求我们编写一个函数来判断一个字符串是否是经过合法压缩后的字符串，并且能够解压缩这个字符串。如果字符串不合法，则返回!error。
     * 压缩规则总结：
     * 连续超过两个相同的字母，将它们压缩为数字 + 字母的形式。例如，aaa压缩为3a，cccc压缩为4c。
     * 字符串中的其他部分保持原样。
     * 合法性检查：
     * 字符串中只允许包含小写字母和数字，不允许出现其他字符。
     * 数字必须大于2，因为两个或更少个相同字母不应该压缩。
     * 数字后面必须跟随一个小写字母，且该数字应扩展为对应数量的字母。
     * 示例分析：
     * 输入4dff：4d扩展为dddd，合法字符串，输出为ddddff。
     * 输入2dff：数字2不应出现，因为两个字母不需要压缩，输入不合法，输出!error。
     * 输入4d@A：包含非法字符@和大写字母A，输入不合法，输出!error。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            System.out.println(decompress(str));
        }
    }

    /**
     *
     * @param str
     * @return
     */
    public static String decompress(String str) {

        if (str == null || str.isEmpty()) {
            return "!error";
        }
        if (!str.matches("[a-z0-9]+")) {
            return "!error";
        }
        String number = "";
        StringBuilder letter = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                number += c;
            } else if (!number.isEmpty()) {
                int num = Integer.parseInt(number);
                if(num <= 2){
                    return "!error";
                }
                for (int j = 0; j < num; j++) {
                    letter.append(c);
                }
            } else {
                letter.append(c);
            }
        }
        return letter.toString();
    }
}
