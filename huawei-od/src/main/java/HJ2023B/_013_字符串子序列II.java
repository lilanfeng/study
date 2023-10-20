package HJ2023B;

import java.util.Scanner;

/**
 * 题目描述
 * 给定字符串 target和 source，判断 target是否为 source 的子序列。你可以认为target和 source
 * 中仅包含英文小写字母。
 *
 * 字符串 source 可能会很长（长度~=500,000），而 target是个短字符串（长度<=100)。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 *
 * （例如，”abc”是”aebycd”的一个子序列，而”ayb”不是）。
 *
 * 请找出最后一个子序列的起始位置。
 *
 * 输入描述
 * 第一行为target，短字符串（长度 <=100）
 *
 * 第二行为source，长字符串（长度 ~= 500,000）
 *
 * 输出描述
 * 最后一个子序列的起始位置，即最后一个子序列首字母的下标
 *
 * ACM输入输出模式
 * 如果你经常使用Leetcode,会知道letcode是不需要编写输入输出函数的。但是华为OD机考使用的是 ACM 模式，需要手动编写输入和输出。
 *
 * 所以最好在牛-客上提前熟悉这种模式。例如C++使用cin/cout,python使用input()/print()。JavaScript使用node的readline()和console.log()。Java
 * 使用sacnner/system.out.print()
 *
 * 用例
 * 输入
 *
 * abc
 * abcaybec
 * 1
 * 2
 * 输出
 *
 * 3
 * 1
 * 说明
 *
 * 这里有两个abc的子序列满足，取下标较大的，故返回3。
 * 备注 若在source中找不到target，则输出-1。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/130010116
 */
public class _013_字符串子序列II {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String target = sc.nextLine();
        String source = sc.nextLine();
        int targetIndex = target.length() -1;
        int sourceIndex = source.length() -1;
        while (sourceIndex >= 0 && targetIndex >= 0){
            if(source.charAt(sourceIndex) == target.charAt(targetIndex)){
                targetIndex--;
            }
            sourceIndex--;
        }
        int result = -1;
        if (targetIndex < 0) {
            result = sourceIndex + 1;
        }
        System.out.println(result);
    }
}
