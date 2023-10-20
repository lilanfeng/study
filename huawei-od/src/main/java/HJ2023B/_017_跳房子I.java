package HJ2023B;

import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * 跳房子，也叫跳飞机，是一种世界性的儿童游戏。
 * 游戏参与者需要分多个回合按顺序跳到第1格直到房子的最后一格。
 * 跳房子的过程中，可以向前跳，也可以向后跳。
 * 假设房子的总格数是count，小红每回合可能连续跳的步教都放在数组steps中，请问数组中是否有一种步数的组合，可以让小红两个回合跳到量后一格?
 * 如果有，请输出索引和最小的步数组合。
 *
 * 注意：
 *
 * 数组中的步数可以重复，但数组中的元素不能重复使用。
 * 提供的数据保证存在满足题目要求的组合，且索引和最小的步数组合是唯一的。
 * 输入描述
 * 第一行输入为房子总格数count，它是int整数类型。
 * 第二行输入为每回合可能连续跳的步数，它是int整数数组类型。
 *
 * 输出描述
 * 返回索引和最小的满足要求的步数组合（顺序保持steps中原有顺序）
 *
 * 备注
 * count ≤ 1000
 * 0 ≤ steps.length ≤ 5000
 * -100000000 ≤ steps ≤ 100000000
 * 用例1
 * 输入
 *
 * [1,4,5,2,2]
 * 7
 * 输出
 * [5, 2]
 *
 * 用例2
 * 输入
 *
 * [-1,2,4,9,6]
 * 8
 * 输出
 *
 * [-1, 9]
 * 说明
 *
 * 此样例有多种组合满足两回合跳到最后，譬如：[-1,9]，[2,6]，其中[-1,9]的索引和为0+3=3，[2,6]的索和为1+4=5，所以索引和最小的步数组合[-1,9]
 *
 * 解题思路
 * 题目要求在给定的房子总格数count和每回合可能连续跳的步数数组steps中，找到一个步数组合，使得小红可以在两个回合内跳到房子的最后一格，并输出索引和最小的步数组合。
 *
 * 解题思路如下：
 *
 * 从输入中读取房子总格数count和每回合可能连续跳的步数数组steps。
 * 初始化最小索引和minIdxSum为Integer.MAX_VALUE，答案ans为空字符串。
 * 使用两层循环遍历数组中的所有可能的组合：
 * a. 对于每个元素steps[idx1]，遍历其后面的所有元素steps[idx2]。
 * b. 如果steps[idx1] + steps[idx2]等于count，说明找到了一个满足条件的组合。
 * c. 计算当前组合的索引和idxSum = idx1 + idx2，并与已找到的最小索引和minIdxSum进行比较。
 * d. 如果当前组合的索引和小于已找到的最小索引和，更新最小索引和和答案。
 * e. 由于只需要找到索引和最小的，所以在找到一个满足条件的组合后，使用break跳出内层循环，继续检查下一个元素。
 * 返回答案ans。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/132331250
 */
public class _017_跳房子I {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int count = Integer.valueOf(sc.nextLine());
        int[] steps = Arrays.stream(str.substring(1,str.length() -1).split(","))
                .mapToInt(Integer::parseInt).toArray();
        findMinStep(count,steps);
    }

    public static void findMinStep(int count,int[] steps){
        int minIndexSum = Integer.MAX_VALUE;
        String result = "";
        for (int i = 0; i < steps.length; i++) {

            for (int j = i + 1; j < steps.length; j++) {
                if(steps[i] + steps[j] == count){

                    if(i+j < minIndexSum){
                        minIndexSum = i + j;
                        result = "[" + steps[i] +","+steps[j]+"]";
                    }
                }
                break;
            }

        }
        System.out.println(result);
    }
}
