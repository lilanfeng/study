package HJ2023B;

import java.util.Scanner;

/**
 *  题目描述
 * 输入两个版本号 version1 和 version2，每个版本号由多个子版本号组成。
 *
 * 子版本号之间由 “.” 隔开，由大小写字母、数字组成，并且至少有一个字符。
 *
 * 按从左到右的顺序比较子版本号，比较规则如下：
 *
 * 子版本号前面的0不参与比较，比如 001 和 1 是相等的。
 * 小写字母 > 大写字母 > 数字
 * 空字符和0相等，比如 1 和 1.0 相等
 * 比较结果
 *
 * 如果 version1 > version2 ，返回 1
 *
 * 如果 version1 < version2 ，返回-1
 *
 * 其他情况返回0
 *
 * 输入描述
 * 第一行输入version1
 *
 * 第二行输入version2
 *
 * 输出描述
 * 输出version1和version2的比较结果
 *
 * ACM输入输出模式
 * 如果你经常使用Leetcode,会知道letcode是不需要编写输入输出函数的。但是华为OD机考使用的是 ACM 模式，需要手动编写输入和输出。
 *
 * 所以最好在牛-客上提前熟悉这种模式。例如C++使用cin/cout,python使用input()/print()。JavaScript使用node的readline()和console.log()。Java
 * 使用sacnner/system.out.print()
 *
 * 用例1
 * 输入
 *
 * 5.2
 * 5.1a
 * 输出
 * 1
 * 用例2
 * 输入
 * 5.6.1
 * 5.6.2a
 * 输出
 *
 * -1
 * 用例3
 * 输入
 *
 * 5.6.8.a
 * 5.6.8.0a
 * 输出
 * 0
 * 代码思路
 * 题目要求比较两个版本号，每个版本号由多个子版本号组成，子版本号之间用点号隔开。比较规则包括子版本号前面的0不参与比较，小写字母大于大写字母大于数字，空字符和0相等。根据这些规则，我们需要逐个比较子版本号，得出最终结果。
 *
 * 解题思路如下：
 *
 * 读取输入的两个版本号 version1 和 version2。
 * 使用 split 方法，根据点号将两个版本号分割成子版本号数组 arr1 和 arr2。
 * 计算两个数组的最大长度 n，用于遍历比较。
 * 遍历数组，从左到右比较子版本号：
 * a. 如果下标 i 在数组范围内，取数组中的值并去除前导0，否则取0。这里使用 removeLeadingZero 函数去除前导0。
 * b. 使用 compareTo 方法比较两个子版本号。如果 tmp1 大于 tmp2，结果为1并跳出循环；如果 tmp1 小于 tmp2，结果为-1并跳出循环；否则继续比较下一个子版本号。
 * 输出最终结果。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/129865461
 */
public class _012_比较两个版本号的大小 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String version1 = sc.nextLine();
        String version2 = sc.nextLine();
        System.out.println(compareVersion(version1,version2));
    }

    public static int compareVersion(String version1,String version2){
        int result = 0;
        String[] version1Arr = version1.split("\\.");
        String[] version2Arr = version2.split("\\.");
        int max = Math.max(version1Arr.length,version2Arr.length);
        for (int i = 0; i < max; i++) {
            String temp1 = ( i < version1Arr.length) ? removeFirstZero(version1Arr[i]) : "0";
            String temp2 = (i < version2Arr.length) ? removeFirstZero(version2Arr[i]) : "0";
            if(temp1.compareTo(temp2) > 0){
                return 1;
            }else if(temp1.compareTo(temp2) < 0){
                return  -1;
            }
        }
        return result;
    }

    public static String removeFirstZero(String source){
        source = source.replaceAll("^0+","");
        return source.isEmpty() ? "0": source;
    }
}
