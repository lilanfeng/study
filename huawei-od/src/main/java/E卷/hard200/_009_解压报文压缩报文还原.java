package E卷.hard200;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/8 05:30
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _009_解压报文压缩报文还原 {
    /**
     * 题目描述
     * 为了提升数据传输的效率，会对传输的报文进行压缩处理。
     * 输入一个压缩后的报文，请返回它解压后的原始报文。
     * 压缩规则：n[str]，表示方括号内部的 str 正好重复 n 次。
     * 注意 n 为正整数（0 < n <= 100），str只包含小写英文字母，不考虑异常情况。
     * 输入描述
     * 输入压缩后的报文：
     * 1）不考虑无效的输入，报文没有额外的空格，方括号总是符合格式要求的；
     * 2）原始报文不包含数字，所有的数字只表示重复的次数 n ，例如不会出现像 5b 或 3[8] 的输入；
     * 输出描述
     * 解压后的原始报文
     * 注：原始报文长度不会超过1000，不考虑异常的情况
     * 示例1
     * 输入
     * 3[k]2[mn]
     * 输出
     * kkkmnmn
     * 说明
     * k 重复3次，mn 重复2次，最终得到 kkkmnmn
     * 示例2
     * 输入
     * 3[m2[c]]
     * 输出
     * mccmccmcc
     * 说明
     * m2[c] 解压缩后为 mcc，重复三次为 mccmccmcc
     *
     * 解题思路
     * 压缩报文的格式为：n[str]，其中 n 是一个表示重复次数的正整数，str 是需要重复的字符串部分，且 str 只包含小写字母。题目要求我们将这种格式的报文解压，得到原始报文。
     *
     * 具体规则：
     * 报文中的 n[str] 表示 str 字符串需要重复 n 次。例如：
     * 3[k] 表示字符串 k 需要重复 3 次，解压后为 kkk。
     * 2[mn] 表示字符串 mn 需要重复 2 次，解压后为 mnmn。
     * 解题思路：
     * 栈的使用：这是一个经典的用栈解决的题目。我们可以用栈来处理嵌套的结构，比如 n[str] 这样的结构，先解压括号内部的内容，然后再处理外部的结构。
     *
     * 主要步骤：
     *
     * 遍历字符串，如果遇到数字（表示重复次数 n），将数字读取出来。
     * 遇到 [ 时，开始记录括号内的字符串，直到遇到对应的 ]，此时将前面的字符串部分和数字进行拼接。
     * 将结果拼接到最终的解压字符串中。
     * 示例解析（3[m2[c]]）：
     * 初始字符串为 3[m2[c]]。
     * 第一步：遇到 3，表示后面 [...] 内的内容重复 3 次。
     * 第二步：遇到 m，开始记录字符串部分 m。
     * 第三步：遇到 2，表示后面 [c] 内的内容重复 2 次。
     * 第四步：遇到 c，记录字符串 c。
     * 第五步：遇到 ]，表示 2[c] 解压为 cc。
     * 第六步：mcc 需要重复 3 次，解压后为 mccmccmcc。
     * 因此输出结果为 mccmccmcc。
     */
    public static void main(String[] args) {
        //String compressed = "3[k]2[mn]";
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String compressed = scanner.nextLine();
            System.out.println(decompress(compressed));
        }
    }

    /**
     *  解压字符串
     * @param compressed
     * @return
     */
    public static String decompress(String compressed) {
        Stack<String[]> stack = new Stack<>();
        // 初始化栈，将一个空字符串入栈，用于处理字符串的拼接 重复字符串 重复次数  上一个字符串
        stack.push(new String[]{"", "1",""});
        int i = 0;
        String number = "";
        String str = "";
        while (i < compressed.length()) {
            char c = compressed.charAt(i);
            if(Character.isLetter(c)){
                str += c;
            }
            if (Character.isDigit(c)) {
                number += c;
            }
            if (c == '[') {
                stack.push(new String[]{str, number, ""});
                number = "";
                str = "";
            } else if (c == ']') {
                String[] temp = stack.pop();
                String repeatStr = temp[0];
                int repeat = Integer.parseInt(temp[1]);
                String preResult = temp[2];
                String resultStr = "";
                for (int j = 0; j < repeat; j++) {
                    resultStr +=  preResult + str;
                }
                stack.peek()[2] += stack.peek()[2] + repeatStr + resultStr;
                str = "";
            }
            i++;
        }
        return stack.peek()[2] + str;
    }
}
