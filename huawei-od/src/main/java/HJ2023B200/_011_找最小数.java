package HJ2023B200;

import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 *
 * 找最小数
 * 知识点贪心
 * 时间限制：1s 空间限制：32MB 限定语言：不限
 *
 * 题目描述：
 * 给一个正整数NUM1，计算出新正整数NUM2，NUM2为NUM1中移除N位数字后的结果，需要使得NUM2的值最小。
 * 输入描述：
 * 1.输入的第一行为一个字符串，字符串由0-9字符组成，记录正整数NUM1，NUM1长度小于32。
 * 2.输入的第二行为需要移除的数字的个数，小于NUM1长度。
 *
 * 如：
 * 2615371
 * 4
 * 输出描述：
 * 输出一个数字字符串，记录最小值NUM2。
 * 如：131
 *
 * 示例1
 * 输入：
 * 2615371
 * 4
 * 输出：
 * 131
 * 说明：
 * 移除2、6、5、7这四个数字，剩下1、3、1按原有顺序排列组成131，为最小值
 * 文章知识点与官方知识档案匹配，可进一步学习相关知识
 * 算法技能树首页概览52096 人正在系统学习中
 *
 * ————————————————
 * 版权声明：本文为CSDN博主「若博豆」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_34465338/article/details/125250103
 *
 */
public class _011_找最小数 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){
            System.out.println(findSmall(sc.next(),sc.nextInt()));
        }
    }

    public static String findSmall(String target,int size){
        if(size <= 0){
            return target;
        }
        char[] numberStr = target.toCharArray();

        int idx = 0;
        while (idx < numberStr.length){
            if(Integer.valueOf(numberStr[idx]) > Integer.valueOf(numberStr[idx+1])){
                break;
            }
            idx++;
        }

        target = target.substring(0,idx) + target.substring(idx+1);

        return findSmall(target,size -1);
    }

}
