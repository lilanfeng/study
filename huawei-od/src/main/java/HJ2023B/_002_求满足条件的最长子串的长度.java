package HJ2023B;

import java.util.Scanner;

/**
 * 题目描述
 *   给定一个字符串，只包含字母和数字，按要求找出字符串中的最长（连续）子串的长度，字符串本身是其最长的子串，子串要求：
 *   1，只包含1个字母（a-z，A-Z），其余必须是数字；
 *   2，字母可以在子串中的任意位置；
 *   如果找不到满足要求的子串，如全是字母或全是数字，则返回-1。
 *   输入描述：
 *   字符串（只包含字母和数字）
 *
 *   输出描述
 *     子串的长度
 *     用例
 *     输入： abC124ACb  输出：4
 *     输入：a5   输出：2
 *     输入 aBB9   输出：2
 *     输入：abcdef  输出：-1
 */
public class _002_求满足条件的最长子串的长度 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()){
            findLongest(sc.nextLine());
        }
    }

    public static void findLongest(String str){
        if(str.matches("[0-9]+") || str.matches("[a-zA-Z]+")){
            System.out.println(-1);
            return;
        }
        //最大区间长度
        int max = -1;
        //左指针
        int left = 0;
        //右指针
        int right = 0;
        //最后字符串出现位置
        int lastLetterIndex = 0;

        while (right < str.length()){
            if(Character.isLetter(str.charAt(right))){
                if(right > left){
                    max = Math.max(max,right - left);
                    if(right - left == 1){
                        left = right;
                    }else {
                       left = lastLetterIndex+1;
                    }
                }
                lastLetterIndex = right;
            }
            right++;
        }
        max = Math.max(max,right -left);
        System.out.println(max);
    }
}
