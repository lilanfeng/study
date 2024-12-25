package E卷.easy100;

import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2024/12/5 15:06
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _022_计算三叉搜索树的高度 {

    /**
     * 题目描述
     * 定义构造三叉搜索树规则如下：
     * 每个节点都存有一个数，当插入一个新的数时，从根节点向下寻找，直到找到一个合适的空节点插入。查找的规则是：
     * 如果数小于节点的数减去500，则将数插入节点的左子树
     * 如果数大于节点的数加上500，则将数插入节点的右子树
     * 否则，将数插入节点的中子树
     * 给你一系列数，请按以上规则，按顺序将数插入树中，构建出一棵三叉搜索树，最后输出树的高度。
     *
     * 输入描述
     * 第一行为一个数 N，表示有 N 个数，1 ≤ N ≤ 10000
     *
     * 第二行为 N 个空格分隔的整数，每个数的范围为[1,10000]
     *
     * 输出描述
     * 输出树的高度（根节点的高度为1）
     *
     * 示例1
     * 输入
     * 5
     * 5000 2000 5000 8000 1800
     * 输出
     * 3
     * 说明
     *
     *
     * 示例2
     * 输入
     * 3
     * 5000 4000 3000
     * 输出
     * 3
     * 说明
     * 示例3
     * 输入
     * 9
     * 5000 2000 5000 8000 1800 7500 4500 1400 8100
     * 输出
     * 4
     * 说明
     *
     * 解题思路
     * 这道题目要求构造一棵 三叉搜索树，并计算这棵树的高度。所谓三叉搜索树，就是每个节点可以有最多三个子节点：左子树、中子树和右子树。每当插入一个新数时，按照一定的规则决定它应该插入到哪个子树中。
     *
     * 插入规则
     * 左子树：如果新数小于当前节点的值减去500，则新数应该插入到当前节点的左子树中。
     * 右子树：如果新数大于当前节点的值加上500，则新数应该插入到当前节点的右子树中。
     * 中子树：如果新数介于当前节点的值减去500和加上500之间（包括边界），则新数应该插入到当前节点的中子树中。
     * 示例解释
     * 示例1
     * 输入：
     * 5
     * 5000 2000 5000 8000 1800
     * 解释：
     *
     * 插入第一个数 5000，成为根节点。
     * 插入第二个数 2000，因为 2000 < 5000 - 500，所以插入到根节点的左子树中。
     * 插入第三个数 5000，因为 5000 位于根节点 5000 的中子树范围内（4500 <= 5000 <= 5500），所以插入到根节点的中子树。
     * 插入第四个数 8000，因为 8000 > 5000 + 500，所以插入到根节点的右子树中。
     * 插入第五个数 1800，因为 1800 < 2000 - 500，所以插入到 2000 的左子树中。
     * 最终构造出的树如下：
     *
     *        5000
     *        / | \
     *     2000 5000 8000
     *     /
     *   1800
     * 树的高度为 3。
     *
     * 示例2
     * 输入：
     * 3
     * 5000 4000 3000
     * 解释：
     *
     * 插入第一个数 5000，成为根节点。
     * 插入第二个数 4000，因为 4000 在中子树范围内（4500 <= 4000 <= 5500），所以插入到根节点的中子树。
     * 插入第三个数 3000，因为 3000 位于 4000 的左子树范围内（3000 < 4000 - 500），所以插入到 4000 的左子树。
     * 最终构造出的树如下：
     *        5000
     *          |
     *        4000
     *        /
     *     3000
     * 树的高度为 3。
     *
     * 示例3
     * 输入：
     *
     * 9
     * 5000 2000 5000 8000 1800 7500 4500 1400 8100
     * 解释：
     *
     * 这个例子中，多个数按规则插入，最终树的高度为 4。
     *
     * 代码方法
     * 定义树类Tree，包含两个方法：insert和getHeight。
     *
     * insert方法用于向树中插入新值。它接受当前树的根节点和要插入的值作为参数。
     *
     * 如果当前节点为空，创建一个新的TreeNode实例，并返回它作为新的根节点。
     * 如果要插入的值小于当前节点值减去500，递归地在左子树中插入该值。
     * 如果要插入的值大于当前节点值加上500，递归地在右子树中插入该值。
     * 如果要插入的值在当前节点值加减500的范围内，递归地在中间子树中插入该值。
     * 每次递归插入后，返回当前节点作为该子树的新根节点。
     * getHeight方法用于计算树的高度。它接受树的根节点作为参数。
     *
     * 如果当前节点为空，返回0，表示高度为0。
     * 否则，递归地计算左子树、中间子树和右子树的高度。
     * 取三者中的最大值，然后加1（当前节点的高度），作为整棵树的高度。
     */

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode mid;
        TreeNode(int val) {
            this.val = val;
        }
    }

    static class Tree {

        /**
         * 插入新值
         * @param root
         * @param val
         * @return
         */
        public  TreeNode insert(TreeNode root, int val) {
            if (root == null) {
                return new TreeNode(val);
            }
            if (val < root.val - 500) {
                root.left = insert(root.left, val);
            } else if (val > root.val + 500) {
                root.right = insert(root.right, val);
            } else {
                root.mid = insert(root.mid, val);
            }
            return root;
        }

        /**
         * 获取树的高度
         * @param root
         * @return
         */
        public  int getHeight(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftHeight = getHeight(root.left);
            int midHeight = getHeight(root.mid);
            int rightHeight = getHeight(root.right);
            return Math.max(leftHeight, Math.max(midHeight, rightHeight)) + 1;
        }



    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        TreeNode root = null;
        Tree tree = new Tree();
        for (int num : nums) {
            root = tree.insert(root, num);
        }
        System.out.println(tree.getHeight(root));
    }



}
