package E卷.easy100;

import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/6 06:26
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _027_开心消消乐 {
    /**
     * 题目描述
     * 给定一个N行M列的二维矩阵，矩阵中每个位置的数字取值为0或1。矩阵示例如：
     * 1 1 0 0
     * 0 0 0 1
     * 0 0 1 1
     * 1 1 1 1
     * 现需要将矩阵中所有的1进行反转为0，规则如下：
     *
     * 当点击一个1时，该1便被反转为0，同时相邻的上、下、左、右，以及左上、左下、右上、右下8 个方向的1（如果存在1）均会自动反转为0；
     * 进一步地，一个位置上的1被反转为0时，与其相邻的8个方向的1（如果存在1）均会自动反转为0；
     * 按照上述规则示例中的矩阵只最少需要点击2次后，所有值均为0。
     *
     * 请问，给定一个矩阵，最少需要点击几次后，所有数字均为0？
     *
     * 输入描述
     * 第一行为两个整数，分别表示句子的行数 N 和列数 M，取值范围均为 [1, 100]
     *
     * 接下来 N 行表示矩阵的初始值，每行均为 M 个数，取值范围 [0, 1]
     *
     * 输出描述
     * 输出一个整数，表示最少需要点击的次数
     *
     * 示例1
     * 输入
     * 4 4
     * 1 1 0 0
     * 0 0 0 1
     * 0 0 1 1
     * 1 1 1 1
     * 输出
     * 2
     * 示例2
     * 输入
     * 3 3
     * 1 0 1
     * 0 1 0
     * 1 0 1
     * 输出
     * 1
     * 说明
     *
     * 上述样例中，四个角上的 1 均在中间的 1 的相邻 8 个方向上，因此只需要点击一次即可
     *
     * 解题思路
     * 本题的原型题：https://leetcode.cn/problems/number-of-islands/description/
     *
     * 给定一个二维矩阵，矩阵中的每个元素取值为0或1。题目要求我们通过点击矩阵中的1，将其反转为0，并且它周围8个方向上的1也会被自动反转为0，直到所有的1都被反转成0。我们需要找到最少点击次数，使得所有的1都变为0。
     *
     * 这个问题可以看作是一个联通区域的统计问题。每次点击一个1，它不仅自身变为0，它所在的整个联通的1的区域（包括上下左右以及四个对角线上的方向）都会变为0。因此，求最少点击次数相当于求矩阵中有多少个1的联通区域。
     *
     * 每个联通区域内的1可以通过一次点击全部变为0，因此我们只需找到有多少个这样的联通区域，就能得出最少的点击次数。
     *
     * 示例分析：
     * 示例1：
     * 输入：
     * 4 4
     * 1 1 0 0
     * 0 0 0 1
     * 0 0 1 1
     * 1 1 1 1
     * 第一次点击可以选中矩阵的左上角的1，覆盖该联通区域，第一行的两个1变为0。
     * 第二次点击可以选择矩阵右下角的1，这个联通区域包括右下的所有1。
     * 最终，只需点击两次就可以使所有的1变为0，输出为 2。
     *
     * 示例2：
     * 输入：
     * 3 3
     * 1 0 1
     * 0 1 0
     * 1 0 1
     * 这里四个角的1都相邻于中间的1，因此只需点击中间的1，四个角的1都会自动变为0。因此输出为 1。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        if (N < 1 || N > 100 || M < 1 || M > 100) {
            System.out.println("ERROR");
        }
        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        System.out.println(findMinClick(matrix));

    }

    /**
     *
     * @param matrix
     * @return
     */
    public static int findMinClick(int[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    doClick(matrix, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 点击处理器
     * @param matrix
     * @param i
     * @param j
     */
    private static void doClick(int[][] matrix, int i, int j) {
        matrix[i][j] = 0;
        //上下左右 8个方向 都处理一下翻转逻辑
        //左边逻辑处理
        if (i > 0 && matrix[i - 1][j] == 1) {
            doClick(matrix, i - 1, j);
        }

        //右边逻辑处理
        if (i < matrix.length - 1 && matrix[i + 1][j] == 1) {
            doClick(matrix, i + 1, j);
        }

        //上面逻辑处理
        if (j > 0 && matrix[i][j - 1] == 1) {
            doClick(matrix,i, j - 1);
        }
        //下面逻辑处理
        if (j < matrix[0].length - 1 && matrix[i][j + 1] == 1) {
            doClick(matrix, i, j + 1);
        }
        //左上逻辑处理
        if (i > 0 && j > 0 && matrix[i - 1][j - 1] == 1) {
            doClick(matrix, i - 1, j - 1);
        }
        //右上逻辑处理
        if (i < matrix.length - 1 && j < matrix[0].length - 1 && matrix[i + 1][j + 1] == 1) {
            doClick(matrix, i + 1, j + 1);
        }
        //左下逻辑处理
        if (i > 0 && j < matrix[0].length - 1 && matrix[i - 1][j + 1] == 1) {
            doClick(matrix, i - 1, j + 1);
        }
        //右下逻辑处理
        if (i < matrix.length - 1 && j > 0 && matrix[i + 1][j - 1] == 1) {
            doClick(matrix, i + 1, j - 1);
        }
    }


}
