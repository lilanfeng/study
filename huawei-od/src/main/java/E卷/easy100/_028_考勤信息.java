package E卷.easy100;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/6 06:43
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _028_考勤信息 {
    /**
     * 考勤信息
     * 题目描述
     * 公司用一个字符串来表示员工的出勤信息
     * absent：缺勤
     * late：迟到
     * leaveearly：早退
     * present：正常上班
     * 现需根据员工出勤信息，判断本次是否能获得出勤奖，能获得出勤奖的条件如下：
     * 缺勤不超过一次；
     * 没有连续的迟到/早退；
     * 任意连续7次考勤，缺勤/迟到/早退不超过3次。
     * 输入描述
     * 用户的考勤数据字符串
     * 记录条数 >= 1；
     * 输入字符串长度 < 10000；
     * 不存在非法输入；
     * 如：
     * 2
     * present
     * present absent present present leaveearly present absent
     * 输出描述
     * 根据考勤数据字符串，如果能得到考勤奖，输出”true”；否则输出”false”，
     * 对于输入示例的结果应为：
     * true false
     *
     * 示例1
     * 输入
     *
     * 2
     * present
     * present present
     * 输出
     * true true
     * 说明
     *
     * 无
     *
     * 示例2
     * 输入
     * 2
     * present
     * present absent present present leaveearly present absent
     * 输出
     * true false
     * 说明
     *
     * 解题思路
     * 这个题目要求我们根据员工的出勤信息字符串来判断员工是否可以获得出勤奖。根据题目的描述，有三个条件来判断员工是否能够获得出勤奖。、
     *
     * 1. 出勤奖条件
     * 员工要获得出勤奖，必须满足以下三个条件：
     * 缺勤不超过一次：即字符串中 absent 出现的次数不能超过1次。
     * 没有连续的迟到/早退：即字符串中不能有 late 和 leaveearly 这两种情况连续出现。
     * 任意连续7次考勤中，缺勤/迟到/早退不超过3次：在任何7次连续考勤记录中，absent、late 和 leaveearly 的总次数不能超过3次。
     * 2. 示例分析
     * 示例1：
     * 输入 2 present \n present present：两个员工的考勤记录分别是 present 和 present present。
     * 第一个员工没有缺勤、迟到或早退，因此符合所有条件，输出 true。
     * 第二个员工也符合所有条件，输出 true。
     * 示例2：
     * 输入 2 present \n present absent present present leaveearly present absent：两个员工的考勤记录分别是 present 和 present absent
     * present present leaveearly present absent。
     * 第一个员工没有问题，输出 true。
     * 第二个员工的考勤记录包含了两个 absent，因此违反了第一个条件（缺勤不超过一次），所以输出 false。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<String> strList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            strList.add(scanner.nextLine());
        }
        scanner.close();
        System.out.println(check(strList));
    }

    /**
     *  考勤信息
     * @param strList
     * @return
     */
    public static String check(List<String> strList) {
        StringJoiner sb = new StringJoiner(" ");
        for (String str : strList) {
            String[] strArr = str.split(" ");
            String result = "true";
            for (int i = 0; i < strArr.length; i++) {
                /**
                 *       * 缺勤不超过一次：即字符串中 absent 出现的次数不能超过1次。
                 *      * 没有连续的迟到/早退：即字符串中不能有 late 和 leaveearly 这两种情况连续出现。
                 *
                 */
                String s = strArr[i];
                if ("absent".equals(s)) {
                    result = "false";
                    break;
                }
                if ("late".equals(s) || "leaveearly".equals(s)) {
                    if (i > 0 && ("late".equals(strArr[i - 1]) || "leaveearly".equals(strArr[i - 1]))) {
                        result = "false";
                        break;
                    }
                }
                // 任意连续7次考勤中，缺勤/迟到/早退不超过3次：在任何7次连续考勤记录中，absent、late 和 leaveearly 的总次数不能超过3次。
                if(i > 6){
                    int continuousCount = 0;
                    for (int j = i - 6; j <= i; j++) {
                        String s1 = strArr[j];
                        if ("absent".equals(s1) || "late".equals(s1) || "leaveearly".equals(s1)) {
                            continuousCount++;
                        }
                    }
                    if(continuousCount > 3){
                        result = "false";
                        break;
                    }
                }
            }
            sb.add(result);
        }
        return sb.toString();
    }
}
