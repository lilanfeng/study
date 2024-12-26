package 全部;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.lang.String;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 */
public class _001_两数之和{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
            int target = Integer.parseInt(sc.nextLine());
            System.out.println(Arrays.toString(twoSum(nums, target)));
        }

    }

    public static int[] twoSum(int[] nums, int target) {

        Map<Integer,Integer> numsMap = new HashMap<Integer, Integer>(nums.length);
        int[] targetArray = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if(numsMap.containsKey(target-nums[i])){
                targetArray[0] = numsMap.get(target-nums[i]);
                targetArray[1] = i;
            }else {
                numsMap.put(nums[i],i);
            }
        }
        return targetArray;
    }
}
