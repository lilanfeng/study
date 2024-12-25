package E卷.easy100;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/14 17:30
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _048_游戏分组王者荣耀 {
    /**
     *  题目描述
     * 2020年题：
     *
     * 英雄联盟是一款十分火热的对战类游戏。每一场对战有10位玩家参与，分为两组，每组5
     * 人。每位玩家都有一个战斗力，代表着这位玩家的厉害程度。为了对战尽可能精彩，我们需要把玩家们分为实力尽量相等的两组。一组的实力可以表示为这一组5位玩家的战斗力和。现在，给你10
     * 位玩家的战斗力，请你把他们分为实力尽量相等的两组。请你输出这两组的实力差。
     *
     * 2023年题：
     *
     * 部门准备举办一场王者荣耀表演赛，有10名游戏爱好者参与，分5为两队，每队5人。每位参与者都有一个评分，代表着他的游戏水平。为了表演赛尽可能精彩，我们需要把10名参赛者分为实力尽量相近的两队。一队的实力可以表示为这一队5
     * 名队员的评分总和。
     * 现在给你10名参与者的游戏水平评分，请你根据上述要求分队最后输出这两组的实力差绝对值。
     * 例: 10名参赛者的评分分别为5 1 8 3 4 6 710 9 2，分组为 (135 8 10) (24 679)，两组实力差最小，差值为1。有多种分法，但实力差的绝对值最小为1。
     *
     * 输入描述
     * 10个整数，表示10名参与者的游戏水平评分。范围在[1,10000]之间
     *
     * 输出描述
     * 1个整数，表示分组后两组实力差绝对值的最小值.
     *
     * 用例1
     * 输入：
     * 1 2 3 4 5 6 7 8 9 10
     * 输出：
     * 1
     * 说明：
     *
     * 10名队员分成两组，两组实力差绝对值最小为1.
     *
     * 解题思路
     * 在这个问题中，我们通过深度优先搜索（DFS）尝试所有可能的分队方式，以找到实力差的绝对值最小的分队方案。整个算法的目标是遍历所有可能的组合，并计算出两队实力差的最小绝对值。
     *
     * 这里使用的深度优先搜索算法中，每一步都有两种选择：将当前玩家分配给第一队，或者不分配给第一队（即默认分配给第二队）。这样的策略保证了覆盖所有可能的分队方式。
     *
     * 解释代码段
     * // 为第一个队伍选择当前玩家
     * dfs(nums, idx + 1, count + 1, currentSum + nums[idx]);
     *
     * // 不为第一个队伍选择当前玩家
     * dfs(nums, idx + 1, count, currentSum);
     * 1
     * 2
     * 3
     * 4
     * 5
     * 这两行代码是DFS递归的核心，它们代表了在每一步有两种选择：
     *
     * 选择当前玩家加入第一队：这是通过dfs(nums, idx + 1, count + 1, currentSum + nums[idx]);实现的。这里idx + 1表示考虑下一个玩家，count +
     * 1表示第一队的玩家数增加了1，currentSum + nums[idx]表示第一队的总评分增加了当前玩家的评分。
     *
     * 不选择当前玩家加入第一队：即留给第二队，通过dfs(nums, idx + 1, count, currentSum);
     * 实现。这里只将idx增加1，移动到下一个玩家，而count和currentSum保持不变，因为没有新的玩家加入第一队。
     *
     * 整体逻辑
     * 初始时，totalSum计算了所有玩家的评分总和，targetSum是总和的一半。这是因为我们的目标是使两队的评分尽可能接近totalSum / 2。
     * 通过DFS尝试所有可能的分队方式，每次递归都有两种选择：将当前玩家加入第一队或不加入。
     * 当一个队伍选满5名玩家时，计算两队的评分差，并更新最小差值res。
     * 继续递归直到所有玩家都被考虑过，最终res会是实力差的最小绝对值。
     * 剪枝优化
     * 注释中提到的剪枝条件if (currentSum > targetSum) return;， 经过考友的反馈，去掉的话是100%的通过率，请各位机考时都加上去试试。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            int[] inputArr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int res = getMinDiff(inputArr);
            System.out.println(res);
        }
        scanner.close();
    }

    private static int getMinDiff(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        int targetSum = totalSum / 2;
        return dfs(nums, 0, 0, 0, totalSum, targetSum, Integer.MAX_VALUE);
    }

    private static int dfs(int[] nums, int idx, int count, int currentSum, int totalSum, int targetSum, int res) {

        // 剪枝优化，如果当前队和总评分差大于目标值，则直接返回，剪枝
        if (currentSum > targetSum){
            return res;
        }

        if (count == 5) {
            // 队满，计算两队评分差，更新最小差值res
            int diff = Math.abs(totalSum - 2 * currentSum);
            res = Math.min(res, diff);
            return res;
        }

        if(idx >= nums.length){
            return res;
        }

        // 为第一个队伍选择当前玩家
        res = dfs(nums, idx + 1, count + 1, currentSum + nums[idx], totalSum, targetSum, res);

        // 不为第一个队伍选择当前玩家
        res = dfs(nums, idx + 1, count, currentSum, totalSum, targetSum, res);

        return res;
    }



}
