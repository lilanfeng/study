package 简单;

import java.util.Scanner;

/**
 * 描述
 * 首先输入要输入的整数个数n，然后输入n个整数。输出为n个整数中负数的个数，和所有正整数的平均值，结果保留一位小数。
 * 0即不是正整数，也不是负数，不计入计算。如果没有正数，则平均值为0。
 *
 * 数据范围：1≤n ≤2000  ，输入的整数都满足  ∣val∣≤1000
 * 输入描述：
 * 首先输入一个正整数n，
 * 然后输入n个整数。
 *
 * 输出描述：
 * 输出负数的个数，和所有正整数的平均值。
 *
 * 示例1
 * 输入：
 * 11
 * 1 2 3 4 5 6 7 8 9 0 -1
 * 复制
 * 输出：
 * 1 5.0
 * 示例2
 * 输入：
 * 3
 * 0 0 0
 * 复制
 * 输出：
 * 0 0.0
 */
public class _HJ097_记负均正 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int divCount = 0;
        int count = 0;
        float sum = 0;
        for (int i = 0; i < number; i++) {
            int temp = sc.nextInt();
            if(temp < 0){
                count++;
            }else if(temp > 0){
                sum += temp;
                divCount++;
            }
        }
        System.out.println(count);
        float result = 0.0f;
        if (divCount != 0) {
            result = sum / divCount;
        }
        System.out.printf("%.1f\n",result);

    }
}
