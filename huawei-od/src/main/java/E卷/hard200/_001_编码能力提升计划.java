package E卷.hard200;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/3 06:59
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _001_编码能力提升计划 {
    /**
     * 题目描述
     * 为了提升软件编码能力，小王制定了刷题计划，他选了题库中的n道题，编号从0到n-1，并计划在m天内按照题目编号顺序刷完所有的题目（注意，小王不能用多天完成同一题）。
     * 在小王刷题计划中，小王需要用tme[i]的时间完成编号 i 的题目。
     * 此外，小王还可以查看答案，可以省去该题的做题时间。为了真正达到刷题效果，小王每天最多直接看一次答案。
     * 我们定义m天中做题时间最多的一天耗时为T（直接看答案的题目不计入做题总时间)。
     * 请你帮小王求出最小的T是多少。
     * 输入描述
     * 第一行输入为time，time[i]的时间完成编号 i 的题目
     * 第二行输入为m，m表示几天内完成所有题目，1 ≤ m ≤ 180
     * 输出描述
     * 最小耗时整数T
     *
     * 示例1
     * 输入
     * 999,999,999
     * 4
     * 输出
     * 0
     * 说明
     *
     * 在前三天中，小王每天都直接看答案，这样他可以在三天内完成所有的题目并不花任何时间
     *
     * 示例2
     * 输入
     * 1,2,2,3,5,4,6,7,8
     * 5
     * 输出
     * 4
     * 说明
     *
     * 第一天完成前3题，第3题看答案;
     * 第二天完成第4题和第5题，第5题看答案；
     * 第三天完成第6和第7题，第7提看答案;
     * 第四天完成第8题，直接看答案:
     * 第五天完成第9题，直接看答案
     *
     * 解题思路
     * 解释
     * 在示例2中，输入如下：
     * 1,2,2,3,5,4,6,7,8
     * 5
     * 这意味着小王有9道题目，每道题目的完成时间分别是1, 2, 2, 3, 5, 4, 6, 7, 8，且他需要在5天内完成这些题目。目标是使得这5天中最繁忙的一天（耗时最长的一天）的耗时T尽可能小。
     *
     * 解释如何安排：
     * 第一天：
     * 完成题目0 (1分钟), 题目1 (2分钟) 和题目2 (2分钟)。
     * 可选择题目2作为查看**的题目，因为它不是最耗时的。
     * 实际耗时：1 + 2 = 3分钟
     * 第二天：
     * 完成题目3 (3分钟) 和题目4 (5分钟)。
     * 可选择题目4作为查看**的题目，因为它是这两题中较耗时的。
     * 实际耗时：3分钟
     * 第三天：
     * 完成题目5 (4分钟) 和题目6 (6分钟)。
     * 可选择题目6作为查看**的题目，因为它是这两题中较耗时的。
     * 实际耗时：4分钟
     * 第四天：
     *
     * 只完成题目7 (7分钟)。
     * 选择查看**的题目。
     * 实际耗时：0分钟
     * 第五天：
     *
     * 只完成题目8 (8分钟)。
     * 选择查看**的题目。
     * 实际耗时：0分钟
     * 解题思路
     * 这个问题可以通过二分搜索和贪心策略来解决。我们要找的是最小的最大单日工作时间 T，可以通过以下步骤来寻找：
     * 初始化二分搜索的边界：
     * low = 0（理论上的最低耗时，假设每天都能看答案）。
     * high = sum(times) - max(times)（如果最耗时的题目使用了看答案的机会，剩余的总时间是理论上的最高耗时）。
     * 二分搜索过程：
     * 计算中点 mid 作为假设的最大单日工作时间。
     * 使用贪心策略模拟小王的刷题过程，看是否可以在 m 天内完成所有题目，且每天的工作时间不超过 mid。
     * 维护当前累计的工作时间 currentSum 和当前天数 currentDay。
     * 遍历题目数组 times，对于每一题：
     * 尝试添加到当前天的工作时间。
     * 如果加上这题的时间后超过了 mid，使用看答案机会（如果当天还未使用），然后尝试将这题放入下一天。
     * 如果当天已使用看答案机会，需要新开一天，并重置当前天的时间累计和看答案机会。
     * 检查在假设的最大单日工作时间 mid 下，是否能在 m 天内完成所有题目。
     * 调整搜索范围：
     *
     * 如果可以完成，说明 mid 可能还不是最小的，尝试减小 high。
     * 如果不可以完成，增加 low。
     * 输出结果：
     *
     * 当 low 超过 high，循环结束，此时 low 将是满足条件的最小的最大单日工作时间 T。
     * 这个方法结合了二分搜索的高效查找和贪心策略的局部最优解法，能够有效找到解决问题的最小 T 值。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int[] split = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
            int day = Integer.parseInt(scanner.nextLine());
            int cost = minTime(split, day);
            System.out.println(cost);
        }
    }

    /**
     * 模拟小王刷题
     * @param times
     * @param day
     * @return
     */
    public static int minTime(int[] times, int day) {
        int totalTime = 0;
        int maxTime = 0;
        for (int time : times) {
            totalTime += time;
            maxTime = Math.max(maxTime, time);
        }
        int left = 0;
        int right = totalTime - maxTime;
        while (left <= right) {
            //右移1位，相当于除以2
            int mid = (left + right) >> 1;

            boolean flag = true;
            int currentSum = 0;
            int currentDay = 1;
            int i = 0;
            while (i < times.length) {
                currentSum += times[i];
                if (currentSum <= mid) {
                    i++;
                } else {
                    // 如果当前和超过了 mid
                    if (flag) {
                        // 如果当前和超过了 mid，尝试不计入当前任务，继续下一任务
                        currentSum -= times[i];
                        flag = false;
                        i++;
                    } else {
                        // 如果不能再调整，增加天数，重置当前和和调整标记
                        currentDay++;
                        if (currentDay > day) {
                            // 如果天数超过最大允许值，中断内部循环
                            break;
                        }
                        currentSum = 0;
                        flag = true;
                    }
                }
            }

            if (i == times.length && currentDay <= day) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
