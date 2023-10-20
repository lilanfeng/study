package HJ2023B;

import java.util.List;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 *
 * 题目描述
 * 幼儿园两个班的小朋友在排队时混在了一起，每位小朋友都知道自己是否与前面一位小
 * 朋友同班，请你帮忙把同班的小朋友找出来
 * 小朋友的编号是整数，与前一位小朋友同班用 Y 表示，不同班用 N 表示 学生序号范围(0.999]，如果输入不合法则打印 ERROR。
 * 输入描述
 * 输入为空格分开的小朋友编号和是否同班标志
 * 输出描述 输出为两行，每一行记录一个班小朋友的编号，编号用空格分开，且: 。1.编号需按照升序排列。 。2.若只有一个班的小朋友，第二行为空行。
 * 用例
 *  输入： 1/N 2/Y 3/N 4Y/
 *  输出：1 2
 *       3 4
 *
 *  输入：1/N 2/Y 3/N 4/Y 5/Y
 *  输出：
 *
 */
public class _030_分班问题 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()){
           printSort(sc.nextLine().split(" "));
        }
    }

    public static void printSort(String[] arr){

        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        int classNo = 1;
        for (int i = 0; i < arr.length; i++) {
            String temp = arr[i];
            if(!temp.contains("/")){
                continue;
            }
            String[] strings = temp.split("/");
            String number = strings[0];
            String isClass = strings[1];
            if(i == 0){
                stringBuilder1.append(number).append(" ");
                continue;
            }
            if(isClass.equalsIgnoreCase("Y")){
                if(classNo == 1){
                    stringBuilder1.append(number).append(" ");
                }else {
                    stringBuilder2.append(number).append(" ");
                }

            }else {
                if(classNo == 1){
                    stringBuilder2.append(number).append(" ");
                    classNo = 2;
                }else {
                    stringBuilder1.append(number).append(" ");
                    classNo = 1;
                }
            }
        }

        System.out.println(stringBuilder1.substring(0,stringBuilder1.length()-1));
        if(stringBuilder2.length() > 0){
            System.out.println(stringBuilder2.substring(0,stringBuilder2.length()-1));
        }
    }
}
