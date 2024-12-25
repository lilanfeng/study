package E卷.easy100;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/6 16:32
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _030_螺旋数字矩阵 {
    /**
     * 题目描述
     * 疫情期间，小明隔离在家，百无聊赖，在纸上写数字玩。他发明了一种写法：
     * 给出数字个数n和行数m（0 < n ≤ 999，0 < m ≤ 999），从左上角的1开始，按照顺时针螺旋向内写方式，依次写出2,3…n，最终形成一个m行矩阵。
     * 小明对这个矩阵有些要求：
     * 每行数字的个数一样多
     * 列的数量尽可能少
     * 填充数字时优先填充外部
     * 数字不够时，使用单个*号占位
     * 输入描述
     * 输入一行，两个整数，空格隔开，依次表示n、m
     * 输出描述
     * 符合要求的唯一矩阵
     * 用例1
     * 输入：
     * 9 4
     * 输出：
     * 1 2 3
     * * * 4
     * 9 * 5
     * 8 7 6
     * 说明：
     *
     * 9个数字写成4行，最少需要3列
     *
     * 用例2
     * 输入：
     * 3 5
     * 输出：
     * 1
     * 2
     * 3
     * *
     * *
     * 说明：
     *
     * 3个数字写5行，只有一列，数字不够用*号填充
     *
     * 用例3
     * 输入：
     * 120 7
     * 输出：
     * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18
     * 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 19
     * 45 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 63 20
     * 44 83 114 115 116 117 118 119 120 * * * * * * 99 64 21
     * 43 82 113 112 111 110 109 108 107 106 105 104 103 102 101 100 65 22
     * 42 81 80 79 78 77 76 75 74 73 72 71 70 69 68 67 66 23
     * 41 40 39 38 37 36 35 34 33 32 31 30 29 28 27 26 25 24
     * 解题思路
     * 本题不难，主要就是模拟。按照题目的要求填充矩阵就可以了。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(" ");
            int n = Integer.parseInt(split[0]);
            int m = Integer.parseInt(split[1]);
            String[][] spiralNumbers = spiralNumbers(n, m);
            for (String[] strings : spiralNumbers) {
                StringJoiner sj = new StringJoiner(" ");
                for (String string : strings) {
                    if (string == null) {
                        string = "*";
                    }
                    sj.add(string);
                }
                System.out.println(sj.toString());
            }
        }
    }

    /**
     * 模拟生成螺旋数字矩阵
     * @param n
     * @param m
     * @return
     */
    public static String[][] spiralNumbers(int n, int m) {
        int column = n%m == 0? n/m : n/m+1;
        String[][] matrix = new String[m][column];
        int number = 1;
        int top = 0, bottom = m - 1, left = 0, right = column - 1;
        while (number <= n){
            //上面
            for (int i = left; i <= right && number <= n; i++) {
                matrix[top][i] = String.valueOf(number++);
            }
            top++;
            //右边
            for (int i = top; i <= bottom && number <= n; i++) {
                matrix[i][right] = String.valueOf(number++);
            }
            right--;
            //下面
            for (int i = right; i >= left && number <= n; i--) {
                matrix[bottom][i] = String.valueOf(number++);
            }
            bottom--;
            //左边
            for (int i = bottom; i >= top && number <= n; i--) {
                matrix[i][left] = String.valueOf(number++);
            }
            left++;
        }
        return matrix;
    }
}
