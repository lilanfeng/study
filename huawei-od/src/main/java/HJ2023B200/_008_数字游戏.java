package HJ2023B200;

import java.util.Scanner;

/**
 * 题目描述
 * 小明玩一个游戏。
 * 系统发1+n张牌，每张牌上有一个整数。
 * 第一张给小明，后n张按照发牌顺序排成连续的一行。
 * 需要小明判断，后n张牌中，是否存在连续的若干张牌，其和可以整除小明手中牌上的数字。
 *
 * 输入描述
 * 输入数据有多组，每组输入数据有两行，输入到文件结尾结束。
 * 第一行有两个整数n和m，空格隔开。m代表发给小明牌上的数字。
 * 第二行有n个数，代表后续发的n张牌上的数字，以空格隔开。
 * 输出描述
 * 对每组输入，如果存在满足条件的连续若干张牌，则输出1;否则，输出0
 *
 * 备注
 * 1 ≤ n ≤ 1000
 * 1 ≤ 牌上的整数 ≤ 400000
 * 输入的组数，不多于1000
 * 用例确保输入都正确，不需要考虑非法情况。
 * 用例
 * 输入	6 7
 * 2 12 6 3 5 5
 * 10 11
 * 1 1 1 1 1 1 1 1 1 1
 * 输出
 * 1
 * 0
 *
 * 说明	两组输入。第一组小明牌的数字为7，再发了6张牌。第1、2两张牌教字和为14，可以整除7，输出1，第二组小明牌的教字为11，再发了10张牌，这10张牌数字和为10，无法整除11，输出0。
 * 解题思路
 * 总体思路是使用累加和的余数来判断是否存在连续的若干张牌和可以整除m。通过遍历后续发的牌的数字，累加到sum中，并计算当前和的余数。如果之前已经存在相同的余数，说明存在连续的若干张牌和可以整除m，将found标记为true
 * 。最后根据found的值输出1或0，表示是否存在满足条件的连续若干张牌。
 *
 * 首先，逐行读取输入数据。
 * 对于每组输入数据，第一行包含两个整数n和m，分别表示牌的数量和小明手中牌上的数字。
 * 第二行包含n个数，表示后续发的n张牌上的数字。
 * 创建一个长度为m的布尔数组remainderExists，用于记录余数的出现情况。初始时，所有元素都设置为false。
 * 初始化变量sum为0，用于累加牌的数字的和。
 * 初始化变量found为false，表示是否找到满足条件的连续若干张牌。
 * 遍历后续发的n张牌的数字：
 * a. 将当前牌的数字累加到sum中。
 * b. 计算当前和的余数，即remainder = sum % m。
 * c. 如果之前已经存在相同的余数（即remainderExists[remainder]为true），说明存在连续的若干张牌和可以整除m，将found标记为true，并跳出循环。
 * d. 否则，将当前余数标记为已存在，即remainderExists[remainder] = true。
 * 根据found的值输出1或0，表示是否存在满足条件的连续若干张牌。
 * 重置isFirstLine为true，以准备读取下一组输入。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/132643477
 */
public class _008_数字游戏 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String[] input = sc.nextLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            int[] array = new int[n];
            String[] numString = sc.nextLine().split(" ");
            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(numString[i]);
            }
            findSum(m,array);
        }
    }

    public static  void findSum(int m,int[] array){
        boolean[] exists = new boolean[m];

        int sum = 0;
        boolean found = false;
        for (int number :array){
            sum += number;
            int remainder = sum % m;
            if(exists[remainder]){
                found = true;
                break;
            }else {
                exists[remainder] = true;
            }
        }

        System.out.println(found ? 1 : 0);
    }

}
