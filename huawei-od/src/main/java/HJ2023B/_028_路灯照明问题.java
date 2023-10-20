package HJ2023B;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 题目描述
 * 在一条笔直的公路上安装了 N 个路灯，从位置 0 开始安装，路灯之间间距固定为 100 米.每个路灯都有自己的照明半径，请计算第一个路灯和最后一个路灯之间，无法照明的区 间的长度和
 * 输入描述
 * 第一行为一个数 N，表示路灯个数，1<=N<=100000 第二行为 N 个空格分隔的数，表示
 * 路灯的照明半径，1<=照明半径<=100000*100 输出描述
 *   第一个路灯和最后一个路灯之间，无法照明的区间的长度和
 *   用例
 *      输入： 2
 *      50 50
 *      输出： 0
 *  说明： 路灯1覆盖0-50  路灯2覆盖 50-100  区间是0-100
 *
 *
 *     输入：4
 *          50 70 20 70
 *     输出： 20
 *
 *     说明： 区间是0-300，
 *     路灯1：0-50，路灯2：30-170 路灯3，180-220 路灯4 ： 230-300
 */
public class _028_路灯照明问题 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            findDistance(Integer.parseInt(sc.nextLine()),
                    Arrays.stream(sc.nextLine().split(" "))
                            .mapToInt(Integer::parseInt).toArray());
        }
    }

    /**
     *
     * @param number
     * @param arr
     */
    public static void findDistance(int number,int[] arr){
        int distance = 0;
        int left = 0;
        int right = 0;
        for (int i = 1; i < arr.length; i++) {
            left = (i-1) *100 + arr[i-1];
            right = i*100 - arr[i];
            if(right > left){
                distance = distance + (right - left);
            }
        }
        System.out.println(distance);

    }



}
