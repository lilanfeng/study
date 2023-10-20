package 简单;

import java.util.Scanner;

/**
 * 描述
 * Catcher是MCA国的情报员，他工作时发现敌国会用一些对称的密码进行通信，比如像这些ABBA，ABA，A，123321，但是他们有时会在开始或结束时加入一些无关的字符以防止别国破解。比如进行下列变化
 * ABBA->12ABBA,ABA->ABAKK,123321->51233214　。因为截获的串太长了，而且存在多种可能的情况（abaaab可看作是aba,
 * 或baaab的加密形式），Cathcer的工作量实在是太大了，他只能向电脑高手求助，你能帮Catcher找出最长的有效密码串吗？
 *
 * 数据范围：字符串长度满足 1≤n≤2500
 * 输入描述：
 * 输入一个字符串（字符串的长度不超过2500）
 *
 * 输出描述：
 * 返回有效密码串的最大长度
 *
 * 示例1
 * 输入：
 * ABBA
 * 输出：
 * 4
 * 示例2
 * 输入：
 * ABBBA
 * 输出：
 * 5
 * 示例3
 * 输入：
 * 12HHHHA
 * 输出：
 * 4
 */
public class _HJ032_密码截取 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            findCount(sc.nextLine());
        }
    }

    public static void findCount(String str){
        int length = str.length();
        boolean[][] dp = new boolean[length][length];
        int res = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if( str.charAt(j) == str.charAt(i) &&
                        ( i -j <= 2 || dp[j+1][i-1] )){
                    dp[j][i] = true;
                    res = Math.max(res,i - j +1);
                }
            }
        }
        System.out.println(res);
    }
}
