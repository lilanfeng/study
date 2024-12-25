package E卷.hard200;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/7 20:10
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _008_计算网络信号信号强度 {
    /**
     * 题目描述
     * 网络信号经过传递会逐层衰减，且遇到阻隔物无法直接穿透，在此情况下需要计算某个位置的网络信号值。
     * 注意:网络信号可以绕过阻隔物。
     *
     * array[m][n] 的二维数组代表网格地图，
     * array[i][j] = 0代表i行j列是空旷位置;
     * array[i][j] = x(x为正整数)代表i行j列是信号源，信号强度是x;
     * array[i][j] = -1代表i行j列是阻隔物。
     * 信号源只有1个，阻隔物可能有0个或多个
     * 网络信号衰减是上下左右相邻的网格衰减1
     * 现要求输出对应位置的网络信号值。
     *
     * 输入描述
     * 输入为三行，
     *
     * 第一行为 m 、n ，代表输入是一个 m × n 的数组。
     * 第二行是一串 m × n 个用空格分隔的整数。每连续 n 个数代表一行，再往后 n 个代表下一行，以此类推。对应的值代表对应的网格是空旷位置，还是信号源，还是阻隔物。
     * 第三行是 i 、 j，代表需要计算array[i][j]的网络信号值。
     * 注意：此处 i 和 j 均从 0 开始，即第一行 i 为 0。
     *
     * 6 5
     * 0 0 0 -1 0 0 0 0 0 0 0 0 -1 4 0 0 0 0 0 0 0 0 0 0 -1 0 0 0 0 0
     * 1 4
     * 代表如下地图
     *
     * image-20230312223543785
     *
     * 需要输出第1行第4列的网络信号值，值为2。
     *
     * image-20230312223703130
     *
     * 输出描述
     * 输出对应位置的网络信号值，如果网络信号未覆盖到，也输出0。
     *
     * 一个网格如果可以途径不同的传播衰减路径传达，取较大的值作为其信号值。
     *
     * 示例1
     * 输入
     *
     * 6 5
     * 0 0 0 -1 0 0 0 0 0 0 0 0 -1 4 0 0 0 0 0 0 0 0 0 0 -1 0 0 0 0 0
     * 1 4
     * 输出
     *
     * 2
     * 说明
     *
     * 示例2
     * 输入
     *
     * 6 5
     * 0 0 0 -1 0 0 0 0 0 0 0 0 -1 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
     * 2 1
     * 输出
     *
     * 0
     * 说明
     *
     * 解题思路
     * 在用例1中。有一个信号源：
     * image-20230312223543785
     *
     * 需要求的是（1，4）位置的强度，请注意行列是从0开始的。
     *
     * image-20230312223703130
     *
     * 根据衰减原理，最后的结果是2
     *
     * 这道题可以使用广度优先搜索（BFS）来解决。我们可以先找到信号源的位置，将其加入队列中，然后不断从队列中取出位置，向四个方向传播信号，如果某个位置的信号强度为
     * 0，则说明可以传播到该位置，将其加入队列，并将其信号强度设为当前位置的信号强度减 1。当某个位置的信号强度为 1 时，说明不需要再传播了，后面的位置肯定都是 0。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }
        int findx = scanner.nextInt();
        int findy = scanner.nextInt();
        scanner.close();
        System.out.println(getSignalStrength(grid, findx, findy));
    }

    /**
     *  获取信号强度
     * @param grid
     * @param row
     * @param col
     * @return
     */
    public static int getSignalStrength(int[][] grid, int row, int col) {

        //找出信号源的数据
        Queue<int[]> strengthQueue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] > 0) {
                    strengthQueue.offer(new int[]{i, j, grid[i][j]});
                }
            }
        }
        while (!strengthQueue.isEmpty()) {
            int[] cur = strengthQueue.poll();
            int x = cur[0];
            int y = cur[1];
            int strength = cur[2];
            //向四个方向传播信号
            if (x > 0 && grid[x - 1][y] == 0) {
                grid[x - 1][y] = strength - 1;
                strengthQueue.offer(new int[]{x - 1, y, strength - 1});
            }
        }
        return grid[row][col];
    }
}
