package 全部;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _371_两整数之和 {
    /**
     * https://leetcode.cn/problems/sum-of-two-integers/description/
     * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。

     * 示例 1：
     *
     * 输入：a = 1, b = 2
     * 输出：3
     * 示例 2：
     *
     * 输入：a = 2, b = 3
     * 输出：5
     * 提示：
     * -1000 <= a, b <= 1000
     * 思路：
     * 1. 循环相加，直到进位为0
     * 2. 进位为1，则进位为0，当前位为1，否则为0
     * 3. 循环结束后，判断进位，如果进位为1，则最高位为1，否则为0
     */

    public int getSum(int a, int b) {
        while (b != 0) {
            int carry  = (a & b) << 1;
            a = a^b ;
            b = carry;
        }
        return a;
    }

    public static void main(String[] args) {
        _371_两整数之和 obj = new _371_两整数之和();
        System.out.println(obj.getSum(4, 5));
    }

}
