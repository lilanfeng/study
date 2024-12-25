package E卷.easy100;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/6 12:04
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _029_路灯照明问题 {
    /**
     * 题目描述
     * 在一条笔直的公路上安装了N个路灯，从位置0开始安装，路灯之间间距固定为100米。
     * 每个路灯都有自己的照明半径，请计算第一个路灯和最后一个路灯之间，无法照明的区间的长度和。
     * 输入描述
     * 第一行为一个数N，表示路灯个数，1<=N<=100000
     * 第二行为N个空格分隔的数，表示路灯的照明半径，1<=照明半径<=100000*100
     *
     * 输出描述
     * 第一个路灯和最后一个路灯之间，无法照明的区间的长度和
     * 示例1
     * 输入
     * 2
     * 50 50
     * 输出
     * 0
     * 说明
     * 路灯1覆盖0-50，路灯2覆盖50-100，路灯1和路灯2之间(0米-100米)无未覆盖的区间。
     *
     * 示例2
     * 输入
     * 4
     * 50 70 20 70
     * 输出
     * 20
     * 说明
     *
     * 输入说明：
     * 路灯1 覆盖0-50
     * 路灯2 覆盖30-170
     * 路灯3 覆盖180-220
     * 路灯4 覆盖230-370
     * 输出说明
     * [170,180],[220,230]，两个未覆盖的区间，总里程为20
     */
    public static void main(String[] args) {
        // 输入
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] radius = new int[n];
        for (int i = 0; i < n; i++) {
            radius[i] = scanner.nextInt();
        }
        scanner.close();
        System.out.println(getRadius(radius, n) );
    }

    /**
     * 获取半径
     * @param radius
     * @param n
     * @return
     */
    public static int getRadius(int[] radius, int n) {
        // 获取半径 比较前后半径之后是否大于100 不大于就是对应未覆盖的区域

        int[][] rangeRadius = new int[n][2];
        // 初始化各灯的照明范围
        for (int i = 0; i < radius.length; i++) {
            int start = Math.max(0,i*100-radius[i]);
            int end = i*100 + radius[i];
            rangeRadius[i][0] = start;
            rangeRadius[i][1] = end;
        }
        List<int[]> coveredRangeList = new ArrayList<>();
        int start = rangeRadius[0][0];
        int end = rangeRadius[0][1];
        // 遍历灯 范围覆盖 （合并一些重叠的范围区间）
        for (int i = 1; i < radius.length; i++) {
            if (rangeRadius[i][0] > end){
                coveredRangeList.add(new int[]{start,end});
                start = rangeRadius[i][0];
                end = rangeRadius[i][1];
            }else {
                if (rangeRadius[i][0] < start) {
                    Iterator<int[]> iterator = coveredRangeList.iterator();
                    while (iterator.hasNext()){
                        int[] ints = iterator.next();
                        if(ints[0] >= rangeRadius[i][0]){
                            iterator.remove();
                        }
                    }
                    start = rangeRadius[i][0];
                }
                end = Math.max(end,rangeRadius[i][1]);
            }
        }
        coveredRangeList.add(new int[]{start,end});
        int notCovered = 0;
        int x = coveredRangeList.get(0)[1];
        // 计算未覆盖的区域（覆盖范围区间是否有未覆盖的a）
        for (int i = 1; i < coveredRangeList.size(); i++) {
            // 相邻区间相交
            if (coveredRangeList.get(i)[0] - x < 0) {
                x = coveredRangeList.get(i)[1];
                continue;
            }
            notCovered += coveredRangeList.get(i)[0] - x;
            x = coveredRangeList.get(i)[1];
        }
        return notCovered;
    }

}
