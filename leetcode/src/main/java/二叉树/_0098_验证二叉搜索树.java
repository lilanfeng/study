package 二叉树;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _0098_验证二叉搜索树 {

    /**
     * https://leetcode.cn/problems/validate-binary-search-tree/description/?envType=problem-list-v2&envId=binary-tree
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * 有效 二叉搜索树定义如下：
     * 节点的左子树 只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *
     *
     * 示例 1：
     *
     *
     * 输入：root = [2,1,3]
     * 输出：true
     * 示例 2：
     *
     *  [5,4,6,null,null,3,7]
     *
     * 输入：root = [5,1,4,null,null,3,6]
     * 输出：false
     * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
     * 提示：
     *
     * 树中节点数目范围在[1, 104] 内
     * -231 <= Node.val <= 231 - 1
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        //root.left = new TreeNode(4);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        //root.right.right = new TreeNode(7);
        _0098_验证二叉搜索树 obj = new _0098_验证二叉搜索树();
        System.out.println(obj.isValidBST(root));
    }

    /**
     * 递归
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
       return isValidBST(root, Long.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);

    }


    /**
     * 中序遍历出来结果是有序的
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        //栈 先进后出
        Stack<TreeNode> stack = new Stack<>();
        long preVal = Long.MIN_VALUE;
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            //出栈
            root = stack.pop();
            if(root.val <= preVal){
                return false;
            }
            preVal = root.val;
            root = root.right;
        }
        return true;
    }

}
