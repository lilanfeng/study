package HJ2023B200;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 题目描述
 * A，B两个人玩一个数字比大小的游戏，在游戏前，两个人会拿到相同长度的两个数字序列，两个数字序列不相同的，且其中的数字是随机的。
 * A，B各自从数字序列中挑选出一个数字进行大小比较，赢的人得1分，输的人扣1分，相等则各自的分数不变。 用过的数字需要丢弃。
 *
 * 求A可能赢B的最大分数。
 *
 * 输入描述
 * 输入数据的第1个数字表示数字序列的长度N，后面紧跟着两个长度为N的数字序列。
 *
 * 输出描述
 * A可能赢B的最大分数
 *
 * 用例1
 * 输入
 *
 * 3
 * 4 8 10
 * 3 6 4
 * 输出
 * 3
 * 说明
 * 输入数据第1个数字表示数字序列长度为3，后面紧跟着两个长度为3的数字序列。
 * 序列A：4 8 10
 * 序列B：3 6 4
 *
 * A可以赢的最大分数是3。获得该分数的比大小过程可以是：
 * 1）A：4 B：3
 * 2）A：8 B：6
 * 3）A：10 B：4
 * 机考代码查重
 * 华为OD机考完成之后，官方会进行代码查重。华为 od
 * 机考确实有很大的概率抽到原题。如果碰到了题库中的原题，一定不要直接使用题解中的代码，尤其是变量名，一定要修改，可以改成毫无意义的单词。除了变量名之外，代码的组织结构和逻辑一定要进行改变，这就要求在日常的刷题中，提前编写好属于自己的代码。
 *
 * 思路
 * 这道题可以采用贪心策略来解决。首先，将A和B的数字序列分别按照从小到大的顺序进行排序。然后，设置两个指针la和ra分别指向A序列的最左端和最右端，设置两个指针lb和rb分别指向B序列的最左端和最右端。
 * 接下来，使用一个变量ans来记录A可能赢B的最大分数。开始循环，判断A序列的最大值和B序列的最大值的大小关系：
 *
 * 如果A序列的最大值大于B序列的最大值，那么A可以选择最大值与B的最大值进行比较，A得1分，然后将指针向左移动一位。
 * 如果A序列的最大值小于B序列的最大值，那么A只能选择最小值与B的最大值进行比较，A扣1分，然后将指针向右移动一位。
 * 如果A序列的最大值等于B序列的最大值，那么需要进一步判断A序列的最小值和B序列的最小值的大小关系：
 * 如果A序列的最小值大于B序列的最小值，那么A可以选择最小值与B的最小值进行比较，A得1分，然后将指针向右移动一位。
 * 如果A序列的最小值小于B序列的最小值，那么A只能选择最小值与B的最大值进行比较，A扣1分，然后将指针向右移动一位。
 * 最后，返回变量ans即为A可能赢B的最大分数。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/132011888
 */
public class _003_数字序列比大小 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int[] a = new int[number];
        for (int i = 0; i < number; i++) {
            a[i] = sc.nextInt();
        }
        int[] b = new int[number];
        for (int i = 0; i < number; i++) {
            b[i] = sc.nextInt();
        }
        beatAMax(number,a,b);
    }

    public static void beatAMax(int number,int[] a,int[] b){

        int la = 0,ra = number-1,lb = 0,rb = number -1;
        int score = 0;
        Arrays.sort(a);
        Arrays.sort(b);
       while (la <= ra && la >= 0 && ra < number){
           if(a[ra] < b[rb]){
               score -= 1;
               la++;
               rb--;
               continue;
           }
           if(a[ra] > b[rb]){
               score += 1;
               ra--;
               rb--;
               continue;
           }
           if(a[ra] == b[rb]){
               if(a[la] > b[lb]){
                   score += 1;
                   la++;
                   lb++;
                   continue;
               }
               if(a[la] < b[lb]){
                   score -= 1;
                   la++;
                   rb--;
                   continue;
               }
           }


       }

       System.out.println(score);

    }


}
