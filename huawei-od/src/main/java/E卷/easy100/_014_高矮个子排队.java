package E卷.easy100;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _014_高矮个子排队 {
    /**
     * 题目描述
     * 现在有一队小朋友，他们高矮不同，我们以正整数数组表示这一队小朋友的身高，如数组{5,3,1,2,3}。
     * 我们现在希望小朋友排队，以“高”“矮”“高”“矮”顺序排列，每一个“高”位置的小朋友要比相邻的位置高或者相等；每一个“矮”位置的小朋友要比相邻的位置矮或者相等；
     * 要求小朋友们移动的距离和最小，第一个从“高”位开始排，输出最小移动距离即可。
     *
     * 例如，在示范小队{5,3,1,2,3}中，{5, 1, 3, 2, 3}是排序结果。
     *
     * {5, 2, 3, 1, 3} 虽然也满足“高”“矮”“高”“矮”顺序排列，但小朋友们的移动距离大，所以不是最优结果。
     * 移动距离的定义如下所示：
     *
     * 第二位小朋友移到第三位小朋友后面，移动距离为1，若移动到第四位小朋友后面，移动距离为2；
     *
     * 输入描述
     * 排序前的小朋友，以英文空格的正整数：
     * 4 3 5 7 8
     * 注：小朋友<100个
     *
     * 输出描述
     * 排序后的小朋友，以英文空格分割的正整数：4 3 7 5 8
     *
     * 备注：4（高）3（矮）7（高）5（矮）8（高）， 输出结果为最小移动距离，只有5和7交换了位置，移动距离都是1。
     *
     * 示例1
     * 输入
     * 4 1 3 5 2
     * 输出
     *  4 1 5 2 3
     * 说明
     *
     * 示例2
     * 输入
     * 1 1 1 1 1
     * 输出
     * 1 1 1 1 1
     * 说明
     *
     * 相邻位置可以相等
     *
     * 示例3
     * 输入
     * xxx
     * 输出
     * [ ]
     * 说明
     * 出现非法参数情况， 返回空数组。
     * 解题思路
     * 这道题看似简单，但看完题目可能会觉得不止这么简单。因为要保证移动距离最小，这意味着可能存在多种情况需要多次比较。不过，实际并没有那么复杂。
     *
     * 比如在用例1中，乍一看好像有点问题：直接让5和2交换位置，得到的结果是：4 1 3 2 5，这样也符合题意，而且移动距离只有1，似乎更符合要求。
     *
     * 然而，这样想的同学可能忽略了题目中的一句关键提示：“第一个从‘高’位开始排”。这句话的意思是，我们只需要从第一个小朋友开始排列，并在发现不符合要求的排队顺序时，就进行交换。这样大大降低了题目难度。
     *
     * 代码解释
     * 为了实现将小朋友的身高按照“高矮交替”的顺序排列，代码中使用了以下判断条件：
     *
     * heights[i] != heights[j] && (heights[i] > heights[j]) != (i % 2 == 0)
     * 这个条件可以分成两个部分来解释：
     * heights[i] != heights[j]：
     *
     * 这个条件确保只在两个相邻的小朋友身高不相等的情况下才进行进一步的判断。如果两个小朋友的身高相等，那么无需交换位置，因为它们已经符合“高矮交替”的要求。
     * (heights[i] > heights[j]) != (i % 2 == 0)：
     * 这个条件用于检查当前的排列是否符合“高矮交替”的要求。
     * i % 2 == 0：判断当前索引 i 是否为偶数。根据题目的要求，如果 i 是偶数位置，那么我们期望 heights[i] > heights[j]，即当前小朋友的身高应该高于下一个小朋友的身高。如果 i
     * 是奇数位置，我们期望 heights[i] < heights[j]。
     * (heights[i] > heights[j]) != (i % 2 == 0)：这一部分的意思是：
     * 如果 i 是偶数，那么期望 heights[i] > heights[j]。如果此时 heights[i] > heights[j] 为 true，与 i % 2 == 0 的结果相同，所以条件成立，说明不需要交换。
     * 如果 i 是奇数，那么期望 heights[i] < heights[j]。如果此时 heights[i] > heights[j] 为 false，与 i % 2 == 0 的结果相反，条件成立，说明不需要交换。
     * 如果实际情况与期望情况不符（即 heights[i] > heights[j] 和 i 的奇偶性不一致），那么 (heights[i] > heights[j]) != (i % 2 == 0) 结果为
     * true，表示当前排列不符合要求，需要交换 heights[i] 和 heights[j]。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String split = scanner.nextLine();
            try {
                int[] splitArray = Arrays.stream(split.split(" ")).mapToInt(Integer::parseInt).toArray();
                sortHeight(splitArray);
                if (splitArray.length == 0){
                    System.out.println("[ ]");
                }else {
                    StringJoiner stringJoiner = new StringJoiner(" ");
                    for (int i : splitArray) {
                        stringJoiner.add(String.valueOf(i));
                    }
                    System.out.println(stringJoiner);
                }
            }catch (Exception e){
                System.out.println("[ ]");
            }

        }
    }

    /**
     * 排序
     * @param heights
     */
    public static void sortHeight(int[] heights) {
        if (heights.length == 0) {
            return;
        }
        int left = 0, right = 1;
        while (right < heights.length) {
            if (heights[left] != heights[right] && (heights[left] > heights[right]) != (left % 2 == 0)) {
                int temp = heights[left];
                heights[left] = heights[right];
                heights[right] = temp;
            }
            right++;
            left++;
        }
    }
}
