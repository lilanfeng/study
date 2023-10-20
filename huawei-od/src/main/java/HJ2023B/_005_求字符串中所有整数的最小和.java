package HJ2023B;

import java.util.Scanner;

/**
 * 题目描述
 *   输入字符串s，输出s中包含所有整数的最小和。
 *   说明：
 *   字符串s，只包含a-zA-Z+-； 合法的整数包括
 *   1，正整数 一个或者多个0-9组成，如0 2 3 002 102 2， 负整数 负号 - 开头，数字部分由一个或者多个0-9组成，如-0 -012 -23 -00023
 *
 *   输入描述：
 *    包含数字的字符串
 *
 *    输出描述：
 *    所有整数的最小和
 *
 *    用例：
 *     输入： bb12-34aa
 *     输出： -31
 *     说明：1 + 2 + （-34） = -31
 *  说明：
 *  获取正整数时候取一位是得到是最小的，负数时候取最多位获取是最小的。
 *
 */
public class _005_求字符串中所有整数的最小和 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            findMinSum(sc.next());
        }
    }

    public static void findMinSum(String str){
        String[] numArr = str.split("[^\\d-]+");
        int res = 0;
        for (String num :numArr){
            if(num.isEmpty()){
                continue;
            }
            if(!num.contains("-")){
                res += getSumNum(num);
            } else {
                boolean isNeg = num.startsWith("-");
                String[] negNumArr = num.split("-");
                for (int i = 0; i < negNumArr.length; i++) {
                    if(negNumArr[i].isEmpty()){
                        continue;
                    }
                    int temp = Integer.parseInt(negNumArr[i]);
                    if(i == 0){
                        res += isNeg ? -temp : getSumNum(negNumArr[i]);
                    } else {
                        res -= temp;
                    }
                }
            }
        }
        System.out.println(res);
    }

    private static int getSumNum(String num){
        int sum = 0;
        for (char c :num.toCharArray()) {
            sum += Character.getNumericValue(c);
        }
        return sum;
    }
}
