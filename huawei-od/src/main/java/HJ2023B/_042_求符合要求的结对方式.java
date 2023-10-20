package HJ2023B;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  题目描述
 * 用一个数组A代表程序员的工作能力，公司想通过结对编程的方式提高员工的能力，假设结对后的能力为两个员工的能力之和，求一共有多少种结对方式使结对后能力为N。
 *
 * 输入描述
 * 第一行为员工的总人数，取值范围[1,1000]
 * 第二行为数组A的元素，每个元素的取值范围[1,1000]
 * 第三行为N的值，取值范围[1,1000]
 *
 * 输出描述
 * 满足结对后能力为N的结对方式总数。
 *
 * 用例
 * 输入	5
 * 1 2 2 2 3
 * 4
 * 输出	4
 * 说明	满足要求的结对方式为：A[0]和A[4]，A[1]和A[2]，A[1]和A[3]，A[2]和A[3]。
 * 解题思路
 * 首先，读取员工总人数（total）。
 * 接着，创建一个列表（arr）用于存储员工的工作能力，并读取每个员工的工作能力值。
 * 然后，读取目标结对能力值（n）。
 * 对员工工作能力列表进行排序（升序）。
 * 初始化满足条件的结对方式总数（ans）为0，初始化左指针（left）为0，初始化右指针（right）为列表长度减1。
 * 当左指针小于右指针时，执行以下操作：
 * a. 计算当前左右指针指向的员工能力之和（sum）。
 * b. 如果能力之和等于目标值N：
 * i. 如果左右指针指向的员工能力相等，计算这种情况下的结对方式数，并累加到总数。然后跳出循环。
 * ii. 否则，初始化左边相等员工能力的计数器（count_left）和右边相等员工能力的计数器（count_right）为1
 * 。计算左边相等员工能力的数量和右边相等员工能力的数量。计算这种情况下的结对方式数，并累加到总数。左指针右移，右指针左移。
 * c. 如果能力之和小于目标值N，左指针右移。
 * d. 如果能力之和大于目标值N，右指针左移。
 * 输出满足条件的结对方式总数。
 * 这个解题思路采用了双指针技巧，通过在排序后的员工能力列表上移动左右指针，寻找满足条件的结对方式。当找到满足条件的结对方式时，需要考虑两种情况：左右指针指向的员工能力相等，或者不相等。对于这两种情况，分别计算结对方式数，并累加到总数。最后，输出满足条件的结对方式总数。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/132677908
 */
public class _042_求符合要求的结对方式 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int[] array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = sc.nextInt();
        }
        int sum = sc.nextInt();
        findSumTotal(array,sum);
    }

    public static void findSumTotal(int[] array,int sum){
        Arrays.sort(array);
        int left = 0;
        int right = array.length -1;
        int total = 0;
        while (left < right){

            int temp = array[left] + array[right];
            if(temp < sum){
                left++;
            }else if(temp > sum){
                right--;
            }else {
                if(array[left] == array[right]){
                    int size = right - left + 1;

                    total +=  (size *size-size)/2;
                    break;
                }
                total++;
                right--;
                left++;
            }

        }

        System.out.println(total);

    }
}
