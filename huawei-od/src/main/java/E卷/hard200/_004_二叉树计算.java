package E卷.hard200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/7 16:57
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _004_二叉树计算 {
    /**
     * 给出一个二叉树如下图所示：
     *
     *       6
     *   7        9
     *     -2  6
     * 请由该二叉树生成一个新的二叉树，它满足其树中的每个节点将包含原始树中的左子树和右子树的和。
     *      20
     *   -2     6
     *      0 0
     * 左子树表示该节点左侧叶子节点为根节点的一颗新树；右子树表示该节点右侧叶子节点为根节点的一颗新树。
     *
     * 输入描述
     * 2行整数，第1行表示二叉树的中序遍历，第2行表示二叉树的前序遍历，以空格分割。
     * 输出描述
     * 1行整数，表示求和树的中序遍历，以空格分割
     * 示例1
     * 输入
     * -3 12 6 8 9 -10 -7
     * 8 12 -3 6 -10 9 -7
     * 输出
     * 0 3 0 7 0 2 0
     * 说明
     * 解题思路
     * 本题主要考察二叉树的还原：根据中序和前序遍历还原。
     *
     * 请注意：根据中序和前序遍历还原，二叉树可能并不是唯一的，因为如果一个树的节点值不是唯一的，那么可能存在多个有效的二叉树。
     *
     * 在本题中，并没有说明存在多个值的处理方式，我们默认节点值是唯一的，也就是最终会还原出唯一的二叉树。
     */

    // 主方法
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // 读取一行输入，分割成字符串数组，转换为整数数组，作为中序遍历的结果
        int[] inorder = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 同样处理前序遍历的结果
        int[] preorder = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 根据中序和前序遍历的结果构造二叉树
        TreeNode root = buildTree(preorder, inorder);
        // 更新二叉树的节点值
        updateTree(root);
        // 创建列表，保存中序遍历的结果
        ArrayList<Integer> result = new ArrayList<>();
        // 中序遍历二叉树，保存结果
        inorderTraversal(root, result);
        // 打印遍历结果
        result.forEach(value -> System.out.print(value + " "));
    }


    /**
     * 定义树的节点结构
     */
    private static class TreeNode {
        int val;  // 节点的值
        TreeNode left;  // 左子节点
        TreeNode right;  // 右子节点
        TreeNode(int x) { val = x; }  // 构造方法
    }

    /**
     *  // 方法：根据中序和前序遍历构造二叉树
     *     // 参数：preorder 前序遍历的结果，inorder 中序遍历的结果
     * @param preorder
     * @param inorder
     * @return
     */

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // 调用辅助方法，传入遍历结果和对应的开始结束索引
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    /**
     * 根据中序和前序遍历的一部分构造子树
     * @param preorder 前序遍历的结果
     * @param preStart 前序遍历的开始索引
     * @param preEnd 前序遍历的结束索引
     * @param inorder 中序遍历的结果
     * @param inStart 中序遍历的开始索引
     * @param inEnd 中序遍历的结束索引
     * @return 构造好的子树根节点
     */
    private static TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        // 如果前序遍历的开始索引大于结束索引，说明这部分遍历结果为空，返回null
        if (preStart > preEnd) {
            return null;
        }

        // 创建根节点，值为前序遍历的第一个元素
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0;
        // 初始化中序遍历中根节点的索引
        // 在中序遍历中找到根节点的位置
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
                break;
            }
        }

        /**
         *  前序遍历 preorder    * -3 12 6 8 9 -10 -7
         *  中序遍历  inorder    * 8 12 -3 6 -10 9 -7
         *      -3 是根节点  左子树 有 2 8  右子树为： 6 -10 9  -7
         */
        // 计算左子树的大小
        int leftTreeSize = inIndex - inStart;

        // 递归构造左子树和右子树
        root.left = build(preorder, preStart + 1, preStart + leftTreeSize, inorder, inStart, inIndex - 1);
        root.right = build(preorder, preStart + leftTreeSize + 1, preEnd, inorder, inIndex + 1, inEnd);

        // 返回构造好的根节点
        return root;
    }

    // 方法：更新节点值为其所有子节点的和
    // 参数：node 需要更新的节点
    private static int updateTree(TreeNode node) {
        // 如果节点为空，返回0
        if (node == null) return 0;
        // 递归更新左子树和右子树，并计算子树的和
        int leftSum = updateTree(node.left);
        int rightSum = updateTree(node.right);
        // 保存当前节点的值
        int oldVal = node.val;
        // 更新当前节点的值为子树的和
        node.val = leftSum + rightSum;
        // 返回当前子树的和（包括当前节点原来的值）
        return node.val + oldVal;
    }

    // 方法：中序遍历
    // 参数：node 需要遍历的节点，result 保存遍历结果的列表
    private static void inorderTraversal(TreeNode node, ArrayList<Integer> result) {
        // 如果节点为空，直接返回
        if (node == null) return;
        // 递归遍历左子树
        inorderTraversal(node.left, result);
        // 将当前节点的值添加到结果列表
        result.add(node.val);
        // 递归遍历右子树
        inorderTraversal(node.right, result);
    }
}
