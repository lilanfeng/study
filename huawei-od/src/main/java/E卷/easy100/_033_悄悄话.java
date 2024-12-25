package E卷.easy100;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/6 21:17
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _033_悄悄话 {
    /**
     * 题目描述
     * 给定一个二叉树，每个节点上站一个人，节点数字表示父节点到该节点传递悄悄话需要花费的时间。
     *
     * 初始时，根节点所在位置的人有一个悄悄话想要传递给其他人，求二叉树所有节点上的人都接收到悄悄话花费的时间。
     *
     * 输入描述
     * 给定二叉树
     * 0 9 20 -1 -1 15 7 -1 -1 -1 -1 3 2
     *
     * 注：-1表示空节点
     * 输出描述
     * 返回所有节点都接收到悄悄话花费的时间
     * 38
     * 用例
     * 输入	0 9 20 -1 -1 15 7 -1 -1 -1 -1 3 2
     * 输出	38
     * 说明	无
     * 解题思路
     * 读取输入：
     * 读取一行输入，这行输入包含了一系列的整数，每个整数代表从父节点到子节点的悄悄话传递时间。
     * 处理根节点：
     * 将根节点（索引为0）加入队列，并设置其悄悄话接收时间为0。
     * 层次遍历：
     * 当队列不为空时，循环执行以下步骤：
     * 从队列中取出一个节点（包括节点索引和该节点的悄悄话接收时间）。
     * 计算左右子节点的索引。
     * 检查左右子节点是否存在（索引有效且不为-1）。
     * 更新子节点时间：
     * 如果子节点存在，将当前节点的悄悄话接收时间加上从当前节点到子节点的悄悄话传递时间，得到子节点的悄悄话接收时间。
     * 将子节点及其悄悄话接收时间加入队列。
     * 更新最大时间：
     * 每次子节点的悄悄话接收时间被计算后，更新最大时间为当前子节点时间和已记录的最大时间中的较大值。
     * 模拟计算
     * 给定的输入数组0 9 20 -1 -1 15 7 -1 -1 -1 -1 3 2代表一棵二叉树，其中每个值代表从父节点到子节点的悄悄话传递时间。数组中的-1表示没有子节点。数组索引代表节点的顺序，按照完全二叉树的顺序排列。
     *
     * 模拟计算过程如下：
     *
     * 初始化队列：
     *
     * 将根节点索引0加入队列，此时队列为[0]。
     * 开始层次遍历：
     *
     * 队列非空，继续遍历。
     * 处理根节点：
     *
     * 取出队列头部元素（根节点索引0），队列变为[]。
     * 计算左子节点索引1（2*0+1），右子节点索引2（2*0+2）。
     * 左子节点值为9，更新为0+9=9，加入队列，队列变为[1]。
     * 右子节点值为20，更新为0+20=20，加入队列，队列变为[1, 2]。
     * 更新maxTime为20。
     * 处理索引为1的节点：
     *
     * 取出队列头部元素1，队列变为[2]。
     * 计算左子节点索引3（2*1+1），右子节点索引4（2*1+2）。
     * 左右子节点值均为-1，没有子节点，不做操作。
     * 处理索引为2的节点：
     *
     * 取出队列头部元素2，队列变为[]。
     * 计算左子节点索引5（2*2+1），右子节点索引6（2*2+2）。
     * 左子节点值为15，更新为20+15=35，加入队列，队列变为[5]。
     * 右子节点值为7，更新为20+7=27，加入队列，队列变为[5, 6]。
     * 更新maxTime为35。
     * 处理索引为5的节点：
     *
     * 取出队列头部元素5，队列变为[6]。
     * 计算左子节点索引11（2*5+1），右子节点索引12（2*5+2）。
     * 左子节点值为3，更新为35+3=38，加入队列，队列变为[6, 11]。
     * 右子节点值为2，更新为35+2=37，加入队列，队列变为[6, 11, 12]。
     * 更新maxTime为38。
     * 处理索引为6的节点：
     *
     * 取出队列头部元素6，队列变为[11, 12]。
     * 计算左子节点索引13（2*6+1），右子节点索引14（2*6+2）。
     * 由于索引超出数组长度，没有子节点，不做操作。
     * 处理索引为11和12的节点：
     *
     * 取出队列头部元素11和12，队列变为[]。
     * 由于索引超出数组长度，没有子节点，不做操作。
     * 结束遍历：
     *
     * 队列为空，遍历结束。
     * 输出结果：
     *
     * 最大时间maxTime为38，这是最后一个节点接收悄悄话的时间。
     * 因此，所有节点接收悄悄话的总时间为38。
     *
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arrNode  = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getMaxTime(arrNode));


    }
    public static int getMaxTime(int[] arrNode) {
        // 顺序二叉树 遍历的数组索引 满足：i为父节点索引值， 2*i+1 左子节点 2*i+2 右子节点
        int maxTime = 0;
        Queue<Integer> nodeQueue = new LinkedList<>();
        nodeQueue.add(0);
        while (!nodeQueue.isEmpty()) {
            int nodeIndex = nodeQueue.poll();
            int leftNodeIndex = 2 * nodeIndex + 1;
            int rightNodeIndex = 2 * nodeIndex + 2;
            if (leftNodeIndex < arrNode.length && arrNode[leftNodeIndex] != -1) {
                arrNode[leftNodeIndex] += arrNode[nodeIndex];
                nodeQueue.add(leftNodeIndex);
                maxTime = Math.max(maxTime, arrNode[leftNodeIndex]);
            }
            if (rightNodeIndex < arrNode.length && arrNode[rightNodeIndex] != -1) {
                arrNode[rightNodeIndex] += arrNode[nodeIndex];
                nodeQueue.add(rightNodeIndex);
                maxTime = Math.max(maxTime, arrNode[rightNodeIndex]);
            }
        }
        return maxTime;
    }
}
