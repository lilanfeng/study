package E卷.easy100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _009_第K个排列 {

    /**
     * 题目描述
     * 给定参数n，从1到n会有n个整数：1,2,3,…,n,这n个数字共有n!种排列。
     * 按大小顺序升序列出所有排列的情况，并一一标记，
     * 当n=3时,所有排列如下:
     * “123” “132” “213” “231” “312” “321”
     * 给定n和k，返回第k个排列。
     * 输入描述
     * 输入两行，第一行为n，第二行为k，
     * 给定n的范围是[1,9],给定k的范围是[1,n!]。
     * 输出描述
     * 输出排在第k位置的数字。
     * 示例1
     * 输入
     * 3
     * 3
     * 输出
     *
     * 213
     * 说明
     *
     * 3的排列有123,132,213…,那么第三位置就是213
     *
     * 示例2
     * 输入
     * 2
     * 2
     * 输出
     * 21
     * 说明
     *
     * 2的排列有12,21，那么第二位置的为21。
     *
     * 解题思路
     * 常见的递归解法
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int k = sc.nextInt();
            System.out.println(findPermutation(n,k));
        }
    }

    /**
     *
     * @param n
     * @param k
     * @return
     */
    public static String findPermutation(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        List<String> resultList = new ArrayList<>();
        generatePermutation(nums, "", resultList, k);
        Collections.sort(resultList);
        return resultList.get(k - 1);
    }

    /**
     * 递归
     * @param nums
     * @param current
     * @param resultList
     * @param k
     */
    private static void generatePermutation(int[] nums,String current,List<String> resultList,int k) {
        if (nums.length == 0) {
            resultList.add(current);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            int[] newNums = new int[nums.length - 1];

            //走到中间获取前面的数字
            for (int j = 0; j < i; j++) {
                newNums[j] = nums[j];
            }
            //走到中间获取后面的数字
            for (int j = i + 1; j < nums.length; j++) {
                newNums[j - 1] = nums[j];
            }
            generatePermutation(newNums, current + first, resultList, k);

            if(resultList.size() == k){
                return ;
            }
        }
    }
}
