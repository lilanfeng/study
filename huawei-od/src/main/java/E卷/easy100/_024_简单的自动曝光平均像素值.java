package E卷.easy100;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/5 16:13
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _024_简单的自动曝光平均像素值 {
    /**
     * 题目描述
     * 一个图像有n个像素点，存储在一个长度为n的数组img里，每个像素点的取值范围[0,255]的正整数。
     *
     * 请你给图像每个像素点值加上一个整数k（可以是负数），得到新图newImg，使得新图newImg的所有像素平均值最接近中位值128。
     *
     * 请输出这个整数k。
     *
     * 输入描述
     * n个整数，中间用空格分开
     *
     * 备注
     * • 1 <= n <= 100
     * • 如有多个整数k都满足，输出小的那个k；
     * • 新图的像素值会自动截取到[0,255]范围。当新像素值<0，其值会更改为0；当新像素值>255，其值会更改为255；
     *
     * 例如newImg=”-1 -2 256″,会自动更改为”0 0 255″
     *
     * 输出描述
     * 一个整数k
     *
     * 示例1
     * 输入
     * 129 130 129 130
     * 输出
     * -2
     * 说明
     *
     * -1的均值128.5，-2的均值为127.5，输出较小的数-2
     * 示例2
     * 输入
     * 0 0 0 0
     * 输出
     * 128
     * 说明
     *
     * 四个像素值都为0
     *
     * 解题思路
     * 代码思路
     * 本题的解题思路是通过枚举每一个可能的 k值，计算新图像的每个像素点的值，并找出使得新图像的平均值与中位值 128 的差的绝对值最小的 k 值。
     *
     * 具体实现步骤如下：
     *
     * 枚举每一个可能的 k kk 值：
     *
     * 计算新图像的每个像素点的值。
     * 将这些像素点的值累加起来，得到新图像的所有像素点值的和 sum。
     * 计算平均值和差的绝对值：
     *
     * 计算新图像的平均值 sum / len。
     * 计算新图像的平均值与中位值 128 之间的差的绝对值 diff。
     * 更新最优解：
     *
     * 如果 diff 小于 min_diff，更新 min_diff 为当前的 diff，并更新 k_ans 为当前的 k 值。
     * 如果 diff 等于 min_diff 且 k_ans 不等于 0，则更新 k_ans 为 k 和 k_ans 中的较小值。
     * 注意事项
     * 对于每一个像素点的新值，需要确保其在 [0, 255] 的范围内：
     *
     * 如果新值小于 0，则将其设为 0。
     * 如果新值大于 255，则将其设为 255。
     * 如果有多个整数 k 满足条件，输出较小的那个 k。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        scanner.close();
        System.out.println(findClosestIndex(nums));
    }

    /**
     *  寻找最接近中位值的整数 128
     * @param nums
     * @return
     */
    public static int findClosestIndex(int[] nums) {
        double min_diff = Integer.MAX_VALUE;
        int min_k = 0;
        for (int i = -127; i <= 128; i++) {
            double sum = 0;
            for (int num : nums) {
                int new_num = num + i;
                if (new_num < 0) {
                    new_num = 0;
                } else if (new_num > 255) {
                    new_num = 255;
                }
                sum += new_num;
            }
            double diff = Math.abs(sum / nums.length - 128);

            if (diff < min_diff) {
                min_diff = diff;
                min_k = i;
            } else if (diff == min_diff && min_k != 0) {
                min_k = Math.min(min_k, i);
            }
        }
        return min_k;
    }
}
