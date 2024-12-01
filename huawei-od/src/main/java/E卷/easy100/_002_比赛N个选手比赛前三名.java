package E卷.easy100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Stream;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _002_比赛N个选手比赛前三名 {
    /**
     * 题目描述
     * 一个有N个选手参加比赛，选手编号为1~N（3<=N<=100），有M（3<=M<=10）个评委对选手进行打分。
     * 打分规则为每个评委对选手打分，最高分10分，最低分1分。
     * 请计算得分最多的3位选手的编号。
     * 如果得分相同，则得分高分值最多的选手排名靠前
     *
     * (10分数量相同，则比较9分的数量，以此类推，用例中不会出现多个选手得分完全相同的情况)。
     *
     * ​
     *
     * 输入描述
     * 第一行为半角逗号分割的两个正整数，第一个数字表示M（3<=M<=10）个评委，第二个数字表示N（3<=N<=100）个选手。
     *
     * 第2到M+1行是半角逗号分割的整数序列，表示评委为每个选手的打分，0号下标数字表示1号选手分数，1号下标数字表示2号选手分数，依次类推。
     *
     * 输出描述
     * 选手前3名的编号。
     *
     * **注：**若输入为异常，输出-1，如M、N、打分不在范围内。
     *
     * 示例1
     * 输入
     *
     * 4,5
     * 10,6,9,7,6
     * 9,10,6,7,5
     * 8,10,6,5,10
     * 9,10,8,4,9
     * 1
     * 2
     * 3
     * 4
     * 5
     * 输出
     *
     * 2,1,5
     * 1
     * 说明
     *
     * 第一行代表有4个评委，5个选手参加比赛
     *
     * 矩阵代表是4*5，每个数字是选手的编号，每一行代表一个评委对选手的打分排序，
     *
     * 2号选手得分36分排第1，1号选手36分排第2，5号选手30分(2号10分值有3个，1号10分值只有1个，所以2号排第一)
     *
     * 示例2
     * 输入
     *
     * 2,5
     * 7,3,5,4,2
     * 8,5,4,4,3
     * 1
     * 2
     * 3
     * 输出
     *
     * -1
     * 1
     * 说明
     *
     * 只有2个评委，要求最少为3个评委
     *
     * 示例3
     * 输入
     *
     * 4,2
     * 8,5
     * 5,6
     * 10,4
     * 8,9
     * 1
     * 2
     * 3
     * 4
     * 5
     * 输出
     *
     * -1
     * 1
     * 说明
     *
     * 只有2名选手参加，要求最少为3名
     *
     * 示例4
     * 输入
     *
     * 4,5
     * 11,6,9,7,8
     * 9,10,6,7,8
     * 8,10,6,9,7
     * 9,10,8,6,7
     * 输出
     *
     * -1
     * 说明
     *
     * 第一个评委给第一个选手打分11，无效分数
     *
     * 解题思路
     * 题目题意解析
     * 题目要求我们从一组评委对多个选手的评分中，计算得分最高的三个选手的编号。具体规则如下：
     *
     * 选手和评委数量：
     *
     * 有 N个选手，编号为1到N，且N的范围在3到100之间。
     * 有 M个评委，M的范围在3到10之间。
     * 打分规则：
     *
     * 每个评委给每个选手打分，分数为 1到10 的整数。
     * 每个选手最终得分是所有评委对他的评分之和。
     * 排名规则：
     *
     * 我们需要计算得分最多的3位选手的编号。
     * 如果有选手得分相同，优先比较高分（例如：10分的数量最多的选手排在前面；如果10分数量相同，则比较9分的数量，依此类推）。
     * 题目保证不存在完全相同的得分情况，即每个选手的分数分布不会完全相同。
     * 异常情况：
     *
     * 如果输入中的 M 或 N 不在题目要求的范围内，或者打分不是1到10之间的有效分数，则输出 -1 表示无效。
     * 示例解析
     * 示例 1:
     * 输入：
     *
     * 4,5
     * 10,6,9,7,6
     * 9,10,6,7,5
     * 8,10,6,5,10
     * 9,10,8,4,9
     * 解析：
     *
     * 第一行表示有4个评委，5个选手。
     * 各个评委对选手的打分矩阵是：
     * 10  6   9   7   6
     *  9  10  6   7   5
     *  8  10  6   5  10
     *  9  10  8   4   9
     * 计算每个选手的总分：
     * 1号选手：10 + 9 + 8 + 9 = 36
     * 2号选手：6 + 10 + 10 + 10 = 36
     * 3号选手：9 + 6 + 6 + 8 = 29
     * 4号选手：7 + 7 + 5 + 4 = 23
     * 5号选手：6 + 5 + 10 + 9 = 30
     * 得分排名前3位的选手分别是：2号、1号、5号（2号选手的10分数量最多，因此排在1号前面）。
     * 输出：
     *
     * 2,1,5
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("[,\n]");
        int scoreCount = scanner.nextInt();
        int playerCount = scanner.nextInt();
        if (scoreCount < 3 || scoreCount > 10 || playerCount < 3 || playerCount > 100) {
            System.out.println("-1");
            return;
        }
        int[][] playerScores = new int[scoreCount][playerCount];
        for (int i = 0; i < scoreCount; i++) {
            for (int j = 0; j < playerCount; j++) {
                playerScores[i][j] = scanner.nextInt();
                if (playerScores[i][j] < 1 || playerScores[i][j] > 10) {
                    System.out.println("-1");
                    return;
                }
            }
        }
        System.out.println(findBestPlayers(scoreCount,playerCount,playerScores));
    }

    /**
     * 输入：
     * @param scoreCount
     * @param playerCount
     * @param playerScores
     * @return
     */
    public static String findBestPlayers(int scoreCount,int playerCount,int[][] playerScores) {
        // 存储每个选手的得分
        HashMap<Integer,Integer[]> playerScoreMap = new HashMap<>();
        for (int i = 0; i < playerCount; i++) {
            Integer[] scores = new Integer[scoreCount];
            for (int j = 0; j < scoreCount; j++) {
                scores[j] = playerScores[j][i];
            }
            Arrays.sort(scores,(a,b)-> b -a);
            playerScoreMap.put(i,scores);
        }
        StringJoiner result = new StringJoiner(",");
        playerScoreMap.entrySet().stream().sorted(
                (o1,o2)->{
                    Integer[] scores1 = o1.getValue();
                    Integer[] scores2 = o2.getValue();
                    int sum1 = Stream.of(scores1).mapToInt(Integer::intValue).sum();
                    int sum2 = Stream.of(scores2).mapToInt(Integer::intValue).sum();
                    if(sum1 != sum2){
                        return sum2 - sum1;
                    }
                    for (int i = 0; i < scoreCount; i++) {
                        if (scores1[i].compareTo(scores2[i]) == 0) {
                            continue;
                        }
                        //按照最高分倒序排列
                        return scores2[i] - scores1[i];
                    }
                    return 0;
                }
        ).limit(3).forEach(entry->result.add(entry.getKey()+1+""));
        return result.toString();
    }


}
