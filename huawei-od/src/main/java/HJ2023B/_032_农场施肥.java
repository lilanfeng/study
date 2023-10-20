package HJ2023B;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 题目描述
 * 某农场主管理了一大片果园，fields[i]表示不同果林的面积，单位:m^2，现在要为所有 的果林施肥且必须在 n 天之内完成，否则影响收成。小布是果林的工作人员，他每次选择一 片果林进行施肥，且一片果林施肥完后当天不再进行施肥作业。
 * 假设施肥机的能效为 k，单位: m^2/day，请问至少租赁能效 k 为多少的施肥机才能确 保不影响收成? 如果无法完成施肥任务，则返回-1。
 * 输入描述
 * 第一行输入为 m 和 n，m 表示 fields 中的元素个数，n 表示施肥任务必须在 n 天内 (含 n 天)完成
 * 第二行输入为 fields，fields[i]表示果林 i 的面积，单位: m^2 输出描述
 * 对于每组数据，输出最小施肥机的能效 k ，无多余空格.
 * 备注
 * 1 ≤ fields.length ≤ 10^4 。1≤n≤10^9
 * 。1≤ fields[i] ≤ 10^9
 * 用例
 *   输入：5 7
 *        5 7 9 15 10
 *   输出： 9
 *
 *  说明：
 *
 *   输入：3 1
 *       2 3 4
 *   输出： -1
 *
 *说明：由于一天最多完成一片园林的施肥，目前有三个，所有必须要3天才能完成
 *
 *
 */
public class _032_农场施肥 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){
            int number = sc.nextInt();
            int day = sc.nextInt();
            int[] fields = new int[number];
            for (int i = 0; i < number; i++) {
                fields[i] = sc.nextInt();
            }
            findK(fields,day);
        }
    }

    public static void findK(int[] fields, int day){
        if (day < fields.length) {
            System.out.println("-1");
            return;
        }
        Arrays.sort(fields);
        int max = fields[fields.length-1];
        int min = fields[0];
        int resultK = 0;
        while (min <= max){
            int k = (min + max) / 2;
            int speed = checkSpeed(k,fields);
            if (speed > day) {
                min = k + 1;
            } else {
                resultK = k;
                max = k-1;
            }
        }
        System.out.println(resultK);
    }

    public static int checkSpeed(int k,int[] fields){
        int speed = 0;
        for (int i = 0; i < fields.length; i++) {
            int field = fields[i];
            if(field <= k){
                speed++;
            }else {
                speed += Math.ceil(field/(double)k);
            }
        }
        return speed;
    }


}
