package already;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 学生方阵
 *
 * 示例：
 *  输入：
 *      5,6
 *      M,W,W,W,M,M
 *      M,M,M,M,M,M
 *      W,W,M,M,W,M
 *      M,W,W,M,M,W
 *      M,W,M,M,M,M
 *  输出：
 *
 */
public class _003_找出行斜竖中最大连续男孩的个数 {

    private static int hang;
    private static int shu;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] numberArr = sc.nextLine().split(",");
        hang = Integer.valueOf(numberArr[0]);
        shu = Integer.valueOf(numberArr[1]);
        String[][] inputArr = new String[hang][shu];
        for (int i = 0; i < hang; i++) {
            String[] temp = sc.nextLine().split(",");
            for (int j = 0; j < temp.length; j++) {
                inputArr[i][j] = temp[j];
            }
        }

        System.out.println(findMaxMale(inputArr));

        List<List<String>> students = new java.util.ArrayList<>();
        for (int i = 0; i < hang; i++) {
            students.add(java.util.Arrays.asList(inputArr[i]));
        }
        List<Integer> res = new java.util.ArrayList<>();
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < shu; j++) {
                getMaxConnected(students, i, j, res);
            }
        }
        // 对结果数组排序
        Collections.sort(res);
        // 输出最大的连续的M的个数
        System.out.println(res.get(res.size() - 1));


    }

    public static void getMaxConnected(List<List<String>> students, int row, int column, List<Integer> res) {
        int len = 1; // 初始化连续的M的个数为1
        int a = 0, b = 0; // 初始化行和列的索引
        int m = students.size(), n = students.get(0).size(); // 获取方阵的行数和列数
        if (column < n) {  // 从左往右搜索
            a = row;
            b = column;
            while (b < n - 1 && students.get(a).get(++b).equals("M")) { // 不越界且下一个元素为M
                len++; // 连续的M的个数加1
            }
            res.add(len); // 把连续的M的个数加入结果数组
            len = 1; // 重新初始化连续的M的个数为1
        }
        if (row < m) {  // 从上往下搜索
            a = row;
            b = column;
            while (a < m - 1 && students.get(++a).get(b).equals("M")) { // 不越界且下一个元素为M
                len++; // 连续的M的个数加1
            }
            res.add(len); // 把连续的M的个数加入结果数组
            len = 1; // 重新初始化连续的M的个数为1
        }
        if (row < m && column < n) {  // 对角线搜索
            a = row;
            b = column;
            while ((a < m - 1 && b < n - 1) && students.get(++a).get(++b).equals("M")) { // 不越界且下一个元素为M
                len++; // 连续的M的个数加1
            }
            res.add(len); // 把连续的M的个数加入结果数组
            len = 1; // 重新初始化连续的M的个数为1
        }
        if (row >= 0 && column < n) {  // 从右往左搜索
            a = row;
            b = column;
            while ((a > 0 && b < n - 1) && students.get(--a).get(++b).equals("M")) { // 不越界且下一个元素为M
                len++; // 连续的M的个数加1
            }
            res.add(len); // 把连续的M的个数加入结果数组
        }
    }



    /**
     * @param inputArr
     */
    public static int findMaxMale(String[][] inputArr){
        int maxCount = 0;
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < shu; j++) {
                if("M".equalsIgnoreCase(inputArr[i][j])){
                    //寻找横，竖，斜的所有情况处理
                    maxCount = Math.max(maxCount,findVertical(i,j,inputArr));
                    maxCount = Math.max(maxCount,findHorizontal(i,j,inputArr));
                    maxCount = Math.max(maxCount,findDiagonalNegative(i,j,inputArr));
                    maxCount = Math.max(maxCount,findDiagonalPositive(i,j,inputArr));
                }
            }
        }
        return maxCount;
    }

    /**
     *
     * @param i
     * @param j
     * @param inputArr
     * @return
     */
    private static int  findVertical(int i, int j, String[][] inputArr){
        int max = 1;
        if(i > 0 && "M".equals(inputArr[i-1][j])){
            return 0;
        }
        while (i + 1 < hang && "M".equals(inputArr[++i][j]) ){
            max++;
        }
        return max;
    }

    private static int  findHorizontal(int i, int j, String[][] inputArr){
        int max = 1;
        if(i > 0 && "M".equals(inputArr[i][j-1])){
            return 0;
        }

        while (j + 1 < shu && "M".equals(inputArr[i][j++]) ){
            max++;
        }
        return max;
    }


    private static int  findDiagonalPositive(int i, int j, String[][] inputArr){
        int max = 1;
        if(i > 0 && j+1 < shu && "M".equals(inputArr[i-1][j +1])){
            return 0;
        }
        while (i + 1 < hang && j > 0  && "M".equals(inputArr[++i][--j]) ){
            max++;
        }
        return max;
    }

    private static int  findDiagonalNegative(int i, int j, String[][] inputArr){
        int max = 1;
        if(i > 0 && j > 0 &&  "M".equals(inputArr[i-1][j-1])){
            return 0;
        }
        while (i + 1 < hang && j + 1 < shu && "M".equals(inputArr[++i][++j]) ){
            max++;
        }
        return max;
    }

}
