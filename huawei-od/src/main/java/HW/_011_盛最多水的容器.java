package HW;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 * 提示：
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 */
public class _011_盛最多水的容器 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()){
            System.out.println(maxArea(Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray()));
        }

    }


    public static int maxArea(int[] height){
        int maxArea = 0;
        int left = 0;
        int right = height.length -1;
        while (left < right){
            maxArea = Math.max(maxArea,(right - left) * Math.min(height[left],height[right]));
            left++;
            maxArea = Math.max(maxArea,(right - left) * Math.min(height[left],height[right]));
            right--;
        }
        return maxArea;
    }
}
