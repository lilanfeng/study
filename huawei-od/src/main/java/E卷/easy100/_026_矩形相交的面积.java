package E卷.easy100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/5 17:35
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _026_矩形相交的面积 {
    /**
     * 矩形相交的面积
     * 题目描述
     * 给出3组点坐标(x, y, w, h)，-1000<x,y<1000，w,h为正整数。
     * (x, y, w, h)表示平面直角坐标系中的一个矩形：
     * x, y为矩形左上角坐标点，w, h向右w，向下h。
     * (x, y, w, h)表示x轴(x, x+w)和y轴(y, y-h)围成的矩形区域；
     * (0, 0, 2, 2)表示 x轴(0, 2)和y 轴(0, -2)围成的矩形区域；
     * (3, 5, 4, 6)表示x轴(3, 7)和y轴(5, -1)围成的矩形区域；
     * 求3组坐标构成的矩形区域重合部分的面积。
     *
     * 输入描述
     * 3行输入分别为3个矩形的位置，分别代表“左上角x坐标”，“左上角y坐标”，“矩形宽”，“矩形高” -1000 <= x,y < 1000
     *
     * 输出描述
     * 输出3个矩形相交的面积，不相交的输出0。
     *
     * 示例1
     * 输入
     * 1 6 4 4
     * 3 5 3 4
     * 0 3 7 3
     * 输出
     * 2
     * 说明
     *
     * 解题思路
     * 三个矩形的左上角坐标 (x1, y1) 以及宽度 w 和高度 h。利用这些输入，计算出每个矩形的右下角坐标 (x2, y2)：
     * x2 = x1 + w：右下角的 x 坐标是左上角 x 加上矩形的宽度。
     * y2 = y1 - h：右下角的 y 坐标是左上角 y 减去矩形的高度（因为高度是向下的，所以用减法）。
     * 这些计算结果存储在 x_coords 和 y_coords 两个列表中，分别保存所有的 x 和 y 坐标信息。
     *
     * 确定最小和最大坐标：
     * 代码通过遍历 x_coords 和 y_coords 来确定x、y坐标的最小值和最大值。这些值用于构建包含所有矩形的二维数组，这样可以将坐标转换为一个可索引的数组形式，方便后续的重叠检测。
     *
     * 偏移量的计算：
     * 为了确保坐标系可以从数组的索引 0 开始，代码计算了 x_offset 和 y_offset，这些偏移量用于将负数坐标平移到数组的有效范围内。
     *
     * 构建二维数组表示区域并进行重叠检测：
     * 使用二维数组 intersection_area 来表示整个坐标系的矩形区域，每个位置（数组的元素）表示该位置被多少个矩形覆盖：
     *
     * 遍历每个矩形的左上角到右下角的范围，将这些区域的值在二维数组中加1，表示这些区域已被覆盖。
     * 如果某个二维数组的值是3，说明该位置被三个矩形同时覆盖。
     * 计算重叠区域：
     * 最后，遍历二维数组，统计值为3的区域数量，即为三个矩形重叠部分的面积。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] rectangles =  new int[3][4];
        rectangles[0] = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rectangles[1] = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rectangles[2] = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        scanner.close();
        System.out.println(getIntersectionArea(rectangles));
    }

    /**
     *
     * @param inputArray
     * @return
     */
    public static int getIntersectionArea(int[][] inputArray) {
        List<Integer> x_coords = new ArrayList<>();
        List<Integer> y_coords = new ArrayList<>();
        List<int[]> rectangles = new ArrayList<>();
        for (int i = 0; i < inputArray.length; i++) {
            int x = inputArray[i][0];
            int y = inputArray[i][1];
            int w = inputArray[i][2];
            int h = inputArray[i][3];
            int x1 = x + w;
            int y1 = y - h;
            x_coords.add(x);
            x_coords.add(x1);
            y_coords.add(y);
            y_coords.add(y1);
            rectangles.add(new int[]{x, y, x1, y1});
        }
        int min_x = x_coords.stream().mapToInt(Integer::intValue).min().getAsInt();
        int max_x = x_coords.stream().mapToInt(Integer::intValue).max().getAsInt();
        int min_y = y_coords.stream().mapToInt(Integer::intValue).min().getAsInt();
        int max_y = y_coords.stream().mapToInt(Integer::intValue).max().getAsInt();
        int x_offset = 0-min_x;
        int y_offset = 0-min_y;
        int[][] intersection_area = new int[Math.abs(max_x - min_x)][Math.abs(max_y - min_y)];

        for (int[] rectangle : rectangles) {
            int x1 = rectangle[0] ;
            int y1 = rectangle[1] ;
            int x2 = rectangle[2] ;
            int y2 = rectangle[3];
            for (int i = Math.min(x1,x2) + x_offset; i <= Math.max(x1,x2)+x_offset; i++) {
                for (int j = Math.min(y1,y2) + y_offset; j <= Math.max(y1,y2) + y_offset; j++) {
                    intersection_area[i][j]++;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < intersection_area.length; i++) {
            for (int j = 0; j < intersection_area[i].length; j++) {
                if (intersection_area[i][j] == 3) {
                    count++;
                }
            }
        }
        return count;
    }
}
