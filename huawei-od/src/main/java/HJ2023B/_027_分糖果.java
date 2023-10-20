package HJ2023B;

import sun.jvm.hotspot.debugger.win32.coff.WindowsNTSubsystem;

import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 题目描述
 * 小明从糖果盒中随意抓一把糖果，每次小明会取出一半的糖果分给同学们。当糖果不能 平均分配时，小明可以选择从糖果盒中(假设盒中糖果足够)取出一个糖果或放回一个糖果小 明最少需要多少次(取出、放回和平均分配均记一次)
 * ，能将手中糖果分至只剩一颗。
 * 输入描述
 * 抓取的糖果数 (<10000000000) : 15 输出描述 最少分至一颗糖果的次数: 5
 * 用例
 *  输入：15
 *  输出： 5
 *     1，15 + 1 = 16
 *     2，16/2= 8；
 *     3，8/2=4；
 *     4，4/2=2；
 *     5，2/2=1；
 *  输入：3
 *  输出：2
 *
 *  输入：6
 *  输出：4
 *
 *  题目分析：
 *      要平分到最后一个，必须要先凑到2的n次方来才是最有的解法
 *
 */
public class _027_分糖果 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            System.out.println(divedCount(Integer.parseInt(sc.nextLine())));
        }

    }

    /**
     * 求解糖果
     * @param total
     * @return
     */
    public static int divedCount(int total) {
        double pow = Math.log(total) / Math.log(2);
        int powInt = (int) pow;
        double minSize = Math.pow(2, powInt);
        if (minSize == total) {
            return powInt;
        }
        double maxSize = Math.pow(2, powInt + 1);
        if (maxSize - total > total - minSize) {
            powInt += total - minSize;
        } else {
            powInt += maxSize - total;
            powInt++;
        }
        return powInt;
    }



}
