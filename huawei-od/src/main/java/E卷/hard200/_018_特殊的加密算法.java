package E卷.hard200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/15 12:26
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _018_特殊的加密算法 {
    /**
     * 题目描述
     * 有一种特殊的加密算法，明文为一段数字串，经过密码本查找转换，生成另一段密文数字串。
     * 规则如下：
     * 明文为一段数字串由 0~9 组成
     * 密码本为数字 0~9 组成的二维数组
     * 需要按明文串的数字顺序在密码本里找到同样的数字串，密码本里的数字串是由相邻的单元格数字组成，上下和左右是相邻的，
     * 注意：对角线不相邻，同一个单元格的数字不能重复使用。
     * 每一位明文对应密文即为密码本中找到的单元格所在的行和列序号（序号从0开始）组成的两个数宇。
     *
     * 如明文第 i 位 Data[i] 对应密码本单元格为 Book[x][y]，则明文第 i 位对应的密文为X Y，X和Y之间用空格隔开。
     * 如果有多条密文，返回字符序最小的密文。
     * 如果密码本无法匹配，返回"error"。
     * 请你设计这个加密程序。
     *
     * 示例1：
     * 密码本：
     * 0 0 2
     * 1 3 4
     * 6 6 4
     * 明文：“3”，密文：“1 1”
     * 示例2：
     * 密码本：
     * 0 0 2
     * 1 3 4
     * 6 6 4
     * 明文：“0 3”，密文：“0 1 1 1”
     *
     * 示例3：
     * 密码本：
     * 0 0 2 4
     * 1 3 4 6
     * 3 4 1 5
     * 6 6 6 5
     * 明文：“0 0 2 4”，密文：“0 0 0 1 0 2 0 3” 和 “0 0 0 1 0 2 1 2”，返回字典序最小的"0 0 0 1 0 2 0 3"
     *
     * 明文：“8 2 2 3”，密文：“error”，密码本中无法匹配
     *
     * 输入描述
     * 第一行输入 1 个正整数 N，代表明文的长度（1 ≤ N ≤ 200）
     * 第二行输入 N 个明文组成的序列 Data[i]（0 ≤ Data[i] ≤ 9）
     * 第三行输入 1 个正整数 M，代表密文的长度
     * 接下来 M 行，每行 M 个数，代表密文矩阵
     *
     * 输出描述
     * 输出字典序最小密文，如果无法匹配，输出"error"
     *
     * 用例1
     * 输入
     * 2
     * 0 3
     * 3
     * 0 0 2
     * 1 3 4
     * 6 6 4
     * 输出
     * 0 1 1 1
     * 用例2
     * 输入
     *
     * 2
     * 0 5
     * 3
     * 0 0 2
     * 1 3 4
     * 6 6 4
     * 输出
     * error
     * 说明
     *
     * 找不到 0 5 的序列，返回error
     *
     * 解题思路
     * 核心解题思路是通过深度优先搜索（DFS）在一个给定的密码本（二维数组）中找到一条路径，使得这条路径上的数字序列与给定的明文数字序列相匹配，
     * 并且在所有可能的匹配路径中选择一条字典序最小的作为密文路径输出。具体步骤如下：
     *
     * 初始化变量：
     * n 和 m 分别存储明文的长度和密码本的尺寸。
     * book 二维数组存储密码本内容。
     * directions 数组表示搜索的四个方向（右、下、左、上）。
     * minPath 字符串用于存储找到的字典序最小的密文路径。
     * found 布尔变量标记是否找到至少一种加密方式。
     * 搜索准备：
     * 创建一个 visited 布尔二维数组来标记密码本中的数字是否已被访问，以避免重复搜索。
     * 开始搜索：
     * 遍历密码本的每个数字，当找到一个数字与明文的第一个数字相匹配时，从该位置开始使用深度优先搜索（DFS）。
     * 深度优先搜索（DFS）：
     * 递归地搜索所有可能的路径。对于当前位置，如果满足以下条件之一，则继续搜索：
     * 当前位置的数字与明文的当前索引指向的数字相匹配。
     * 已到达明文的末尾，且路径符合条件（字典序最小或找到的第一条路径）。
     * 在每个位置，尝试向四个方向移动，并递归调用 dfs 方法继续搜索。
     * 搜索过程中，使用 visited 数组标记当前位置已访问，以避免循环访问。
     * 回溯与更新：
     * 每当找到一条完整的匹配路径时，比较并更新 minPath 为字典序最小的路径。
     * 完成当前路径的搜索后，回溯（撤销当前位置的访问标记），尝试其他可能的路径。
     * 输出结果：
     * 如果找到至少一条匹配的路径（即 found 为 true），则输出字典序最小的密文路径。
     * 否则，输出 "error" 表示无法在密码本中找到与明文完全匹配的路径。
     */


    // 分别用于存储明文的长度和密码本的尺寸
    static int brightNumber, blackNumber;
    // 用于存储密码本，是一个二维数组
    static int[][] blackNumbers;
    // 表示四个搜索方向：下、右、左、上
    static int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    // 用于存储找到的字典序最小的密文路径
    static String minPath = "";
    // 标记是否找到了至少一种加密方式
    static boolean found = false;

    /**
     *      * 2
     *      * 0 3
     *      * 3
     *      * 0 0 2
     *      * 1 3 4
     *      * 6 6 4
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            brightNumber = Integer.parseInt(scanner.nextLine());
            int[] brightNumbers = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            blackNumber = Integer.parseInt(scanner.nextLine());
            blackNumbers = new int[blackNumber][brightNumber];
            for (int i = 0; i < blackNumber; i++) {
                int[] temp = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                blackNumbers[i] = temp;
            }
            System.out.println(findEncryption(brightNumbers));
        }
        scanner.close();

    }

    /**
     * 找到加密后的密文
     * @param brightNumbers
     * @return
     */
    public static String findEncryption(int[] brightNumbers) {

        boolean[][] visited = new boolean[blackNumber][blackNumber];
        for (int i = 0; i < blackNumbers.length; i++) {
            for (int j = 0; j < blackNumbers[i].length; j++) {
                dfs(brightNumbers, 0, i, j, "", visited);
            }
        }
        return found ? minPath.trim() : "error";

    }

    private static void dfs(int[] brightNumbers, int index, int x, int y, String path, boolean[][] visited ) {

        if (index == brightNumber) {
            if (!found || path.compareTo(minPath) < 0) {
                minPath = path;
            }
            found = true;
            return;
        }

        if (x < 0 || y < 0 || x >= blackNumber || y >= blackNumber || visited[x][y] || blackNumbers[x][y] != brightNumbers[index]) {
            // 如果坐标越界，或该位置已访问，或该位置数字与明文不匹配，则返回
            return;
        }

        visited[x][y] = true;
        String newPath = path + x + " " + y + " ";

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            dfs(brightNumbers, index + 1, newX, newY, newPath, visited);
        }
        // 回溯
        visited[x][y] = false;
    }



}
