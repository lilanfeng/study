package 简单;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 描述
 * 输入整型数组和排序标识，对其元素按照升序或降序进行排序
 *
 * 数据范围： 1≤n≤1000  ，元素大小满足
 * 0≤val≤100000
 * 输入描述：
 * 第一行输入数组元素个数
 * 第二行输入待排序的数组，每个数用空格隔开
 * 第三行输入一个整数0或1。0代表升序排序，1代表降序排序
 *
 * 输出描述：
 * 输出排好序的数字
 *
 * 示例1
 * 输入：
 * 8
 * 1 2 4 9 3 55 64 25
 * 0
 * 输出：
 * 1 2 3 4 9 25 55 64
 * 示例2
 * 输入：
 * 5
 * 1 2 3 4 5
 * 1
 * 输出：
 * 5 4 3 2 1
 */
public class _HJ101_输入整形数组和排序标识_按照要求输出 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int[] sourceArr = new int[number];
        for (int i = 0; i < number; i++) {
            sourceArr[i] = sc.nextInt();
        }
        int sort = sc.nextInt();
        //升序
        Arrays.sort(sourceArr);
        if(sort == 0){
            for (int i = 0; i < number; i++) {
                System.out.print(sourceArr[i] + " ");
            }
        }else if(sort == 1){
            for (int i = number -1; i >= 0; i--) {
                System.out.print(sourceArr[i] + " ");
            }
        }
    }
}
