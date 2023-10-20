package 简单;

import java.util.Scanner;

/**
 * 描述
 * 输入 n 个整型数，统计其中的负数个数并求所有非负数的平均值，结果保留一位小数，如果没有非负数，则平均值为0
 * 本题有多组输入数据，输入到文件末尾。
 *
 * 数据范围：
 * 1
 * ≤
 * �
 * ≤
 * 50000
 *
 * 1≤n≤50000  ，其中每个数都满足
 * ∣
 * �
 * �
 * �
 * ∣
 * ≤
 * 1
 * 0
 * 6
 *
 * ∣val∣≤10
 * 6
 *
 * 输入描述：
 * 输入任意个整数，每行输入一个。
 *
 * 输出描述：
 * 输出负数个数以及所有非负数的平均值
 *
 * 示例1
 * 输入：
 * -13
 * -4
 * -7
 * 复制
 * 输出：
 * 3
 * 0.0
 * 复制
 * 示例2
 * 输入：
 * -12
 * 1
 * 2
 * 复制
 * 输出：
 * 1
 * 1.5
 */
public class _HJ105_记负均正二 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = 0;
        int count = 0;
        float sum = 0;
        while (sc.hasNextInt()){
            int temp = sc.nextInt();
            total++;
            if(temp < 0){
                count++;
            }else{
                sum += temp;
            }
        }
        System.out.println(count);
        float result = 0.0f;
        if ((total - count) != 0) {
            result = sum / (total - count);
        }
        System.out.printf("%.1f\n",result);

    }
}
