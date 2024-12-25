package E卷.easy100;

import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/3 17:13
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _018_光伏场地建设规划 {
    /**
     * 题目描述
     * 祖国西北部有一片大片荒地，其中零星的分布着一些湖泊，保护区，矿区;
     * 整体上常年光照良好，但是也有一些地区光照不太好。
     *
     * 某电力公司希望在这里建设多个光伏电站，生产清洁能源对每平方公里的土地进行了发电评估，
     * 其中不能建设的区域发电量为0kw，可以发电的区域根据光照，地形等给出了每平方公里年发电量x千瓦。
     * 我们希望能够找到其中集中的矩形区域建设电站，能够获得良好的收益。
     *
     * 输入描述
     * 第一行输入为调研的地区长，宽，以及准备建设的电站【长宽相等，为正方形】的边长最低要求的发电量
     * 之后每行为调研区域每平方公里的发电量
     *
     * 输出描述
     * 输出为这样的区域有多少个
     *
     * 示例1
     * 输入
     *
     * 2 5 2 6
     * 1 3 4 5 8
     * 2 3 6 7 1
     * 输出
     *
     * 4
     * 说明
     *
     * 输入含义：
     * 调研的区域大小为长2宽5的矩形，我们要建设的电站的边长为2，建设电站最低发电量为6.
     * 输出含义：
     * 长宽为2的正方形满足发电量大于等于6的区域有4个。
     *
     * 示例2
     * 输入
     * 5 1 1 6
     * 1 3 4 5 8
     * 2 3 6 7 1
     * 输出
     * 3
     * 说明
     *
     * 解题思路
     * 本题可以使用动态规划前缀和思想解题。
     *
     * 解题思路如下：
     *
     * 首先，将每一行在水平方向上选取c个相邻地块进行发电量合并，用例中是2块相邻的地合并
     *
     * 行合并完后，接下来对列进行合并
     *
     *
     *
     * 样的话，最终得到【9，16，22，21】
     *
     * 其中9，起始就是下图中绿色部分，是一个c*c的区域，9是这个区域的发电量总和。其他的16，22，21也同理。
     *
     * image-20230305111558836
     *
     * 因此，9，16，22，21每一个都是符合要求发电站发电量>6的区域，因此结果输出4个
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取矩阵的行数、列数、子矩形的边长和阈值
        int r = scanner.nextInt();
        // 矩阵的行数
        int c = scanner.nextInt();
        // 矩阵的列数
        int len = scanner.nextInt();
        // 子矩形的边长
        int limit = scanner.nextInt();
        // 阈值
        int[][] grid = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        System.out.println(solve(grid,r,c, len,limit));

    }

    /**
     * 矩阵合并
     * @param grid
     * @param len
     * @param minPower
     * @return
     */
    public static int solve(int[][] grid, int r,int c,int len, int minPower) {
        // 初始化前缀和数组，比原矩阵多一行一列，方便边界处理
        int[][] preSum = new int[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                // 前缀和公式：当前值 + 左边 + 上边 - 左上角重复部分
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }

        int res = 0;
        for (int i = len; i <= r; i++) {
            for (int j = len; j <= c; j++) {
                // 计算以当前左上角为起点，右下角为终点的区域的发电量总和
                int sum = preSum[i ][j ] - preSum[i - len][j] - preSum[i][j - len] + preSum[i - len][j - len];
                if (sum >= minPower) {
                    res++;
                }
            }
        }
        return res;
    }
}
