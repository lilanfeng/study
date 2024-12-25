package E卷.hard200;

import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/3 15:53
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _003_电脑病毒感染 {
    /**
     * 题目描述
     * 一个局域网内有很多台电脑，分别标注为 0 ~ N-1 的数字。相连接的电脑距离不一样，所以感染时间不一样，感染时间用 t 表示。
     * 其中网络内一台电脑被病毒感染，求其感染网络内所有的电脑最少需要多长时间。如果最后有电脑不会感染，则返回-1。
     * 给定一个数组 times 表示一台电脑把相邻电脑感染所用的时间。
     * 如图：path[i] = {i, j, t} 表示：电脑 i->j，电脑 i 上的病毒感染 j，需要时间 t。
     *
     * 输入描述
     * 第一行输入一个整数N ，表示局域网内电脑个数 N ，1 ≤ N ≤ 200 ;
     * 第二行输入一个整数M ,表示有 M 条网络连接；
     * 接下来M行 ,每行输入为 i , j , t 。表示电脑 i 感染电脑j 需要时间 t 。（1 ≤ i , j ≤ N）
     * 最后一行为病毒所在的电脑编号。
     *
     * 输出描述
     * 输出最少需要多少时间才能感染全部电脑，如果不存在输出 -1
     *
     * 用例
     * 输入
     * 4
     * 3
     * 2 1 1
     * 2 3 1
     * 3 4 1
     * 2
     * 输出
     * 2
     * 说明	第一个参数：局域网内电脑个数N，1 ≤ N ≤ 200；
     * 第二个参数：总共多少条网络连接
     * 第三个 2 1 1 表示2->1时间为1
     * 第六行：表示病毒最开始所在电脑号2
     * 解题思路
     * Bellman-Ford算法是一种用于在加权图中找到从单个源点到所有其他顶点的最短路径的算法。它能够处理带有负权边的图，这是它与Dijkstra
     * 算法的主要区别。然而，如果图中存在负权回路，即一个总权重为负的环路，Bellman-Ford算法可以检测到这种情况。
     *
     * 算法的工作原理如下：
     *
     * 初始化距离数组：算法开始时，除了源点（在上面的代码中是变量K）的距离被初始化为0以外，所有顶点的距离都被设置为无穷大（在上面的代码中是INF）。
     * 松弛操作：算法会进行N-1次迭代，其中N是图中顶点的数量。在每次迭代中，算法会遍历所有的边，并尝试更新每条边的目标顶点的距离。如果通过当前边到达目标顶点的距离小于已知的最短距离，则更新该顶点的最短距离。这个过程称为松弛操作。
     * 检测负权回路：在N-1次迭代之后，算法会再次遍历所有的边，检查是否还能进行松弛操作。如果可以，这意味着图中存在负权回路，因为最短路径应该已经在前面的N-1次迭代中被确定下来。
     * 在下面的代码中，networkDelayTime函数实现了Bellman-Ford算法：
     *
     * times数组包含了图中所有的边，其中每个元素是一个三元组[u, v, w]，表示从顶点u到顶点v的边，其权重为w。
     * dist数组用于存储从源点K到每个顶点的最短距离。
     * 在for循环中，算法遍历所有边，并对每条边执行松弛操作。如果dist[u] + w < dist[v]，则更新dist[v]为dist[u] + w。
     * 在所有顶点的最短距离被计算出来后，算法找出最长的最短距离，即maxWait，这是感染所有电脑所需的最少时间。
     * 如果有顶点的距离仍然是无穷大，这意味着有些顶点无法从源点K到达，函数返回-1。
     * Bellman-Ford算法的时间复杂度是O(VE)，其中V是顶点的数量，E是边的数量。这使得它在稠密图中效率较低，但它是处理带有负权边的图的有效算法。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

            int n = Integer.parseInt(scanner.nextLine());
            int m = Integer.parseInt(scanner.nextLine());
            int[][] times = new int[m][3];
            for (int i = 0; i < m; i++) {
                String[] split = scanner.nextLine().split(" ");
                times[i][0] = Integer.parseInt(split[0]) -1;
                times[i][1] = Integer.parseInt(split[1]) -1;
                times[i][2] = Integer.parseInt(split[2]);
            }
            int index = Integer.parseInt(scanner.nextLine()) -1;
            scanner.close();
            int result = networkDelayTime(times, n, index);
            System.out.println(result);
    }

    /**
     *
     * @param times
     * @param n
     * @param index
     * @return
     */
    public static int networkDelayTime(int[][] times, int n, int index) {
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[index] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int[] time : times) {
                int u = time[0];
                int v = time[1];
                int w = time[2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        int maxWait = 0;
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            maxWait = Math.max(maxWait, dist[i]);
        }
        return maxWait;
    }


}
