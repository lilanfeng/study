package 简单;

import java.util.Scanner;

/**
 * 描述
 * 输出 1到n之间 的与 7 有关数字的个数。
 * 一个数与7有关是指这个数是 7 的倍数，或者是包含 7 的数字（如 17 ，27 ，37 ... 70 ，71 ，72 ，73...）
 *
 * 数据范围：
 * 1
 * ≤
 * �
 * ≤
 * 30000
 *
 * 1≤n≤30000
 * 输入描述：
 * 一个正整数 n 。( n 不大于 30000 )
 *
 * 输出描述：
 * 一个整数，表示1到n之间的与7有关的数字个数。
 *
 * 示例1
 * 输入：
 * 20
 * 复制
 * 输出：
 * 3
 * 复制
 * 说明：
 * 输入20，1到20之间有关的数字包括7,14,17共3个。
 */
public class _HJ055_挑7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){
            System.out.println(findCount(sc.nextInt()));
        }
    }

    public static int findCount(int n){
        int count = 0;
        for (int i = 1; i <=  n; i++) {
            if(i % 7 == 0){
                count++;
            }else {
                if(String.valueOf(i).contains("7")){
                    count++;
                }
            }
        }
        return count;
    }
}
