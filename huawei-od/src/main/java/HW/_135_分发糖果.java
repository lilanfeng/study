package HW;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 *
 * 你需要按照以下要求，给这些孩子分发糖果：
 *
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 * 示例 2：
 *
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 *
 *
 * 提示：
 *
 * n == ratings.length
 * 1 <= n <= 2 * 10 4
 * 0 <= ratings[i] <= 2 * 10 4
 */
public class _135_分发糖果 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            System.out.println(candy(Arrays.stream(sc.nextLine().split(","))
                    .mapToInt(Integer::parseInt).toArray()));
        }
    }

    /**
     *
     * @param ratings
     * @return
     */
    public static int candy(int[] ratings){
        int length = ratings.length;
        int ret = 1;
        int increment = 1;
        int decrement = 0;
        int pre = 1;

        for (int i = 1; i < length; i++) {
            if(ratings[i] >= ratings[i-1]){
                //后面的得分比前面的得分多
                decrement = 0;
                pre = ratings[i] == ratings[i-1] ? 1 : pre + 1;
                ret += pre;
                increment = pre;
            }else {
                decrement++;
                if(decrement == increment){
                    decrement++;
                }
                ret += decrement;
                pre = 1;
            }

        }
        return ret;
    }
}
