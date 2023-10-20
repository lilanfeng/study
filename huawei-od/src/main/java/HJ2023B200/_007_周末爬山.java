package HJ2023B200;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 题目描述
 * 周末小明准备去爬山锻炼，0代表平地，山的高度使用1到9来表示，小明每次爬山或下山高度只能相差k及k以内，每次只能上下左右一个方向上移动一格，小明从左上角(0,0)位置出发
 *
 * 输入描述
 * 第一行输入m n k(空格分隔)
 * 代表m*n的二维山地图，k为小明每次爬山或下山高度差的最大值，
 * 然后接下来输入山地图，一共m行n列，均以空格分隔。取值范围：
 *
 * 0 < m ≤ 500
 * 0< n ≤ 500
 * 0 < k < 5
 * 输出描述
 * 请问小明能爬到的最高峰多高，到该最高峰的最短步数，输出以空格分隔。
 * 同高度的山峰输出较短步数。
 * 如果没有可以爬的山峰，则高度和步数都返回0。
 * 备注
 * 所有用例输入均为正确格式，且在取值范围内，考生不需要考虑不合法的输入格式。
 *
 * 用例
 * 输入	5 4 1
 * 0 1 2 0
 * 1 0 0 0
 * 1 0 1 2
 * 1 3 1 0
 * 0 0 0 9
 * 输出	2 2
 * 说明	根据山地图可知，能爬到的最高峰在(0,2)位置，高度为2，最短路径为(0,0)-(0,1)-(0,2)，最短步数为2。
 * 输入	5 4 3
 * 0 0 0 0
 * 0 0 0 0
 * 0 9 0 0
 * 0 0 0 0
 * 0 0 0 9
 * 输出	0 0
 * 说明	根据山地图可知，每次爬山距离3，无法爬到山峰上，步数为0。
 * 解题思路
 * 这个问题的解题思路是使用深度优先搜索（DFS）算法来解决小明爬山的问题。以下是详细的解题步骤：
 *
 * 定义一个常量数组 OFFSETS，表示上下左右四个方向的偏移量。这将帮助我们在搜索过程中遍历相邻的位置。
 *
 * 实现一个深度优先搜索函数 dfs，它接收以下参数：当前位置的坐标 (x, y)，当前步数 step，一个哈希表 min_step_to_height 用于存储到达不同高度的最短步数，山地图矩阵 matrix，矩阵的行数 m
 * 和列数 n，允许的最大高度差 k，一个记忆化数组 memo 用于记录已经访问过的位置和步数，以及一个布尔数组 visited 用于记录已经访问过的位置。
 * 在 dfs 函数中，首先获取当前位置的高度。
 * 遍历四个方向（上下左右），计算新的位置，并检查新位置是否在矩阵范围内。
 * 获取新位置的高度，并检查两个位置的高度差是否在 k 以内。如果高度差在 k 以内，执行以下操作：
 * a. 增加步数。
 * b. 更新到达新高度的最短步数。如果当前高度不在 min_step_to_height 中，或者当前步数小于已记录的最短步数，更新 min_step_to_height。
 * c. 检查记忆化数组，避免重复计算。如果当前位置没有被访问过，或者已访问过但当前步数小于已记录的步数，执行以下操作：
 * i. 更新记忆化数组。
 * ii. 标记当前位置为已访问。
 * iii. 递归调用 dfs 函数，继续搜索。
 * iv. 回溯时，将当前位置标记为未访问。
 * d. 减少步数。
 * 在主函数中，读取输入的 m、n、k 和山地图矩阵，调用 dfs 函数，从起点 (0, 0) 开始搜索。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/130887814
 */
public class _007_周末爬山 {

    private static final int[][] OFFSETS = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] array = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = sc.nextInt();
            }

        }

        Map<Integer,Integer> minStepToHeight = new HashMap<>();
        minStepToHeight.put(array[0][0],0);
        int[][] memory = new int[m][n];

        boolean[][] visited = new boolean[m][n];

        dfs(0,0,0,minStepToHeight,array,m,n,k,memory,visited);

        int maxHeight = minStepToHeight.keySet().stream().max((a,b)->a-b).orElse(0);
        int minStep = minStepToHeight.get(maxHeight);

        System.out.println(maxHeight + " " + minStep);


    }


    public static void dfs(int x, int y, int step, Map<Integer,Integer> minStepToHeight,int[][] array,int m,int n,int k,int[][] memory,boolean[][] visited ){
        int lastHeight = array[x][y];

        for (int[] offset :OFFSETS){
            int newX = x + offset[0];
            int newY = y + offset[1];
            if (newX < 0 || newX >= m || newY < 0 || newY >= n) {
                continue;
            }

            int curHeight = array[newX][newY];
            //abs() 绝对值
            if(Math.abs(curHeight -lastHeight) <= k){
                step++;

                if(!minStepToHeight.containsKey(curHeight) || minStepToHeight.get(curHeight) > step){
                    minStepToHeight.put(curHeight,step);
                }
                if(memory[newX][newY] == 0|| memory[newX][newY] > step){
                    memory[newX][newY] = step;
                    visited[x][y] = true;
                    dfs(newX,newY,step,minStepToHeight,array,m,n,k,memory,visited);

                    visited[x][y] = false;
                }
                step--;
            }

        }
    }
}
