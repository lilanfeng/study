package E卷.easy100;

import java.util.Scanner;
import java.util.regex.Matcher;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/11 17:34
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _041_水仙花数1 {
    /**
     * 题目描述
     * 所谓水仙花数，是指一个n位的正整数，其各位数字的n次方和等于该数本身。
     * 例如153是水仙花数，153是一个3位数，并且153 = 1^3 + 5^3 + 3^3。
     * 输入描述
     * 第一行输入一个整数n，表示一个n位的正整数。n在3到7之间，包含3和7。
     * 第二行输入一个正整数m，表示需要返回第m个水仙花数。
     * 输出描述
     * 返回长度是n的第m个水仙花数。个数从0开始编号。
     * 若m大于水仙花数的个数，返回最后一个水仙花数和m的乘积。
     * 若输入不合法，返回-1。
     * 示例1
     * 输入
     * 3
     * 0
     * 输出
     * 153
     * 说明
     * 153是第一个水仙花数
     *
     * 示例2
     * 输入
     * 9
     * 1
     * 输出
     * -1
     * 说明
     * 9超出范围
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            int n = Integer.parseInt(scanner.nextLine());
            int m = Integer.parseInt(scanner.nextLine());
            System.out.println(getNum(n, m));
        }
        scanner.close();


    }

    /**
     * 获取第m个水仙花数
     * @param n n位的正整数。n在3到7之间，包含3和7
     * @param m 正整数m，表示需要返回第m个水仙花数
     * @return 返回第m个水仙花数
     */
    private static int getNum(int n, int m) {
        if (n < 3 || n > 7) {
            return -1;
        }
        double minNum = Math.pow(10,n-1);
        double maxNum = Math.pow(10,n);
        int count = 0;
        int tempResult = -1;
        for (int i = (int) minNum; i < (int)maxNum; i++) {
            String str = String.valueOf(i);
            int sum = 0;
            for (int j = 0; j < str.length(); j++) {
                sum += Math.pow(Integer.parseInt(String.valueOf(str.charAt(j))),n);
            }
            if (sum == i) {
                tempResult = i;
                if (m == count) {
                    return i;
                }
                count++;
            }
        }
        return tempResult;
    }

}
