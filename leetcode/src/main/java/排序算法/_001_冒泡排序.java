package 排序算法;


import java.util.Arrays;
import java.util.Scanner;

/**
 * 1.排序的定义：
 * 所谓排序算法，即通过特定的算法因式将一组或多组数据按照既定模式进行重新排序。这种新序列遵循着一定的规则，体现出一定的规律，
 * 因此，经处理后的数据便于筛选和计算，大大提高了计算效率。对于排序，我们首先要求其具有一定的稳定性，即当两个相同的元素同时出
 * 现于某个序列之中，则经过一定的排序算法之后，两者在排序前后的相对位置不发生变化。换言之，即便是两个完全相同的元素，它们在排序过程中也是各有区别的，不允许混淆不清。
 * 排序(Sorting) 是计算机程序设计中的一种重要操作，它的功能是将一个数据元素（或记录）的任意序列，重新排列成一个关键字有序的序列。
 *
 * 2.排序的方式：
 * 排序就是把集合中的元素按照一定的次序排序在一起，排序大的分类可以分为两种：内排序和外排序。在排序过程中，全部记录存放在内存，则称为内排序，
 * 如果排序过程中需要使用外存，则称为外排序。下面讲的排序都是属于内排序。
 * 内排序有可以分为以下几类：
 *
 * 插入排序： 直接插入排序、二分法插入排序、希尔排序
 * 选择排序： 简单选择排序、堆排序
 * 交换排序： 冒泡排序、快速排序
 * 归并排序
 * 基数排序
 * 接下来我们就这以上几种排序进行详细讲解
 *
 * 冒泡排序
 * 需求分析：
 *  设置循环次数。
 *  设置开始比较的位数，和结束的位数。
 *  两两比较，将最小的放到前面去。
 *  重复2、3步，直到循环次数完毕。
 */
public class _001_冒泡排序 {

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
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

}
