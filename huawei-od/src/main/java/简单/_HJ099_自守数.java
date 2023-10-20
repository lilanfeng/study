package 简单;

import java.util.Scanner;

/**
 *  描述
 * 自守数是指一个数的平方的尾数等于该数自身的自然数。例如：25^2 = 625，76^2 = 5776，9376^2 = 87909376。请求出n(包括n)以内的自守数的个数
 *
 * 数据范围：
 * 1≤n≤10000
 *
 *
 * 输入描述：
 * int型整数
 *
 * 输出描述：
 * n以内自守数的数量。
 *
 * 示例1
 * 输入：
 * 6
 * 输出：
 * 4
 * 说明：
 * 有0，1，5，6这四个自守数
 * 示例2
 * 输入：
 * 1
 * 输出：
 * 2
 * 说明：
 * 有0, 1这两个自守数
 */
public class _HJ099_自守数 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){
            int number = sc.nextInt();
            int count = 1;
            for (int i = 1; i <= number; i++) {
                int total = i*i;
                String source = String.valueOf(total);
                String target = String.valueOf(i);
                if(source.endsWith(target) ){
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
