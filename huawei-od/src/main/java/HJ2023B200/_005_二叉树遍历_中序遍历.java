package HJ2023B200;

import java.util.LinkedList;
import java.util.Scanner;

/**
 *  题目描述
 * 根据给定的二叉树结构描述字符串，输出该二叉树按照中序遍历结果字符串。中序遍历顺序为：左子树，根结点，右子树。
 *
 * 输入描述
 * 由大小写字母、左右大括号、逗号组成的字符串:字母代表一个节点值，左右括号内包含该节点的子节点。
 *
 * 左右子节点使用逗号分隔，逗号前为空则表示左子节点为空，没有逗号则表示右子节点为空。
 *
 * 二叉树节点数最大不超过100。
 *
 * 注:输入字符串格式是正确的，无需考虑格式错误的情况。
 *
 * 输出描述
 * 输出一个字符串为二叉树中序遍历各节点值的拼接结果。
 *
 * 用例1
 * 输入
 *
 * a{b{d,e{g,h{,i}}},c{f}}
 * 1
 * 输出
 *
 * dbgehiafc
 * 1
 * 题目解析
 * 输入的字符串描述了一个二叉树的结构，其中字母代表节点的值，左右括号内包含该节点的子节点。左子节点使用逗号分隔，逗号前为空则表示左子节点为空，没有逗号则表示右子节点为空。
 *
 * 我们需要根据给定的二叉树结构描述字符串，输出该二叉树按照中序遍历结果字符串。中序遍历顺序为：左子树，根结点，右子树。
 *
 * 解题思路如下：
 *
 * 1 首先，我们需要来记录左括号的索引，以便后续使用。
 * 2 我们还需要存储节点值。我们可以将节点值存储为字符串类型。
 * 3 遍历输入的字符串，对于每个字符：
 *      如果是右括号 ‘}’，则说明一个子树的节点值已经结束。我们需要获取上一个左括号的索引，并将右括号后面的节点值拼接起来。
 *      如果是左括号 ‘{’，则说明一个子树的节点值开始。我们需要记录左括号的索引。
 *      如果是其他字符，则说明是一个节点的值。我们将节点值加入到栈中。
 * 4 最后，我们可以输出栈中的第一个元素，即为中序遍历结果。
 * ————————————————
 * 版权声明：本文为CSDN博主「算法大师」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/banxia_frontend/article/details/131949093
 */
public class _005_二叉树遍历_中序遍历 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        LinkedList<Integer> indexlist = new LinkedList<>();
        LinkedList<String> stack = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c == '}'){
                int index = indexlist.removeLast();
                StringBuilder sb = new StringBuilder();
                while (index + 1 < stack.size()){
                    sb.append(stack.remove(index +1));
                }
                String subTree = sb.toString();
                stack.removeLast();
                
            }

            if(c == '{'){
                indexlist.addLast(stack.size());
            }
            stack.addLast(c + "");
        }

        System.out.println(stack.get(0));
    }
}
