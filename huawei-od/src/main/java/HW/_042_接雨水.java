package HW;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 *  给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *  示例 1：
 *
 *
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 *
 * 提示：
 *
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */
public class _042_接雨水 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()){
            System.out.println(trap(Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
        }
    }

    public static int trap(int[] height){
        int maxArea = 0;
        int maxLeftHeight = 0;
        int maxRightHeight = 0;
        int left = 1;
        int right = height.length -2;

        for (int i = 1; i < height.length -1; i++) {
            if(height[left - 1] < height[right +1]){
                //从左到右变更
                maxLeftHeight = Math.max(maxLeftHeight,height[left - 1]);
                if(maxLeftHeight > height[left]){
                    maxArea = maxArea + (maxLeftHeight - height[left]);
                }
                left++;

            } else {
                //从右到左变更
                maxRightHeight = Math.max(maxRightHeight,height[right+1]);
                if(maxRightHeight > height[right]){
                    maxArea = maxArea + (maxRightHeight -  height[right]);
                }
                right--;
            }
        }
        return maxArea;
    }
}
