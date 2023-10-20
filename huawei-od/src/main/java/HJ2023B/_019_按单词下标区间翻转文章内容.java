package HJ2023B;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 给定一段英文文章片段，由若干单词组成，单词间以空格间隔，单词下标从0开始。
 * 请翻转片段中指定区间的单词顺序并返回翻转后的内容。
 *
 * 例如给定的英文文章片段为"I am a developer"，翻转区间为[0,3]，则输出“developer a am I”。
 *
 * String reverseWords(String s, int start, int end)
 *
 * 输入描述
 * 使用换行隔开三个参数
 *
 * 第一个参数为英文文章内容即英文字符串
 * 第二个参数为待翻转内容起始单词下标
 * 第三个参数为待翻转内容最后一个单词下标
 * 输出描述
 * 翻转后的英文文章片段所有单词之间以一个半角空格分隔进行输出。
 *
 * 备注
 * 英文文章内容首尾无空格
 *
 * 用例1
 * 输入
 *
 * I am a developer.
 * 1
 * 2
 * 输出
 *
 * I a am developer.
 * 用例2
 * 输入
 *
 * hello world
 * -1
 * 1
 * 输出
 *
 * world hello
 * 说明
 *
 * 下标小于0时，从第一个单词开始
 *
 * 用例3
 * 输入
 *
 * I am a developer
 * 0
 * 5
 * 输出
 *
 * developer a am I
 * 说明
 *
 * 下标大于实际单词个数，则按最大下标算
 *
 * 用例4
 * 输入
 *
 * I am a developer
 * -2
 * -1
 * 输出
 *
 * I am a developer
 * 说明
 *
 * 翻转区间无效时，不做翻转
 *
 * 解题思路
 * 这个题目的解题思路如下：
 *
 * 读取用户输入的英文文章片段、待翻转内容的起始单词下标和最后一个单词下标。
 * 将英文文章片段拆分为单词数组。
 * 对起始下标和结束下标进行边界检查，确保它们在有效范围内。
 * 使用双指针法翻转指定区间的单词顺序：
 * 初始化两个指针，一个指向待翻转区间的起始位置（i），另一个指向待翻转区间的结束位置（j）。
 * 当 i 小于 j 时，交换 words[i] 和 words[j] 的值，然后将 i 向右移动一位，将 j 向左移动一位。重复此过程，直到 i 大于等于 j。
 * 将翻转后的单词数组连接为字符串，并输出。
 * 这个解题思路可以确保在给定的英文文章片段中，指定区间的单词顺序被正确翻转。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129508634
 */
public class _019_按单词下标区间翻转文章内容 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String words = sc.nextLine();
        int start = sc.nextInt();
        int end = sc.nextInt();
        overturnWord(start,end,words);

    }

    public static void overturnWord(int start,int end,String words){
        String[] wordList = words.split(" ");
        if (start <= 0) {
            start = 0;
        }
        if (end >= wordList.length) {
            end = wordList.length - 1;
        }

        while (start < end){
            String temp = wordList[start];
            wordList[start] = wordList[end];
            wordList[end] = temp;
            start++;
            end--;
        }
        System.out.println(Arrays.stream(wordList).toArray().toString().replace("[","").replace("]","").replace(",",""));
    }
}
