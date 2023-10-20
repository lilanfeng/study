package 简单;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 描述
 * N 位同学站成一排，音乐老师要请最少的同学出列，使得剩下的 K 位同学排成合唱队形。
 *
 * 设
 * �
 * K位同学从左到右依次编号为 1，2…，K ，他们的身高分别为
 * �
 * 1
 * ,
 * �
 * 2
 * ,
 * …
 * ,
 * �
 * �
 * T
 * 1
 * ​
 *  ,T
 * 2
 * ​
 *  ,…,T
 * K
 * ​
 *   ，若存在
 * �
 * (
 * 1
 * ≤
 * �
 * ≤
 * �
 * )
 * i(1≤i≤K) 使得
 * �
 * 1
 * <
 * �
 * 2
 * <
 * .
 * .
 * .
 * .
 * .
 * .
 * <
 * �
 * �
 * −
 * 1
 * <
 * �
 * �
 * T
 * 1
 * ​
 *  <T
 * 2
 * ​
 *  <......<T
 * i−1
 * ​
 *  <T
 * i
 * ​
 *   且
 * �
 * �
 * >
 * �
 * �
 * +
 * 1
 * >
 * .
 * .
 * .
 * .
 * .
 * .
 * >
 * �
 * �
 * T
 * i
 * ​
 *  >T
 * i+1
 * ​
 *  >......>T
 * K
 * ​
 *  ，则称这
 * �
 * K名同学排成了合唱队形。
 * 通俗来说，能找到一个同学，他的两边的同学身高都依次严格降低的队形就是合唱队形。
 * 例子：
 * 123 124 125 123 121 是一个合唱队形
 * 123 123 124 122不是合唱队形，因为前两名同学身高相等，不符合要求
 * 123 122 121 122不是合唱队形，因为找不到一个同学，他的两侧同学身高递减。
 *
 * 你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
 *
 * 注意：不允许改变队列元素的先后顺序 且 不要求最高同学左右人数必须相等
 *
 * 数据范围：
 * 1
 * ≤
 * �
 * ≤
 * 3000
 *
 * 1≤n≤3000
 *
 * 输入描述：
 * 用例两行数据，第一行是同学的总数 N ，第二行是 N 位同学的身高，以空格隔开
 *
 * 输出描述：
 * 最少需要几位同学出列
 *
 * 示例1
 * 输入：
 * 8
 * 186 186 150 200 160 130 197 200
 * 复制
 * 输出：
 * 4
 * 复制
 * 说明：
 * 由于不允许改变队列元素的先后顺序，所以最终剩下的队列应该为186 200 160 130或150 200 160 130
 */
public class _HJ024_合唱队 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            solution0(n,arr);
        }
    }

    public static void solution0(int n, int[] arr){
        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = 1;
        right[n-1] = 1;

        for(int i = 0; i < n; i++){
            left[i] = 1;
            for (int j = 0; j < i ; j++) {
                if(arr[i] > arr[j]){
                    left[i] = Math.max(left[j] + 1,left[i]);
                }
            }
        }

        for (int i = n-1; i >= 0; i--) {
            right[i] = 1;
            for (int j = n-1; j > i ; j--) {
                if(arr[i] >arr[j]) {
                    right[i] = Math.max(right[j] +1,right[i]);
                }
            }
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = left[i] + right[i] -1;
        }
        Arrays.sort(result);
        System.out.println(n-result[n-1]);

    }


    /**
     * 二分查找
     * @param n
     * @param arr
     */
    public static void solution(int n,int[] arr){
        //存储每个数左边小于其的数的个数
        int[] left = new int[n];
        //存储每个数右边小于其的数的个数
        int[] right = new int[n];
        left[0] = arr[0];
        right[n-1] = arr[n-1];
        //记录以i为终点的从左向右和从右向左的子序列元素个数
        int num[] = new int[n];
        //记录当前子序列的长度
        int index = 1;
        for (int i = 1; i < n; i++){
            if(arr[i] > left[index-1]){
                num[i] = index;
                left[index++] = arr[i];
            }else {
                int low = 0;
                int high = index -1;
                while (low < high){
                    int mid = (low + high) / 2;
                    if(left[mid] < arr[i]){
                        low = mid + 1;
                    }else {
                        high = mid;
                    }
                }
                left[low] = arr[i];
                num[i] = low;
            }
        }

        index = 1;
        for (int i = n-2; i >= 0; i--){
            if(arr[i] > right[index -1]) {
                num[i] += index;
                right[index++] = arr[i];
            }else {
                int low = 0;
                int high = index - 1;
                while (low < high){
                    int mid = (high +low)/2;
                    if(right[mid] < arr[i]){
                        low = mid + 1;
                    }else {
                        high = mid;
                    }
                }
                right[low] = arr[i];
                num[i] += low;
            }
        }

        int max = 1;
        for (int number:num){
            max = Math.max(max,number);
        }
        //max + 1 为最大的k
        System.out.println(n-max);


    }
}
