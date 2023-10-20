package 简单;

import java.util.Scanner;

/**
 * 描述
 * 把m个同样的苹果放在n个同样的盘子里，允许有的盘子空着不放，问共有多少种不同的分法？
 * 注意：如果有7个苹果和3个盘子，（5，1，1）和（1，5，1）被视为是同一种分法。
 *
 * 数据范围：0≤m≤10 ，1≤n≤10 。
 *
 * 输入描述：
 * 输入两个int整数
 *
 * 输出描述：
 * 输出结果，int型
 *
 * 示例1
 * 输入：
 * 7 3
 * 复制
 * 输出：
 * 8
 */
public class _HJ061_放苹果 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){
            int m = sc.nextInt();
            int n = sc.nextInt();
            System.out.println(putApple(m,n));
        }
    }

    public static int putApple(int m ,int n){
        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n ; j++) {
                if(i < j){
                    dp[i][j] = dp[i][j-1];
                }else {
                    dp[i][j] = dp[i][j-1] + dp[i-j][j];
                }
            }
        }
        return dp[m][n];
    }

    public static int getCount(int m, int n){
        if (m == 0 || n == 1) {
            return 1;
        }
        if (n > m) {
            return getCount(m, n - 1);
        } else {
            return getCount(m, n - 1) + getCount(m - n, n);
        }
    }
}
