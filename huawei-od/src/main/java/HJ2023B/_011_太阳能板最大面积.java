package HJ2023B;

import java.util.Scanner;

/**
 * 题目描述
 * 给航天器一侧加装长方形或正方形的太阳能板（图中的红色斜线区域），需要先安装两个支柱（图中的黑色竖条），再在支柱的中间部分固定太阳能板。
 *
 * 但航天器不同位置的支柱长度不同，太阳能板的安装面积受限于最短一侧的那根支柱长度。如图：
 *
 *
 *
 * 现提供一组整形数组的支柱高度数据，假设每根支柱间距离相等为1个单位长度，计算如何选择两根支柱可以使太阳能板的面积最大。
 *
 * 输入描述
 * 10,9,8,7,6,5,4,3,2,1
 *
 * 注：支柱至少有2根，最多10000根，能支持的高度范围1~10^9的整数。柱子的高度是无序的，例子中递减只是巧合。
 *
 * 输出描述
 * 可以支持的最大太阳能板面积：（10米高支柱和5米高支柱之间）
 *
 * 25
 *
 * ACM输入输出模式
 * 如果你经常使用Leetcode,会知道letcode是不需要编写输入输出函数的。但是华为OD机考使用的是 ACM 模式，需要手动编写输入和输出。
 *
 * 所以最好在牛-客上提前熟悉这种模式。例如C++使用cin/cout,python使用input()/print()。JavaScript使用node的readline()和console.log()。Java
 * 使用sacnner/system.out.print()
 *
 * 用例
 * 输入	10,9,8,7,6,5,4,3,2,1
 * 输出	25
 * 备注
 * 10米高支柱和5米高支柱之间宽度为5，高度取小的支柱高也是5，面积为25。
 * 任取其他两根支柱所能获得的面积都小于25。
 * 所以最大的太阳能板面积为25。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/130022091
 */
public class _011_太阳能板最大面积 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] heights = input.split(",");
        int[] heightArr = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            heightArr[i] = Integer.parseInt(heights[i]);
        }

        int max_area = 0;
        for (int i = 0; i < heightArr.length - 1; i++) {
            for (int j = i + 1; j < heightArr.length; j++) {
                int distance = j - i;
                int height = Math.min(heightArr[i], heightArr[j]);
                int area = distance * height;
                max_area = Math.max(max_area, area);
            }
        }
        System.out.println(max_area);
    }
}
