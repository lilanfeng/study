package 简单;


import java.util.Locale;
import java.util.Scanner;

/**
 * 3.
 * 进制转换
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
 *
 * 数据范围：保证结果在
 * 1
 * ≤
 * �
 * ≤
 * 2
 * 31
 * −
 * 1
 *
 * 1≤n≤2
 * 31
 *  −1
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 32M，其他语言64M
 * 输入描述：
 * 输入一个十六进制的数值字符串。
 *
 * 输出描述：
 * 输出该数值的十进制字符串。不同组的测试用例用\n隔开。
 *
 * 示例1
 * 输入例子：
 * 0xAA
 * 输出例子：
 * 170
 */
public class _2023081303_十六进制转换 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()){
            String  a = in.next();
            solution1(a);
            solution1(a);
        }
    }

    public static void solution1(String a){
        Integer value = Integer.valueOf(a.substring(2),16);
        System.out.println(value);
    }

    public static void solution(String a){
        int number = 0;
        int length = a.length();

        char[] achars = a.toCharArray();
        int index = 0;
        int total = 0;
        for (int i = length -1; i > 1; i--) {

            char temp = achars[i];
            int value = 0;
            switch (String.valueOf(temp).toUpperCase(Locale.ROOT)){
                case "A":
                    value = 10;
                    break;
                case "B":
                    value = 11;
                    break;
                case "C":
                    value = 12;
                    break;
                case "D":
                    value = 13;
                    break;
                case "E":
                    value = 14;
                    break;
                case "F":
                    value = 15;
                    break;
                default:
                    value = Integer.valueOf(String.valueOf(temp));
            }

            total += value*getIndex(index);
            index++;

        }

        System.out.println(total);
    }
    public static int getIndex(int index){
        if(index == 0){
            return 1;
        }
        int sum = 1;
        for (int i = 0; i < index; i++) {
            sum = sum*16;
        }
        return sum;
    }
}
