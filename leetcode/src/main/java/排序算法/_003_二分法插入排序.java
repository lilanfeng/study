package 排序算法;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  1.基本思想:
 * 基本思想：二分法插入排序的思想和直接插入一样，只是找合适的插入位置的方式不同，这里是按二分法找到合适的位置，可以减少比较的次数。
 *
 * 2.实现过程：
 * 二分法排序其实是一种改进的插入排序，也是通过查找待插入位置来实现排序，这和插入排序是类似的。
 *
 * 算法思想，在插入第i个元素时，对前面的0～i-1元素进行折半，先跟他们中间的那个元素比，如果小，则对前半部分再进行折半，否则对后半进行折半，
 *
 * 直到left<right，然后再把第i个元素前1位与目标位置之间的所有元素后移，再把第i个元素放在目标位置上。
 *
 * 二分法实际上没有进行排序，只进行了有查找。所以当找到要插入的位置时，必须从移动最后一个记录开始，向后移动一位，再移动倒数第2位，直到要插入的位置的记录移后一位。如图所示：
 */
public class _003_二分法插入排序 {

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
            int insertNum = array[i];
            int left = 0;
            int right = i - 1;
            int mid = 0;
            while (left < right){
                mid = (left + right) / 2;
                if(insertNum < array[mid]){
                    right = mid -1;
                }else {
                    left = mid+1;
                }
            }
            for (int j = i-1; j >= left ; j--) {
                array[j+1] = array[j];
            }

            if( left != i ){
                array[left] = insertNum;
            }
        }
    }


}
