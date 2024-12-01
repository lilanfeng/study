package E卷.easy100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _001_报数游戏 {
    /**
     *  100个人围成一圈，每个人有一个编码，编号从1开始到100。
     * 他们从1开始依次报数，报到为M的人自动退出圈圈，然后下一个人接着从1开始报数，直到剩余的人数小于M。
     * 请问最后剩余的人在原先的编号为多少？
     *
     * 输入描述
     * 输入一个整数参数 M
     *
     * 输出描述
     * 如果输入参数M小于等于1或者大于等于100，输出“ERROR!”；
     *
     * 否则按照原先的编号从小到大的顺序，以英文逗号分割输出编号字符串
     *
     * 示例1
     * 输入
     * 3
     * 输出
     *
     * 58,91
     * 说明
     *
     * 示例2
     * 输入
     *
     * 4
     * 输出
     *
     * 34,45,97
     * 说明
     *
     * 解题思路
     * 题目描述的是一个经典的约瑟夫环（Josephus）问题的变种。题目要求在100个人围成一圈的情况下，进行报数，每次报到数字为M的那个人自动退出，直到剩下的人数少于M为止。我们需要找出最后剩下的人的编号。
     * 详细步骤
     * 初始状态：有100个人，编号从1到100。
     * 报数规则：
     * 每个人从1开始依次报数，每当某个人报到M时，该人退出圈子，剩下的人继续报数。
     * 报数是连续进行的，即如果当前报数人退出，接下来的人从1重新开始报数。
     * 停止条件：当剩余人数小于M时，停止报数，输出剩余人的编号。
     * 输入限制：
     * 如果输入的M不符合1 < M < 100的条件，输出"ERROR!"。
     * 输出：当程序结束时，按原先编号从小到大的顺序，输出剩余的人的编号，多个编号之间以英文逗号分隔。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int m = scanner.nextInt();
            if (m <= 1 || m >= 100) {
                System.out.println("ERROR!");
                continue;
            }
            List<Integer> sortNumberList = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                sortNumberList.add(i);
            }
            List<Integer> resultList = findLastRemainingNumbers(sortNumberList,m);
            Collections.sort(resultList);
            List<String> result = resultList.stream().map(String::valueOf).collect(Collectors.toList());
            System.out.println(String.join( ",", result));
        }
    }

    /**
     * 递归实现 约瑟夫问题
     * @param sortNumberList
     * @param m
     * @return
     */
    public static List<Integer> findLastRemainingNumbers(List<Integer> sortNumberList,int m) {
        for (int i = 0; i <= sortNumberList.size(); i++) {
            if (i == m) {
                List<Integer> tempList = new ArrayList<>(sortNumberList.subList(m, sortNumberList.size()));
                // 将原数组从0 到 m-1 的元素添加到新数组中末尾
                tempList.addAll(sortNumberList.subList(0, m - 1));
                return findLastRemainingNumbers(tempList, m);
            }
        }
        return sortNumberList;
    }
}
