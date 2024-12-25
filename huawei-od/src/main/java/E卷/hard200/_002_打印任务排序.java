package E卷.hard200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/3 15:09
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _002_打印任务排序 {
    /**
     * 题目描述
     * 某个打印机根据打印队列执行打印任务。打印任务分为九个优先级，分别用数字1-9表示，数字越大优先级越高。打印机每次从队列头部取出第一个任务A，
     * 然后检查队列余下任务中有没有比A优先级更高的任务，如果有比A优先级高的任务，则将任务A放到队列尾部，否则就执行任务A的打印。
     *
     * 请编写一个程序，根据输入的打印队列，输出实际的打印顺序。
     * 输入描述
     * 输入一行，为每个任务的优先级，优先级之间用逗号隔开，优先级取值范围是1~9。
     * 输出描述
     * 输出一行，为每个任务的打印顺序，打印顺序从0开始，用逗号隔开
     * 示例1
     * 输入
     * 9,3,5
     * 输出
     * 0,2,1
     * 说明
     * 队列头部任务的优先级为9，最先打印，故序号为0；
     * 接着队列头部任务优先级为3，队列中还有优先级为5的任务，优先级3任务被移到队列尾部；
     * 接着打印优先级为5的任务，故其序号为1；
     * 最后优先级为3的任务的序号为2。
     *
     * 示例2
     * 输入
     * 1,2,2
     * 输出
     * 2,0,1
     * 说明
     * 队列头部任务的优先级为1，被移到队列尾部；接着顺序打印两个优先级为2的任务，故其序号分别为0和1；最后打印剩下的优先级为1的任务，其序号为2
     *
     * 解题思路
     * 题目理解
     * 本题描述了一个打印机按照一定的规则处理打印任务的场景，打印任务的优先级会影响其被打印的顺序。规则如下：
     *
     * 任务优先级：任务有1到9个优先级，数字越大优先级越高。
     * 检查任务：每次从队列头部取出任务A，检查队列中是否还有比A优先级高的任务：
     * 如果有更高优先级的任务，任务A会被移到队列尾部。
     * 如果没有更高优先级的任务，任务A将被打印。
     * 任务序号：打印任务时，记录下任务的初始位置，最终输出的打印顺序为任务的初始序号顺序。
     * 具体例子分析
     * 示例1：输入 9,3,5
     * 初始任务队列：[9, 3, 5]，对应的初始序号：[0, 1, 2]
     *
     * 取出队列头部任务优先级9，队列中没有比9优先级高的任务，直接打印任务9，顺序记录为0。
     * 取出队列头部任务优先级3，队列中有优先级5的任务，优先级3的任务被移到队列尾部，队列变为：[5, 3]。
     * 取出优先级5的任务，队列中没有比5优先级高的任务，直接打印任务5，顺序记录为2。
     * 最后打印剩下的优先级3任务，顺序记录为1。
     * 最终输出为0,2,1。
     *
     * 示例2：输入 1,2,2
     * 初始任务队列：[1, 2, 2]，对应的初始序号：[0, 1, 2]
     *
     * 取出队列头部任务优先级1，队列中有两个优先级为2的任务，优先级1的任务被移到队列尾部，队列变为：[2, 2, 1]。
     * 取出队列头部任务优先级2，队列中没有比2优先级高的任务，直接打印任务2，顺序记录为1。
     * 继续取出优先级2的任务，队列中没有比2优先级高的任务，直接打印任务2，顺序记录为2。
     * 最后打印优先级1的任务，顺序记录为0。
     * 最终输出为1,2,0。
     *
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] tasks = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        //String[] tasks = {"9", "3", "5"};
        System.out.println(printTasks(tasks));
    }

    /**
     *
     * @param tasks
     * @return
     */
    public static String  printTasks(int[] tasks) {
        StringJoiner joiner = new StringJoiner(",");
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < tasks.length; i++) {
            list.add(new int[]{i,tasks[i]});
        }
        //排序 按照优先级从大到小排序
        list.sort(Comparator.comparingInt(a-> -a[1]));
        for (int i = 0; i < list.size(); i++) {
            joiner.add(String.valueOf(list.get(i)[0]));
        }
        return joiner.toString();
    }
}
