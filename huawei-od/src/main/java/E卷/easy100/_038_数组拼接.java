package E卷.easy100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/10 20:27
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _038_数组拼接 {
    /**
     * 题目描述
     * 现在有多组整数数组，需要将它们合并成一个新的数组。
     * 合并规则，从每个数组里按顺序取出固定长度的内容合并到新的数组中，取完的内容会删除掉，
     * 如果该行不足固定长度或者已经为空，则直接取出剩余部分的内容放到新的数组中，继续下一行。
     * 输入描述
     * 第一行是每次读取的固定长度，0<长度<10
     * 第二行是整数数组的数目，0<数目<1000
     * 第3-n行是需要合并的数组，不同的数组用回车换行分隔，数组内部用逗号分隔，最大不超过100个元素。
     * 输出描述
     * 输出一个新的数组，用逗号分隔。
     * 示例1
     * 输入
     * 3
     * 2
     * 2,5,6,7,9,5,7
     * 1,7,4,3,4
     * 输出
     * 2,5,6,1,7,4,7,9,5,3,4,7
     * 说明
     *
     * 1、获得长度3和数组数目2
     * 2、先遍历第一行，获得2,5,6
     * 3、再遍历第二行，获得1,7,4
     * 4、再循环回到第一行，获得7,9,5
     * 5、再遍历第二行，获得3,4
     * 6、再回到第一行，获得7，按顺序拼接成最终结果
     *
     * 示例2
     * 输入
     * 4
     * 3
     * 1,2,3,4,5,6
     * 1,2,3
     * 1,2,3,4
     * 输出
     * 1,2,3,4,1,2,3,1,2,3,4,5,6
     * 说明
     * 解题思路
     * 题目的要求是将多组整数数组合并成一个新的数组，合并的规则是从每个数组中按顺序取出指定长度的元素，然后将取出的元素拼接到新的数组中，直到所有数组中的元素全部被取完为止。
     * 合并规则详解：
     * 固定长度：每次从一个数组中取出的元素个数是固定的，假设为L。
     * 多数组操作：数组的数目是N个，需要按顺序逐个遍历这些数组。
     * 按序取元素：
     * 如果一个数组剩余的元素不足L，则取出剩余的所有元素。
     * 如果一个数组的元素已经全部取完，则跳过该数组，继续处理下一个数组。
     * 循环合并：每次遍历完所有数组后，若还有未取完的数组，继续从第一个数组开始按顺序取，直到所有数组都取完为止。
     * 示例解释：
     * 示例1
     * 输入
     * 3
     * 2
     * 2,5,6,7,9,5,7
     * 1,7,4,3,4
     * 步骤解释：
     * 固定长度为3，数组数目为2。
     * 第一轮：
     * 从第一个数组取3个元素：2,5,6。
     * 从第二个数组取3个元素：1,7,4。
     * 第二轮：
     * 从第一个数组取3个元素：7,9,5（接着取未取完的部分）。
     * 从第二个数组取2个元素：3,4（该数组剩余的元素不足3个，取出剩余部分）。
     * 第三轮：
     * 从第一个数组取1个元素：7（剩余的最后一个元素）。
     * 第二个数组已取完，跳过。
     * 合并后的新数组为：2,5,6,1,7,4,7,9,5,3,4,7。
     * 输出
     * 2,5,6,1,7,4,7,9,5,3,4,7
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalSize = 0;
        while (scanner.hasNextLine()) {
            int n = Integer.parseInt(scanner.nextLine());
            int m = Integer.parseInt(scanner.nextLine());
            List<List<Integer>> nums = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                String[] temp = scanner.nextLine().split(",");
                List<Integer> tempList = new ArrayList<>();
                for (int j = 0; j < temp.length; j++) {
                  tempList.add(Integer.parseInt(temp[j]));
                  totalSize++;
                }
                nums.add(tempList);
            }
            System.out.println(mergeArrays(nums, n,totalSize));
        }
        scanner.close();
    }

    /**
     *
     * @param nums
     * @param mergeNum
     * @return
     */
    private static String  mergeArrays(List<List<Integer>> nums,int mergeNum,int totalSize) {
        StringJoiner joiner = new StringJoiner(",");
        int index = 0;
        while (index < totalSize) {
            for(int i = 0; i < nums.size(); i++){
                for (int j = 0; j < mergeNum; j++) {
                    if(nums.get(i).size() > 0){
                        joiner.add(nums.get(i).get(0).toString());
                        index++;
                        nums.get(i).remove(0);
                    }
                }
            }
        }

        return joiner.toString();
    }


}
