package E卷.easy100;

import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/13 14:55
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _044_玩牌高手 {
    /**
     * 题目描述
     * 给定一个长度为n的整型数组，表示一个选手在n轮内可选择的牌面分数。选手基于规则选牌，
     * 请计算所有轮结束后其可以获得的最高总分数。
     * 选择规则如下：
     * 在每轮里选手可以选择获取该轮牌面，则其总分数加上该轮牌面分数，为其新的总分数。
     * 选手也可不选择本轮牌面直接跳到下一轮，此时将当前总分数还原为3轮前的总分数，若当前轮次小于等于3（即在第1、2、3轮选择跳过轮次），则总分数置为0。
     * 选手的初始总分数为0，且必须依次参加每一轮
     *
     * 输入描述
     * 第一行为一个小写逗号分割的字符串，表示n轮的牌面分数，1<= n <=20。
     *
     * 分数值为整数，-100 <= 分数值 <= 100。
     * 不考虑格式问题。
     * 输出描述
     * 所有轮结束后选手获得的最高总分数。
     * 示例1
     * 输入
     * 1,-5,-6,4,3,6,-2
     * 输出
     * 11
     * 说明
     *
     * 解题思路
     * 这个题目要求解的是在一系列轮次中如何选择分数以最大化总分，同时考虑到特定的规则制约选择过程。
     * 规则
     * 选择牌面：若选手选择获取当前轮的牌面分数，则该轮分数加到总分上。
     * 跳过选择：
     * 跳到下一轮：如果选手跳过当前轮，那么他的总分数会回到3轮前的总分数。特别地，如果当前是前三轮（第1、2、3轮），总分将被置为0。
     * 初始总分为0：选手开始时没有分数。
     * 必须依次参加每轮：选手不能跳过轮次，只能选择是否获取分数。
     * 目标
     * 计算在所有轮次结束后，选手可以获得的最高总分数。
     *
     * 示例说明
     * 输入的分数数组为 [1, -5, -6, 4, 3, 6, -2]。
     * 考虑最优策略为：
     * 第1轮选择分数1：总分为1。
     * 第2轮跳过：由于在前三轮内，总分重置为0。
     * 第3轮跳过：总分仍为0。
     * 第4轮选择分数4：总分从0变为4。
     * 第5轮选择分数3：总分从4变为7。
     * 第6轮选择分数6：总分从7变为13。
     * 第7轮跳过：总分回到第4轮的总分4。
     * 选择这些分数后，最后总分为11。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strNumbers = scanner.nextLine().split(",");
        System.out.println(findMaxScore(strNumbers));
        scanner.close();
    }

    /**
     * 寻找最大的分数
     * @param strNumbers 输入的分数数组为 [1, -5, -6, 4, 3, 6, -2]。
     * @return  11
     */
    public static int findMaxScore(String[] strNumbers) {
        // 记录每轮的最大分数
        int[] scoreArr = new int[strNumbers.length+1];

        int maxScore = 0;
        for (int i = 0; i < strNumbers.length; i++) {
            int score = Integer.parseInt(strNumbers[i]);
            if(i < 3){
                if (i == 0) {
                    maxScore = Math.max(score, 0);
                } else {
                    maxScore = Math.max(maxScore + score, 0);
                }
            }else {
                //第四轮及以上处理逻辑
                //选手也可不选择本轮牌面直接跳到下一轮，此时将当前总分数还原为3轮前的总分数
                //分两种情况：1，选择本轮牌面，上一轮的最大值加上本轮牌面值
                //2，不选择本轮牌面，就是3轮前的总分数
                maxScore = Math.max(maxScore + score, scoreArr[i-2]);
            }
            //
            scoreArr[i+1] = maxScore;
        }
        return  maxScore;
    }
}
