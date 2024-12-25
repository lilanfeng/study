package E卷.easy100;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/10 17:32
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _037_数字涂色 {
    /**
     * 题目描述
     * 疫情过后，希望小学终于又重新开学了，三年二班开学第一天的任务是将后面的黑板报重新制作。
     * 黑板上已经写上了N个正整数，同学们需要给这每个数分别上一种颜色。
     * 为了让黑板报既美观又有学习意义，老师要求同种颜色的所有数都可以被这种颜色中最小的那个数整除。
     * 现在请你帮帮小朋友们，算算最少需要多少种颜色才能给这N个数进行上色。
     *
     * 输入描述
     * 第一行有一个正整数N，其中。
     * 第二行有N个int型数(保证输入数据在[1,100]范围中)，表示黑板上各个正整数的值。
     *
     * 输出描述
     * 输出只有一个整数，为最少需要的颜色种数。
     *
     * 示例1
     * 输入
     * 3
     * 2 4 6
     * 输出
     * 3
     * 2 4 6
     * 说明
     *
     * 有数都能被2整除
     *
     * 示例2
     * 输入
     * 4
     * 2 3 4 9
     * 输出
     * 2
     * 说明
     *
     * 与4涂一种颜色，4能被2整除；3与9涂另一种颜色，9能被3整除。不能4个数涂同一个颜色，因为3与9不能被2整除。所以最少的颜色是两种。
     *
     * 解题思路
     * 题目要求给黑板上的 N 个正整数 上色，具体的要求是：同种颜色的所有数都可以被这一颜色中最小的那个数整除。
     *
     * 换句话说，如果我们为某个数字选择了一种颜色，那么所有和它涂同种颜色的数都应该是它的倍数。目标是找到最少的颜色种类来满足这个条件。
     *
     * 颜色分配逻辑：每个数字从最小开始，尝试加入已经存在的组中，只有当它无法整除任何一个已有组的最小数时，才新建一个组。这种策略确保了所有组中，数字都满足题目要求——同组内的所有数字都可以被该组的最小数整除。
     *
     * 可以分为以下几个步骤：
     *
     * 1. 输入数据并排序
     * 排序后的数组便于我们从最小的数字开始处理，因为最小数决定了它这一组的颜色。
     * 2. 定义颜色数组
     * 创建一个数组 colors，用来存储已经作为组最小数的数字。
     * colorCount 用来统计已经使用了多少种颜色，即有多少个组。
     * 3. 遍历每个数字，决定是否可以加入现有的颜色组
     * 遍历排序后的 numList 数组，对于每个数字 numList[i]：
     * 检查是否能被已有颜色组的最小数整除：通过 for 循环遍历 colors 数组，检查 numList[i] % colors[j] == 0，如果当前数字可以被某个已经存在的组的最小数整除（即
     * colors[j]），则认为这个数字可以归入该颜色组，设置 foundColor = true，并退出循环。
     * 如果没有找到合适的颜色组（即 foundColor == false），那么当前数字 numList[i] 就必须作为一个新的组的最小数。因此，将它添加到 colors 数组中，并增加颜色计数 colorCount。
     * 以示例2为例：
     *
     * 输入：
     * 4
     * 2 3 4 9
     * 对输入数据排序：[2, 3, 4, 9]
     * 遍历每个数字：
     * 2：没有已有组，所以新建一个组，colors = [2]，colorCount = 1
     * 3：没有现有组能够整除 3，所以新建一个组，colors = [2, 3]，colorCount = 2
     * 4：能被 2 整除，所以加入 2 所在的组，颜色数不增加。
     * 9：能被 3 整除，所以加入 3 所在的组，颜色数不增加。
     * 输出结果：2（需要两种颜色）。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());
        int[] numList  = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        if(number != numList.length){
            System.out.println("-1");
            return;
        }
        scanner.close();
        System.out.println(solveColor(numList));
    }

    /**
     * 颜色分配
     * @param numList
     * @return
     */
    public static int solveColor(int[] numList) {
        Arrays.sort(numList);
        int[] colors = new int[numList.length];
        int colorCount = 0;
        colors[0] = numList[0];
        for (int i = 0; i < numList.length; i++) {
            boolean foundColor = false;
            for (int j = 0; j < colorCount; j++) {
                if (numList[i] % colors[j] == 0) {
                    foundColor = true;
                    break;
               }
            }
            if (!foundColor) {
                colors[colorCount++] = numList[i];
            }
        }
        return colorCount;
    }
}
