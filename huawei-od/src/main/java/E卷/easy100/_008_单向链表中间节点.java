package E卷.easy100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _008_单向链表中间节点 {

    /**
     * 题目描述
     * 给定一个单链表 L，请编写程序输出 L 中间结点保存的数据。
     * 如果有两个中间结点，则输出第二个中间结点保存的数据。
     * 例如：
     * 给定 L 为 1→7→5，则输出应该为 7；
     * 给定 L 为 1→2→3→4，则输出应该为 3。
     * 输入描述
     * 每个输入包含 1 个测试用例。每个测试用例:
     * 第 1 行给出链表首结点的地址、结点总个数正整数 N (≤105)。
     * 结点的地址是 5 位非负整数，NULL 地址用 −1 表示。
     * 接下来有 N 行，每行格式为：
     * Address Data Next
     * 其中 Address 是结点地址，Data 是该结点保存的整数数据(0 ≤ Data ≤ 108)，Next 是下一结点的地址。
     *
     * 输出描述
     * 对每个测试用例，在一行中输出 L 中间结点保存的数据。
     *
     * 如果有两个中间结点，则输出第二个中间结点保存的数据。
     *
     * ( 如果奇数个节点取中间，偶数个取偏右边的那个值)
     *
     * 示例1
     * 输入
     *
     * 00010 4
     * 00000 3 -1
     * 00010 5 12309
     * 11451 6 00000
     * 12309 7 11451
     * 输出
     *
     * 6
     * 说明
     * 无
     * 示例2
     * 输入
     *
     * 10000 3
     * 76892 7 12309
     * 12309 5 -1
     * 10000 1 76892
     * 输出
     * 7
     * 说明
     *
     * 示例3
     * 输入
     *
     * 00100 4
     * 00000 4 -1
     * 00100 1 12309
     * 33218 3 00000
     * 12309 2 33218
     * 输出
     * 3
     * 说明
     *
     * 解题思路
     * 示例 1：
     *
     * 链表为：5 -> 7 -> 6 -> 3，长度为 4，偶数节点，因此中间两个节点是 7 和 6，输出第二个中间结点的值：6。
     *
     * 示例 2：
     *
     * 链表为：1 -> 7 -> 5，长度为 3，奇数节点，中间结点是 7，输出 7。
     *
     * 示例 3：
     *
     * 链表为：1 -> 2 -> 3 -> 4，长度为 4，偶数节点，中间两个节点是 2 和 3，输出第二个中间结点的值：3。
     *
     * 这道题的要求是给定一个单链表，输出它的中间结点的数据。如果链表长度是奇数，那么中间结点就是第 $\frac{n+1}{2} $ 个结点（第一个结点为第 1 个）；如果链表长度是偶数，则中间结点是第 $ \frac{n}{
     * 2} + 1 $ 个结点，也就是偏右的那个结点。
     *
     * 通过输入中的 Address、Data、Next
     * 信息，首先建立链表的结构。，使用快慢指针法，一个指针每次移动两步，另一个指针每次移动一步，当快指针到达链表末尾时，慢指针刚好位于中间节点。对于偶数长度的链表，这样的算法能自动返回偏右的那个节点。
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split(" ");
            String headAddress = split[0];
            int nodeCount = Integer.parseInt(split[1]);
            List<String> nodeList = new ArrayList<>();
            for (int i = 0; i < nodeCount; i++) {
                nodeList.add(scanner.nextLine());
            }
            System.out.println(findMiddleNode(headAddress, nodeList));
        }
    }

    /**
     * 输入：链表头结点地址，链表结点信息
     * @param headAddress
     * @param nodeList
     * @return
     */
    public static String findMiddleNode(String headAddress, List<String> nodeList) {
        HashMap<String,String[]> nodeMap = new HashMap<>();
        for (String node : nodeList) {
            String[] split = node.split(" ");
            nodeMap.put(split[0],new String[]{split[1],split[2]});
        }
        String fastNode = headAddress;
        String slowNode = headAddress;
        int count = nodeList.size();
        while (nodeMap.containsKey(fastNode) && nodeMap.containsKey(nodeMap.get(fastNode)[1])){
            slowNode = nodeMap.get(slowNode)[1];
            fastNode = nodeMap.get(fastNode)[1];
            fastNode = nodeMap.get(fastNode)[1];
        }
        if (count % 2 == 0) {
            return nodeMap.get(nodeMap.get(slowNode)[1])[0];
        } else {
            return nodeMap.get(slowNode)[0];
        }
    }
}
