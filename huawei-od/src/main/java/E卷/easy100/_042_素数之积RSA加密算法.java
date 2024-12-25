package E卷.easy100;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/12 06:25
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _042_素数之积RSA加密算法 {
    /**
     * 题目描述
     * RSA加密算法在网络安全世界中无处不在，它利用了极大整数因数分解的困难度，数据越大，安全系数越高，给定一个32位正整数，请对其进行因数分解，找出是哪两个素数的乘积。
     *
     * 输入描述
     * 一个正整数num，0 < num <= 2147483647
     *
     * 输出描述
     * 如果成功找到，以单个空格分割，从小到大输出两个素数，分解失败，请输出-1, -1
     *
     * 用例
     * 输入	15
     * 输出	3 5
     * 输入	27
     * 输出	-1 -1
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int num = sc.nextInt();
            int[] res = getPrime(num);
            System.out.println(res[0] + " " + res[1]);
        }
        sc.close();
    }

    /**
     * 素数因子分解
     * @param num  正整数num，0 < num <= 2147483647
     * @return 成功找到，以单个空格分割，从小到大输出两个素数，分解失败，请输出-1, -1
     */
    public static int[] getPrime(int num) {
        int[] res = new int[2];
        Arrays.fill(res,-1);
        if(isPrime(num)){
            return res;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                if (isPrime(i) && isPrime(num/i)) {
                    res[0] = i;
                    res[1] = num / i;
                    break;
                }
            }
        }
        return res;
    }

    /**
     *  判断是否为素数  一个大于1的自然数，除了1和它本身以外不再有其他因数的数称为素数。换句话说，素数只有两个正因数：1和它本身。
     * @param num
     * @return
     */
    private static boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
