package 简单;

import java.util.Scanner;

/**
 * 描述
 * 将一个字符串中所有的整数前后加上符号“*”，其他字符保持不变。连续的数字视为一个整数。
 *
 *
 * 数据范围：字符串长度满足 1≤n≤100
 * 输入描述：
 * 输入一个字符串
 *
 * 输出描述：
 * 字符中所有出现的数字前后加上符号“*”，其他字符保持不变
 *
 * 示例1
 * 输入：
 * Jkdi234klowe90a3
 * 输出：
 * Jkdi*234*klowe*90*a*3*
 */
public class _HJ096_表示数字 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            solution(sc.nextLine());
        }
    }
    public static void solution(String source){
        boolean isNumber = false;
        for (int i = 0; i < source.length(); i++) {
            Character temp = source.charAt(i);
            if(isNumber(temp.toString())){
                if(!isNumber){
                    System.out.print("*");
                    isNumber = true;
                }
                System.out.print(temp);
            }else {
                if(isNumber){
                    System.out.print("*");
                    isNumber = false;
                }
                System.out.print(temp);
            }
        }
        if(isNumber){
            System.out.print("*");
        }
    }

    public static boolean isNumber(String temp){
        try {
            int number = Integer.parseInt(temp);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
