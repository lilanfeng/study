package 简单;

import java.util.Scanner;

/**
 * 描述
 * 请计算n*m的棋盘格子（n为横向的格子数，m为竖向的格子数）从棋盘左上角出发沿着边缘线从左上角走到右下角，总共有多少种走法，要求不能走回头路，即：只能往右和往下走，不能往左和往上走。
 *
 * 注：沿棋盘格之间的边缘线行走
 *
 * 数据范围：
 * 1≤n,m≤8
 *
 *
 * 输入描述：
 * 输入两个正整数n和m，用空格隔开。(1≤n,m≤8)
 *
 * 输出描述：
 * 输出一行结果
 *
 * 示例1
 * 输入：
 * 2 2
 * 输出：
 * 6
 */
public class _HJ91_走方格的方案数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(move(a,b));
    }

    public static int solution(int a,int b){
        if( a == 1 && b == 1){
            return 2;
        }
        if(a == 1 || b == 1){
          return a*b + 1;
        }
        return solution(a-1,b) + solution(a,b-1);
    }

    public static int move(int a,int b){
        int[][] dp = new int[a+1][b+1];
        for (int i = 0; i <= a; i++) {
            for (int j = 0; j <= b; j++) {
                if(i == 0 || j == 0){
                    dp[i][j] = 1;
                }else {
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                }
            }

        }
        return dp[a][b];
    }
}
