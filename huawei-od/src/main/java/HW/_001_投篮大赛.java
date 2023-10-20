package HW;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 *  题目描述 你现在是一场采用特殊赛制投篮大赛的记录员。这场比赛由若干回合组成，过去几回合的得 分可能会影响以后几回合的得分。
 * 比赛开始时，记录是空白的。
 * 你会得到一个记录操作的字符串列表 ops，其中 ops[i]是你需要记录的第 i 项操作，ops 遵循 下述规则:
 * 。数 X-表示本回合新获得分数 X
 * 。“+”- 表示本回合新获得的得分是前两次得分的总和。
 * 。“D”- 表示本回合新获得的得分是前一次得分的两倍。
 * 。“C”- 表示本回合没有分数，并且前一次得分无效，将其从记录中移除。 请你返回记录中所有得分的总和。
 * 输入描述
 * 输入为一个字符串数组
 * 输出描述
 * 输出为一个整形数字
 * 提示
 * 1.1 <= ops.length <= 1000
 * 2.ops[i] 为“C”、“D”、“+”，或者一个表示整数的字符串。整数范围是[-3*10^4,3*10^4] 3.需要考虑异常的存在，如有异常情况，请返回-1 4
 * .对于“+”操作，题目数据不保证记录此操作时前面总是存在两个有效的分数 5.对于“C”和“D”操作，题目数据不保证记录此操作时前面存在一个有效的分数 6.题目输出范围不会超过整型的最大范围，不超过 2^63 _1
 * 用例
 *  输入：5 2 C D +
 *  输出：30
 *
 *  输入：5 -2 4 C D 9 + +
 *
 */
public class _001_投篮大赛 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            System.out.println(getScore(sc.nextLine()));
        }
    }

    public static int getScore(String input){
        String[] scoreArr = input.split(" ");
        LinkedList<Integer> scoreList = new LinkedList<>();
        //整数 正负都包括
        String reg = "^\\-?\\d+$";

        for (int i = 0; i < scoreArr.length; i++) {
            String score = scoreArr[i];
            if(score.matches(reg)){
                scoreList.add(Integer.parseInt(score));
                continue;
            }

            switch (score){
                case "+":
                    if(scoreList.size() < 2){
                        return  -1;
                    }
                    scoreList.addLast(scoreList.getLast() + scoreList.get(scoreList.size() -2));
                    break;
                case "C":
                    if(scoreList.size() < 1){
                        return  -1;
                    }
                    scoreList.removeLast();
                    break;

                case "D":
                    if(scoreList.size() < 1){
                        return -1;
                    }
                    scoreList.addLast(scoreList.getLast() * 2);
                    break;
                default:
            }
        }

        int sum = 0;
        for (Integer integer : scoreList) {
            sum += integer;
        }
        return sum;
    }

}
