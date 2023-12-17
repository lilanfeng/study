package HW;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */
public class _019_删除链表的倒数第N个节点 {

    public static void main(String[] args) {

    }

    /**
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head,int n){
        ListNode pre = head;
        ListNode leftNode = head;
        int right = 1;
        while (pre.next != null){
            if(right > n){
                leftNode = leftNode.next;
            }
            pre = pre.next;
            right++;
        }
        if(right == n){
            head = head.next;
        }else if(right> n){
            leftNode.next = leftNode.next.next;
        }
        return head;
    }
}
