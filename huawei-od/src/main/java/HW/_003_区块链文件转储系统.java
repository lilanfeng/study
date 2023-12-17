package HW;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 题目描述
 * 区块链只底层存储是一个链式文件系统，由顺序的 N 个文件组成，每个文件的大小不 一，依次为 F1.F2..Fn。随着时间的推移，所占有储会越来越大。
 * 云平台考虑将区块链按文件转储到廉价的 SATA 盘，只有连续的区块链文件才能转储到 SATA 盘上，且转的文件之和不能超过 SATA 盘的容量。
 * 假设每块 SATA 盘容量为 M，求能转储的最大连续文件之和。
 * 输入描述
 * 第一行为 SATA 盘容量 M，1000 <=M <= 1000000
 * 第二行为区块链文件大小序列 F1,F2.....Fn。其中 1<=n<=100000，1<=Fi<= 500
 * 输出描述
 *   求能转储的最大连续文件大小之和
 *   用例
 *    输入：1000
 *         100 300 500 400 400 150 100
 *    输出：950
 *
 *
 *    输入：1000
 *         100 500 400 150 500 100
 *    输出： 1000
 * */

public class _003_区块链文件转储系统 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxSize = Integer.parseInt(sc.nextLine());
        int[] fArray = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        findMaxSata(maxSize,fArray);
    }

    public static void findMaxSata(int maxSize,int[] fArray){

        int left = 0;
        int right = 0;
        int findMax = 0;
        int sum = 0;
        while (right < fArray.length &&  left < fArray.length ) {
            if(sum + fArray[right] < maxSize){
                sum += fArray[right];
                findMax = Math.max(findMax,sum);
                right ++;
            } else if(sum + fArray[right] == maxSize){
               findMax = maxSize;
               break;
            } else {
                if(sum + fArray[right] - fArray[left] <= maxSize){
                    sum = sum + fArray[right] - fArray[left];
                    findMax = Math.max(findMax,sum);
                    left++;
                    right++;
                } else {
                    if(right < left){
                        right++;
                    }
                    sum -= fArray[left];
                    left++;
                }
            }
        }

        System.out.println(findMax);

    }
}
