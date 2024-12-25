package E卷.hard200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/8 11:05
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _014_任务最优调度 {
    /**
     * 题目描述
     * 给定一个正整数数组表示待系统执行的任务列表，数组的每一个元素代表一个任务，元素的值表示该任务的类型。
     * 请计算执行完所有任务所需的最短时间。
     * 任务执行规则如下:
     * 任务可以按任意顺序执行，且每个任务执行耗时间均为1个时间单位。
     * 两个同类型的任务之间必须有长度为N个单位的冷却时间，比如N为2时，在时间K执行了类型3的任务，
     * 那么K+1和K+2两个时间不能执行类型3任务。
     * 系统在任何一个单位时间内都可以执行一个任务，或者等待状态。
     * 说明：数组最大长度为1000，速度最大值1000。
     *
     * 输入描述
     * 第一行记录一个用半角逗号分隔的数组，数组长度不超过1000，数组元素的值不超过1000，
     *
     * 第二行记录任务冷却时间，N为正整数，N<=100。
     *
     * 输出描述
     * 输出为执行完所有任务所需的最短时间。
     *
     * 示例1
     * 输入
     * 2,2,2,3
     * 2
     * 输出
     * 7
     * 说明
     *
     * 时间1：执行类型2任务。
     * 时间2：执行类型3的任务（因为冷却时间为2，所以时间2不能执行类型2的任务）。
     * 时间3：系统等待（仍然在类型2的冷却时间）。
     * 时间4：执行类型2任务。
     * 时间5：系统等待。
     * 时间6：系统等待。
     * 时间7：执行类型2任务。
     * 因此总共耗时7。
     *
     * 解题思路
     * 任务类型：输入的数组表示任务列表，每个元素表示一种任务类型，相同的数字表示同一类型的任务。例如，数组 [2, 2, 2, 3] 表示三个类型为 2 的任务和一个类型为 3 的任务。
     * 冷却时间：在相同类型的任务之间必须间隔 N 个时间单位。比如，如果 N = 2，在某个时间执行了类型 2 的任务，那么接下来的 2 个时间单位内，不能再次执行类型 2 的任务。
     *
     * 任务执行或等待：系统每个时间单位要么执行任务，要么进入等待状态。即使系统没有可以执行的任务（因为冷却时间的限制），它也会进入等待，直到可以执行下一个任务。
     *
     * 思路
     * 优先执行任务:
     * 通过排序，使数量最多的任务优先执行，尽可能减少等待时间的数量。
     * 在每个时间单位内，尝试执行一个任务（如果没有任务可以执行，则进入等待状态）。
     * 冷却机制:
     * 对于每个任务，在执行后会设置冷却时间，在此期间不能再次执行同类型的任务。
     * 在每个时间单位内，所有任务的冷却时间都会减少，直到冷却期结束。
     * 时间计算:
     * 通过增加 time 变量，逐步模拟时间的流逝。每次执行任务或等待，time 都会增加。
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] taskStrArray = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int coolingTime = Integer.parseInt(scanner.nextLine());
        System.out.println(getMinTime(taskStrArray, coolingTime));

    }
    public static int getMinTime(int[] tasks, int coolingTime) {
        // 任务类型和数量
        HashMap<Integer, Integer> typeCountMap = new HashMap<>();
        for (int task : tasks) {
            typeCountMap.put(task, typeCountMap.getOrDefault(task, 0) + 1);
        }
        // 创建一个列表来存储每种任务的数量和冷却时间
        List<int[]> taskList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : typeCountMap.entrySet()) {
            int[] task = new int[]{entry.getKey(), entry.getValue(), 0};
            taskList.add(task);
        }
        taskList.sort((a, b) -> b[1] - a[1]);
        int totalTaskCount = tasks.length;
        int time = 0;
        while (totalTaskCount > 0) {
            time++;
            boolean taskExecuted = false;
            for (int[] task : taskList) {
                if (!taskExecuted && task[1] > 0 && task[2] == 0) {
                    // 当前任务可以执行  还存在任务，目前没有在冷却时间里面
                    taskExecuted = true;
                    task[1]--;
                    task[2] = coolingTime;
                    totalTaskCount--;
                }else {
                    if(task[2] > 0){
                        task[2]--;
                    }
                }
            }
        }
        return time;
    }
}
