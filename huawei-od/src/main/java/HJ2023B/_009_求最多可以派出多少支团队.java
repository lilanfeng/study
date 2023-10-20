package HJ2023B;


import java.util.Arrays;
import java.util.Scanner;

/**
 *  题目描述
 *  用数组代表每个人的能力 一个比赛活动要求参赛团队的最低能力值为N 每个团队可以由一人或者两人组成 且一个人只能参加一个团队 计算出最多可以派出多少只符合要求的队伍。
 *  输入描述\n
 *  第一行代表总人数，范围1-500000\n第二行数组代表每个人的能力\n
 *  数组大小，范围1-500000\n元素取值，范围1-500000\n第三行数值为团队要求的最低能力值，范围1-500000\n
 *  输出描述\n
 *  最多可以派出的团队数量
 *  用例1
 * 输入
 *
 * 5
 * 3 1 5 7 9
 * 8
 * 输出
 *
 * 3
 * 说明
 *
 * 3、5组成一队 1、7一队 9自己一队 输出3
 *
 * 用例2
 * 输入
 *
 * 7
 * 3 1 5 7 9 2 6
 * 8

 * 输出
 *
 * 4

 * 说明
 *
 * 3、5组成一队，1、7一队，9自己一队，2、6一队，输出4
 *
 * 用例3
 * 输入
 *
 * 3
 * 1 1 9
 * 8
 * 输出
 *
 * 1
 * 说明
 *
 * 9自己一队，输出1
 *
 */
public class _009_求最多可以派出多少支团队 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int[] fitArr = new int[number];
        for (int i = 0; i < number; i++) {
            fitArr[i] = sc.nextInt();
        }
        int max = sc.nextInt();

        Arrays.sort(fitArr);
        int left = 0;
        int right = number -1;
        int total = 0;
        while (left < right){
            if(fitArr[right] >= max){
                total++;
                right--;
            }else if(fitArr[left] + fitArr[right] >= max){
                total++;
                left++;
                right--;
            }else {
                left++;
            }
        }
        if(left == right && fitArr[left] >= max){
            total++;
        }

        System.out.print(total);
    }

}
