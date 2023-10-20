package HJ2023B;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 题目描述
 * 有n个人围成一圈，顺序排号为1-n。
 *
 * 从第一个人开始报数(从1到3报数)，凡报到3的人退出圈子，问最后留下的是原来第几号的那位。
 *
 * 输入描述
 * 输入人数n（n < 1000）
 *
 * 输出描述
 * 输出最后留下的是原来第几号
 *
 * 用例
 * 输入	2
 * 输出	2
 * 说明	报数序号为1的人最终报3，因此序号1的人退出圈子，最后剩下序号为2的那位
 * 解题思路
 * 从用户输入中读取人数 n。
 * 创建一个列表 nums，用于存储编号为 1 到 n 的人。
 * 使用一个循环将编号为 1 到 n 的人添加到 nums 列表中。
 * 初始化一个名为 count 的整数变量，用于计数报数，初始值为 3。
 * 使用一个 while 循环，当 nums 列表的大小大于 1 时，继续进行报数和移除操作。
 * 使用一个内部 while 循环，当 nums 列表的大小大于 1 且 count 大于 1 时，将报数的人移动到队尾。这可以通过将列表中的第一个元素添加到列表末尾，然后从列表中移除第一个元素来实现。同时，将 count 减 1。
 * 如果 nums 列表的大小小于等于 1，跳出循环。
 * 如果 count 等于 1，从列表中移除报数为 3 的人，并将 count 重置为 3。
 * 输出最后留下的人的原始编号。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/132012501
 */
public class _020_报数问题_1到3报数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            findLastPerson(sc.nextInt());
        }
    }

    public static void findLastPerson(int size){
        boolean[] existList = new boolean[size];
        for (int i = 0; i < size; i++) {
            existList[i] = true;
        }
        int existSize = size;
        int count = 0;
        while (existSize > 1){
            for (int i = 0; i < size; i++) {
                if(existList[i]){
                    count++;
                }
                if( count == 3){
                    existList[i] = false;
                    existSize--;
                    count = 0;
                }
            }
        }
        for (int i = 0; i < size ; i++) {
            if(existList[i]){
                System.out.println(i+1);
            }
        }

    }
}
