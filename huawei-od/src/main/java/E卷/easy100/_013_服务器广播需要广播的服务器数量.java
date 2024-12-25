package E卷.easy100;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _013_服务器广播需要广播的服务器数量 {
    /**
     * 题目描述
     * 服务器连接方式包括直接相连，间接连接。
     * A和B直接连接，B和C直接连接，则A和C间接连接。
     * 直接连接和间接连接都可以发送广播。
     * 给出一个N*N数组，代表N个服务器，
     * matrix[i][j] == 1，
     * 则代表i和j直接连接；不等于 1 时，代表i和j不直接连接。
     * matrix[i][i] == 1，
     * 即自己和自己直接连接。matrix[i][j] == matrix[j][i]。
     * 计算初始需要给几台服务器广播， 才可以使每个服务器都收到广播。
     *
     * 输入描述
     * 输入为N行，每行有N个数字，为0或1，由空格分隔，
     * 构成N*N的数组，N的范围为 1 <= N <= 40
     *
     * 输出描述
     * 输出一个数字，为需要广播的服务器的数量
     *
     * 示例1
     * 输入
     * 1 0 0
     * 0 1 0
     * 0 0 1
     * 输出
     * 3
     * 说明
     *
     * 3 台服务器互不连接，所以需要分别广播这 3 台服务器
     *
     * 示例2
     * 输入
     * 1 1
     * 1 1
     * 输出
     * 1
     * 说明
     * 2 台服务器相互连接，所以只需要广播其中一台服务器
     *
     * 解题思路
     * 这道题可以理解为在一组服务器之间进行广播传播。服务器之间既可以直接相连，也可以通过其他服务器间接相连。题目的目的是计算最少需要对几台服务器进行广播，才能保证所有服务器都能收到广播信号。
     *
     * 题目要点：
     * 服务器的直接连接与间接连接：
     * 如果服务器 A 和服务器 B 直接连接，或者服务器 A 可以通过其他服务器间接连接到 B，则广播信号可以通过这些连接传播。
     * 如果服务器 A 和服务器 B 不存在直接或间接的连接，说明它们属于不同的独立网络，广播信号无法在这些网络间传播。
     *
     * 矩阵描述：
     *
     * 给定的 N*N 矩阵 matrix 描述了服务器的连接状态：
     * matrix[i][j] == 1 表示服务器 i 和服务器 j 直接相连。
     * matrix[i][j] == 0 表示服务器 i 和服务器 j 之间没有直接连接。
     * matrix[i][i] == 1，表示每台服务器都和自己直接相连。
     * 矩阵是对称的，即 matrix[i][j] == matrix[j][i]，意味着连接是双向的。
     * 广播的最小台数：
     *
     * 要使每一台服务器都能接收到广播，需要确保每一个相互不连通的服务器组至少有一台服务器接收到初始广播。
     * 题目中需要计算最少要对多少台服务器发起广播，才能使所有服务器都能通过直接或间接连接接收到广播。
     * 示例分析：
     * 示例 1：
     * 输入：
     *
     * 1 0 0
     * 0 1 0
     * 0 0 1
     * 解释：
     *
     * 这 3 台服务器互相之间都没有连接，表示它们是完全独立的 3 个部分。
     * 为了使所有服务器都能接收到广播，需要分别对每台服务器进行广播，因此结果是 3。
     * 示例 2：
     * 输入：
     *
     * 1 1
     * 1 1
     * 解释：
     *
     * 这 2 台服务器相互直接连接，只需要对其中一台服务器进行广播，信号就会传递到另一台服务器，因此只需要一次广播，结果是 1。
     * 问题转化：
     * 实际上，这道题可以转化为图论中的 连通分量 问题：
     *
     * 矩阵中的每个服务器可以看作是图中的一个节点；
     * 如果两个服务器之间存在直接连接（即 matrix[i][j] == 1），则它们之间存在一条边；
     * 题目要求的是图中的连通分量的个数。每个连通分量中只需要选择一个服务器发起广播即可，所以连通分量的数量就是答案。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            int[] split = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = split.length;
            int[][] matrix = new int[n][n];
            matrix[0] = split;
            for (int i = 1; i < n; i++) {
                matrix[i] = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            Queue<Integer> queue = new LinkedList<>();
            HashSet<Integer> set = new HashSet<>();
            int maxCount = 0;
            for (int i = 0; i < n; i++) {
                if(!set.contains(i)){
                    dfsMaxDiff(matrix, set, i);
                    maxCount++;
                }
            }
            System.out.println(maxCount);
        }
    }


    public static void  dfsMaxDiff(int[][] matrix, HashSet<Integer> set, int index) {
        set.add(index);
        for (int i = index + 1; i < matrix.length; i++) {
            if(matrix[index][i] == 1 && !set.contains(i)){
                dfsMaxDiff(matrix,set,i);
            }
        }
    }
}
