package HJ2023B;

import java.util.Scanner;

/**
 * 题目描述：
 *  A，B两个人把苹果分为两堆，A希望按照她的计算规则等分苹果，他的计算规则是按照二进制加法计算，并且不计算进位，
 *  12+5=9（1100+0101=9）， B的计算规则是十进制加法，包括正常进位，B希望在满足A的情况下获取苹果重量最多。
 *  输入苹果的数量和每个苹果重量，输出满足A的情况下B获取的苹果总重量。如果无法满足A的要求，输出-1。
 *  数据范围： 1《=总苹果数量 <=20000
 *         1 《= 每个苹果重量 《=10000
 * 输入描述：
 *    输入第一行是苹果数量：3
 *    输入第二行是每个苹果重量：3 5 6
 * 输出描述：
 *    输入第一行是BB获取的苹果总重量：11
 *
 *    用例1
 *    输入
 *       3
 *       3 5 6
 *    输出：
 *       11
 *
 *      用例2：
 *      输入：
 *      8
 *      7258 6579 2602 6716 3050 3564 5396 1773
 *      输出：
 *        35165
 *
 *
 */
public class _001_分苹果 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int[] wight = new int[number];
        for (int i = 0; i < number; i++) {
            wight[i] = sc.nextInt();
        }
        solution(number,wight);
    }

    public static void solution(int number,int[] wightArr){
        int total = 0;
        int min = Integer.MAX_VALUE;
        // 1，输入苹果的重量做异或操作，0^0=0 1^0=1 0^1=1 1^1=0操作 且取所有苹果重量里面最小的
        for (int x :wightArr) {
            total = total^x;
            if(x < min){
                min = x;
            }
        }

        if (total != 0) {
            //2，做完异或操作后最后不为0情况下，证明不符合A的分苹果方案
            System.out.println(-1);
        } else {
            int sum = 0;
            for (int x : wightArr) {
                sum += x;
            }
            //3，苹果总数减去A所分到的苹果重量 = B分到的最大重量值
            System.out.println(sum - min);
        }
    }

}
