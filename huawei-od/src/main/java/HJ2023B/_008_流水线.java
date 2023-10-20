package HJ2023B;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  题目描述：
 *  一个工厂有m条流水线，来并行完成n个独立的作业，该工厂设置了一个调度系统，在安排作业时，总是优先执行处理时间最短的作业。
 *  现在给定流水线个数m，需要完成的作业数n，每个作业的处理时间分别为t1，t2，...tn，请你编程计算处理完成所有作业的耗时时间为多少？
 *  当n > m时候，首先处理时间短的m个作业进入流水线，其它的等待，当某个作业完成时候，依次从剩余的作业中取处理时间最短的进入处理。
 *  输入描述：
 *  第一行为2个整数（采用空格分隔），分别表示流水线个数m和作业数n；
 *  第二行输入n个整数（采用空格分隔），表示每个作业的处理时长
 *  0< m,n<100 , 0< t1  tn< 100.
 *  注意：保证输入都是合法的
 *
 * 输出描述：
 * 输出处理完成所有作业的总时长。
 *
 * 用例：
 * 输入：
 * 3 5
 * 8 4 3 2 10
 *
 *输出：
 * 13
 */
public class _008_流水线 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] costTime = new int[n];
        for (int i = 0; i < n; i++) {
            costTime[i] = sc.nextInt();
        }
        findCostTime(m,n,costTime);

    }

    public static void findCostTime(int m,int n,int[] costTime){
        Arrays.sort(costTime);
        if(m >= n){
            System.out.println(costTime[n-1]);
            return;
        }
        int siz =( n/m ) + 1;
        int[][] dp = new int[siz][m];
        int index = 0;
        for (int i = 0; i < siz; i++) {
            for (int j = 0; j < m; j++) {
                if(index >= n){
                    break;
                }
                if(i == 0){
                    dp[i][j] = costTime[index];
                }else {
                    dp[i][j] = costTime[index] + dp[i-1][j];
                }

                index++;
            }
        }
        System.out.println(dp[n/m][(n%m) -1]);
    }
}
