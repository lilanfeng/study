package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/26 21:11
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _0095_不同的二叉搜索树二 {
    /**
     * https://leetcode.cn/problems/unique-binary-search-trees-ii/description/?envType=problem-list-v2&envId=binary-tree
     * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
     * 示例 1：
     * 输入：n = 3
     * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
     * 示例 2：
     * 输入：n = 1
     * 输出：[[1]]
     * 提示：
     *
     * 1 <= n <= 8
     * 思路：递归，先序遍历，递归函数返回当前节点为根节点的所有二叉搜索树
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            int n = Integer.parseInt(scanner.nextLine());
            _0095_不同的二叉搜索树二 differentBinarySearchTrees = new _0095_不同的二叉搜索树二();
            System.out.println(differentBinarySearchTrees.generateTrees(n));
        }
        scanner.close();

    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new ArrayList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // 遍历所有可能的根节点
        for (int i = start; i <= end; i++) {
            //
            List<TreeNode> leftTreeList = generateTrees(start, i - 1);
            //
            List<TreeNode> rightTreeList = generateTrees(i + 1, end);

            // 遍历左子树集合，遍历右子树集合，拼接到根节点上
            for (TreeNode left : leftTreeList) {
                for (TreeNode right : rightTreeList) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }
}
