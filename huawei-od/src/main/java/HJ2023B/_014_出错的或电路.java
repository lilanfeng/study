package HJ2023B;

import java.util.Scanner;

/**
 * 题目描述
 * 某生产门电路的厂商发现某一批次的或门电路不稳定，具体现象为计算两个二进制数的或操作时，第一个二进制数中某两个比特位会出现交换，交换的比特位置是随机的，但只交换这两个位，其他位不变。
 * 很明显，这个交换可能会影响最终的或结果，也可能不会有影响。
 * 为了评估影响和定位出错的根因，工程师需要研究在各种交换的可能下，最终的或结果发生改变的情况有多少种。
 *
 * 输入描述
 * 第一行有一个正整数N; 其中1≤N≤1000000。
 * 第二行有一个长为N的二进制数，表示与电路的第一个输入数，即会发生比特交换的输入数。
 * 第三行有一个长为N的二进制数，表示与电路的第二个输入数。注意第二个输入数不会发生比特交换。
 *
 * 输出描述
 * 输出只有一个整数，表示会影响或结果的交换方案个数。
 *
 * ACM输入输出模式
 * 如果你经常使用Leetcode,会知道letcode是不需要编写输入输出函数的。但是华为OD机考使用的是 ACM 模式，需要手动编写输入和输出。
 * 所以最好在牛-客上提前熟悉这种模式。例如C++使用cin/cout,python使用input()/print()。JavaScript使用node的readline()和console.log()。Java
 * 使用sacnner/system.out.print()
 *
 * 用例1
 * 输入
 *
 * 3
 * 010
 * 110
 * 输出
 *
 * 1
 * 说明
 *
 * 原本010和110的或结果是110，但第一个输入数可能会发生如下三种交换：
 * 1、交换第1个比特和第2个比特，第一个输入数变为100，计算结果为110，计算结果不变
 * 2、交换第1个比特和第3个比特，第一个输入数变为010，计算结果为110，计算结果不变
 * 3、交换第2个比特和第3个比特，第一个输入数变为001，计算结果为111，计算结果改变
 * 故只有一种交换会改变计算结果。
 *
 * 用例2
 * 输入
 *
 * 6
 * 011011
 * 110110
 * 输出
 * 4
 * 说明
 *
 * 原本011011和110110的或结果是111111，但是第一个输入数发生如下比特交换会影响最终计算结果：
 * 1、交换第1个比特和第3个比特，第一个输入数变为110011，计算结果变为110111
 * 2、交换第1个比特和第6个比特，第一个输入数变为111010，计算结果变为111110
 * 3、交换第3个比特和第4个比特，第一个输入数变为010111，计算结果变为110111
 * 4、交换第4个比特和第6个比特，第一个输入数变为011110，计算结果变为111100
 * 其他交换都不会影响计算结果，故输出4.
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129939817
 */
public class _014_出错的或电路 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        String source = sc.nextLine();
        String target = sc.nextLine();
        int bin1Zero = 0;
        int bin1One = 0;
        int bin2Zero = 0;
        int bin2One = 0;
        for (int i = 0; i < number; i++) {
            if(source.charAt(i) == '0'){
                bin1Zero++;
                if(target.charAt(i) == '0'){
                    bin2Zero++;
                }
            }else {
                bin1One++;
                if(target.charAt(i) == '0'){
                    bin2One++;
                }
            }
        }
        int result = bin2Zero * bin1One + bin2One * bin1Zero - bin2Zero*bin2One;
        System.out.println(result);
    }

}
