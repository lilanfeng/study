package E卷.easy100;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/7 07:05
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _034_生成哈夫曼树 {
    /**
     * 哈夫曼树
     * 题目描述
     * 给定长度为 n nn 的无序的数字数组，每个数字代表二叉树的叶子节点的权值，数字数组的值均大于等于 1 11 。请完成一个函数，
     * 根据输入的数字数组，生成哈夫曼树，并将哈夫曼树按照中序遍历输出。
     * 为了保证输出的二叉树中序遍历结果统一，增加以下限制:又树节点中，左节点权值小于等于右节点权值，根节点权值为左右节点权值之和。
     * 当左右节点权值相同时，左子树高度高度小于等于右子树。
     * 注意: 所有用例保证有效，并能生成哈夫曼树提醒:哈夫曼树又称最优二叉树，是一种带权路径长度最短的一叉树。
     *
     * 所谓树的带权路径长度，就是树中所有的叶结点的权值乘上其到根结点的路径长度(若根结点为 0 00 层，叶结点到根结点的路径长度为叶结点的层数)
     *
     * 输入描述
     * 例如：由叶子节点 5 15 40 30 10 生成的最优二叉树如下图所示，该树的最短带权路径长度为 40 * 1 + 30 * 2 +5 * 4 + 10 * 4 = 205 。
     * 输出描述
     * 输出一个哈夫曼的中序遍历数组，数值间以空格分隔
     *
     * 示例1
     * 输入
     * 5
     * 5 15 40 30 10
     * 输出
     * 40 100 30 60 15 30 5 15 10
     * 解题思路
     * 模拟计算
     * 请结合上图阅读！ 计算过程如下：
     *
     * 输入的5个数是：5, 15, 40, 30, 10。
     * 将这些数作为节点值创建节点，并将节点添加到优先队列中。
     * 构建哈夫曼树：
     * 弹出两个最小的节点，值为5和10，合并为一个新节点值为15，将新节点添加回优先队列。
     * 弹出两个最小的节点，值为15（新合成的）和15（原始的），合并为一个新节点值为30，将新节点添加回优先队列。
     * 弹出两个最小的节点，值为30（新合成的）和30（原始的），合并为一个新节点值为60，将新节点添加回优先队列。
     * 弹出两个最小的节点，值为40和60，合并为一个新节点值为100，将新节点添加回优先队列。
     * 此时队列中只剩下一个节点，这就是树的根节点，值为100。
     * 对哈夫曼树进行中序遍历：
     * 访问左子树，值为40，它是一个叶子节点，输出40。
     * 访问根节点，值为100，输出100。
     * 访问右子树，值为60，它不是叶子节点，继续中序遍历：
     * 访问左子树，值为30，它不是叶子节点，继续中序遍历：
     * 访问左子树，值为15，它是一个叶子节点，输出15。
     * 访问根节点，值为30，输出30。
     * 访问右子树，值为15，它是一个叶子节点，输出15。
     * 访问根节点，值为60，输出60。
     * 访问右子树，值为30，它不是叶子节点，继续中序遍历：
     * 访问左子树，值为10，它是一个叶子节点，输出10。
     * 访问根节点，值为30，输出30。
     * 右子树为空，无输出。
     * 最终输出的结果是：40 100 15 30 60 10 30。
     * 思路
     * 小根堆（最小堆）：实现一个小根堆，用于在构建哈夫曼树的过程中维护节点的顺序，确保每次都能从中取出权值最小的节点。
     *
     * 贪心算法：构建哈夫曼树的过程本身是一个贪心算法的应用，每次选择两个权值最小的节点合并，以确保最终树的带权路径长度最短。
     *
     * DFS（深度优先搜索）：在进行中序遍历时，使用了递归方法。
     */

    public static void main(String[] args) {
        /**
         * 5
         * 5 15 40 30 10
         */
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arrNode = new int[n];
        for (int i = 0; i < n; i++) {
            arrNode[i] = scanner.nextInt();
        }
        scanner.close();
        printHuffmanTree(arrNode);

    }

    /**
     * 输出哈夫曼树
     * @param arrNode
     */
    public static void printHuffmanTree(int[] arrNode) {
        Node root = createHuffmanTree(arrNode);
        inOrder(root);
    }

    private static class Node implements Comparable<Node> {
        int value;
        Node left;
        Node right;
        int height;
        public Node(int value) {
            this.value = value;
        }
        @Override
        public int compareTo(Node node) {
            // 首先比较节点的权值，若权值相等则比较高度
            if (this.value > node.value) {
                return 1;
            }
            if (this.value < node.value) {
                return -1;
            }
            if (this.height > node.height) {
                return 1;
            }
            if (this.height < node.height) {
                return -1;
            }
            return 0;
        }
    }
    private static Node createHuffmanTree(int[] arrNode) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < arrNode.length; i++) {
            priorityQueue.offer(new Node(arrNode[i]));
        }
        while (priorityQueue.size() > 1) {
            Node leftNode = priorityQueue.poll();
            Node rightNode = priorityQueue.poll();
            Node parentNode = new Node(leftNode.value + rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            priorityQueue.offer(parentNode);
        }
        return priorityQueue.poll();

    }

    private static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.value + " ");
        inOrder(root.right);
    }
}
