package E卷.easy100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/7 15:54
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _036_数字排列 {

    /**
     * 题目描述
     * 小明负责公司年会，想出一个趣味游戏：
     * 屏幕给出 1 ~ 9 中任意 4 个不重复的数字，大家以最快时间给出这几个数字可拼成的数字从小到大排列位于第 N 位置的数字，
     * 其中 N 为给出数字中最大的（如果不到这么多数字则给出最后一个即可）。
     * 注意：
     *
     * 2 可以当作 5 来使用，5 也可以当作 2 来使用进行数字拼接，且屏幕不能同时给出 2 和 5；
     * 6 可以当作 9 来使用，9 也可以当作 6 来使用进行数字拼接，且屏幕不能同时给出 6 和 9。
     * 如给出：1，4，8，7，则可以拼接的数字为：
     *
     * 1，4，7，8，14，17，18，41，47，48，71，74，78，81，84，87，147，148，178 … (省略后面的数字)
     *
     * 那么第 N （即8）个的数字为 41。
     *
     * 输入描述
     * 输入以逗号分隔的 4 个 int 类型整数的字符串。
     *
     * 输出描述
     * 输出为这几个数字可拼成的数字从小大大排列位于第 N （N为输入数字中最大的数字）位置的数字，
     * 如果输入的数字不在范围内或者有重复，则输出-1。
     * 示例1
     * 输入
     * 1,4,8,7
     * 输出
     * 41
     * 说明
     * 可以构成的数字按从小到大排序为：
     * 1，4，7，8，14，17，18，41，47，48，71，74，78，81，84，87，147，148，178 … （省略后面的数字），
     * 故第8个为41
     * 示例2
     * 输入
     * 2,5,1
     * 输出
     * -1
     * 说明
     * 2和5不能同时出现
     *
     * 示例3
     * 输入
     * 3,0,9
     * 输出
     * -1
     * 说明
     * 0不在1到9范围内
     * 示例4
     * 输入
     * 3,9,7,8
     * 输出
     * 39
     * 说明
     * 注意9可以当6使用，所以可以构成的数字按从小到大排序为：3，6，7，8，9，36，37，38，39，63，67，68，73，76，78，79，83 … （省略后面的数字），
     * 故第9个为39
     *
     * 解题思路
     * 题目解析
     * 小明的游戏需要从给定的4个不同的数字中，找到所有可以拼接出来的数字，按从小到大的顺序排列，最后取出第 N 个数字输出，N 是给定4个数字中最大的那个数字。
     * 数字只能从1到9中选择。
     * 数字2和5互相可以替代，6和9互相可以替代，但不能同时出现（例如，2和5不能同时出现在输入中，6和9也不能同时出现）。
     * 输入数字必须是4个且无重复。
     * 如果输入的数字不符合上述要求（比如含有重复数字、包含不在范围内的数字，或同时包含了2和5、6和9），则输出-1。
     * 取出第N个排列的数字：
     * 找到这些排列中第 N 个位置的数字（N 是输入中最大的数字）。
     * 如果排列数量不够N个，则取最后一个数字。
     * 方法
     * 检查输入的合法性：
     * 检查输入数字的数量是否为4个。
     * 检查数字是否都在1到9之间。
     * 检查是否有重复数字。
     * 检查是否同时包含2和5，或同时包含6和9，如果有则直接输出-1。
     * 生成所有排列：因为本题数量级不大，可以考虑生成所有数字的排列组合，并将结果按从小到大的顺序排列。
     *
     * 示例解析
     * 示例1
     * 输入：1,4,8,7
     * 输入数字为1, 4, 8, 7。没有冲突的数字。
     * 最大的数字是8，所以我们需要找第8个排列的数字。
     * 按从小到大的顺序排列为：1, 4, 7, 8, 14, 17, 18, 41, 47, 48, 71, 74, 78, 81, 84, 87, …
     * 第8个数字为 41，所以输出 41。
     * 示例4
     * 输入：3,9,7,8
     * 输入数字为3, 9, 7, 8。9可以作为6使用，没有冲突。
     * 最大的数字是9，所以我们需要找第9个排列的数字。
     * 按从小到大的顺序排列为：3, 6, 7, 8, 9, 36, 37, 38, 39, 63, 67, 68, …
     * 第9个数字为 39，所以输出 39。
     */
    public static void main(String[] args) {
        //String[] nums = {"3","9","7","8"};
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        scanner.close();
        System.out.println(findMaxIndex(nums));
    }

    /**
     * 获取最大值
     * @param nums
     * @return
     */
    public static String findMaxIndex(int[] nums){
        String  result = "-1";
        int index = 0;
        HashSet<Integer> set = new HashSet<>();
        for(int num:nums){
            if(num<1 || num>9){
                return result;
            }
            if(set.contains(num)){
                return result;
            }
            set.add(num);
            index = Math.max(index,num);
        }
        if (set.size() != 4 || (set.contains(2) && set.contains(5)) || (set.contains(6) && set.contains(9))) {
            return result;
        }
        ArrayList<Integer> list = new ArrayList<>();
        int[] replaceNums = new int[10];
        replaceNums[2] = 5;
        replaceNums[5] = 2;
        replaceNums[6] = 9;
        replaceNums[9] = 6;
        buildNumbers(nums,list,replaceNums,"",new HashSet<>());
        if(list.isEmpty()){
            return result;
        }
        list.sort(Comparator.naturalOrder());
        if (index >= list.size()) {
            result = list.get(list.size() - 1).toString();
        } else {
            result = list.get(index).toString();
        }
        return result;
    }

    /**
     * 递归生成数字
     * @param nums
     * @param resultList
     * @param replaceNums
     * @param path
     * @param usedSet
     */
    private static void buildNumbers(int[] nums, List<Integer> resultList, int[] replaceNums, String path, Set<Integer> usedSet){

        // path 拼接
        if (!path.isEmpty()) {
            resultList.add(Integer.parseInt(path));
        }
        //递归结束条件
        if (path.length() == nums.length) {
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (usedSet.contains(num)) {
                continue;
            }
            usedSet.add(num);
            buildNumbers(nums, resultList, replaceNums, path + num, usedSet);

            if (replaceNums[num] != 0 && !usedSet.contains(replaceNums[num])) {
                buildNumbers(nums, resultList, replaceNums, path + replaceNums[num], usedSet);
            }
            usedSet.remove(num);
        }
    }

}
