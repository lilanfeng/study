package E卷.hard200;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/8 09:54
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _012_可以处理的最大任务数 {

    /**
     * 题目描述
     * 在某个项目中有多个任务(用tasks数组表示)需要您进行处理，其中tasks[i]=[si,ei],
     * 你可以在si <= day <= ei中的任意一天处理该任务，请返回你可以处理的最大任务数
     *
     * 输入描述
     * 第一行为任务数量n，1 <=n<= 100000。后面n行表示各个任务的开始时间和终止时间，
     * 使用si,ei表示,1 <= si <= ei <= 100000
     *
     * 输出描述
     * 输出为一个整数，表示以处理的最大任可务数。
     *
     * 用例
     * 输入
     * 3
     * 1 1
     * 1 2
     * 1 3
     * 输出
     * 3
     * 解题思路
     * 贪心算法原则：每一步选择当前情况下最优的选择（这里是选择结束时间最早的任务），以达到全局最优解。
     * 这种方法适用于任务调度问题，因为优先完成结束时间早的任务可以为后续任务腾出更多时间。
     *
     * 使用优先队列：
     * 优先队列特性：自动根据任务的结束时间进行排序，保证每次取出的都是结束时间最早的任务。
     * 应用：在处理任务时，将所有任务按开始时间存入列表（数组的每个元素是一个列表，对应于该开始时间的所有任务）。
     * 然后，遍历每个时间点，将该时间点开始的所有任务加入优先队列。这样，优先队列总是包含当前可执行的任务，且队首是最优先执行的任务。
     * 任务调度：
     * 移除过期任务：在每个时间点，首先检查优先队列中是否有已经结束的任务（即结束时间小于当前时间的任务），
     * 将这些任务从队列中移除。这一步确保队列中的任务都是未完成且可执行的。
     * 加入新任务：将当前时间点开始的所有任务加入优先队列。这些任务现在是候选任务，准备被执行。
     * 执行任务：如果优先队列不为空，说明有可执行的任务。从队列中取出（移除）一个任务执行，即完成一个任务，完成任务的计数加一。
     * 按照贪心原则，这个任务是当前所有可执行任务中结束时间最早的。
     * 总结：通过贪心算法选择每一步的最优解（结束时间最早的任务），并利用优先队列自动维护任务的执行顺序，可以有效地解决任务调度问题，
     * 最大化完成的任务数量。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = Integer.parseInt(scanner.nextLine());
        String[] taskStrArray = new String[m];
        for (int i = 0; i < m; i++) {
            taskStrArray[i] = scanner.nextLine();
        }
        scanner.close();
        System.out.println(getMaxTaskCount(taskStrArray));
    }

    /**
     *
     */
    public static int getMaxTaskCount(String[] tasks) {

        List<Task>[] taskList = new ArrayList[100001];
        for (int i = 0; i < taskList.length; i++) {
            taskList[i] = new ArrayList<>();
        }
        for (int i = 0; i < tasks.length; i++) {
            String[] task = tasks[i].split(" ");
            int start = Integer.parseInt(task[0]);
            int end = Integer.parseInt(task[1]);
            taskList[start].add( new Task(start, end));
        }
        PriorityQueue<Task> queue = new PriorityQueue<>(Comparator.comparingInt(t -> t.end));
        int finishCount = 0;

        for (int i = 1; i < 100001; i++) {
            // 移除过期任务
            while (!queue.isEmpty() && queue.peek().end < i) {
                queue.poll();
            }
            if (taskList[i] != null) {
                for (Task task : taskList[i]) {
                    queue.add(task);
                }
            }
            if (!queue.isEmpty()) {
                queue.poll();
                finishCount++;
            }
        }

        return finishCount;
    }

    public static class Task {
        int start;
        int end;

        public Task(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
