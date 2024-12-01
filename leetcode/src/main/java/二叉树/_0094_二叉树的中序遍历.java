package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _0094_二叉树的中序遍历 {

    /**
     * https://leetcode.cn/problems/binary-tree-inorder-traversal/description/?envType=problem-list-v2&envId=binary-tree
     * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
     * 前序遍历： 根节点 -> 左节点 -> 右节点
     * 中序遍历： 左节点 -> 根节点 -> 右节点
     * 后序遍历： 左节点 -> 右节点 -> 根节点
     *
     * 示例 1：
     * 输入：root = [1,null,2,3]
     * 输出：[1,3,2]
     * 示例 2：
     * 输入：root = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：root = [1]
     * 输出：[1]
     *
     *
     * 提示：
     *
     * 树中节点数目在范围 [0, 100] 内
     * -100 <= Node.val <= 100
     *
     *
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     */

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(4);
        _0094_二叉树的中序遍历 obj = new _0094_二叉树的中序遍历();
        System.out.println(obj.inorderTraversal2(treeNode));
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left != null) {
            inorderTraversal(root.left, result);
        }
        result.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right, result);
        }

        return result;
    }

    public void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root.left != null) {
            inorderTraversal(root.left, result);
        }
        result.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right, result);
        }

    }


    /**
     * 迭代方法
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        // 根节点入栈
        while (root != null || !stack.isEmpty()) {
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }


}
