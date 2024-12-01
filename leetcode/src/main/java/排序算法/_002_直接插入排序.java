package 排序算法;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 直接插入排序
 * 1.基本思想：
 * 每步将一个待排序的记录，按其顺序码大小插入到前面已经排序的字序列的合适位置
 * （从后向前找到合适位置后），
 * 直到全部插入排序完为止
 *
 * 2.实现过程：
 * 原数组值为：
 * 32 , 43, 23, 13, 5
 * 第一轮比较：将第一个数和第二个数排序，然后构成一个有序序列，得到结果：
 * 32 , 43, 23, 13, 5
 * 第二轮比较：将第三个数插入，构成新的有序序列，得到结果：
 * 23 , 32, 43, 13, 5
 * 第三轮比较：将第四个数插入，构成新的有序序列，得到结果为：
 * 13 , 23, 32, 43, 5
 * 第四轮比较：将第五个数插入，构成新的有序序列，得到结果为：
 * 5 , 13, 23, 32, 43
 */
public class _002_直接插入排序 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = sc.nextInt();
        }
        sorting(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sorting(int[] array){
        int length = array.length;
        int insertNum;
        for (int i = 1; i < length; i++) {
            insertNum = array[i];
            int j = i -1;
            while (j >= 0 && array[j] > insertNum){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = insertNum;
        }
    }


}
