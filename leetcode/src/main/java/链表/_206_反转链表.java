package 链表;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _206_反转链表 {
    /**
     * https://leetcode.cn/problems/reverse-linked-list/description/?envType=problem-list-v2&envId=linked-list
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * 示例 1：
     * 输入：head = [1,2,3,4,5]
     * 输出：[5,4,3,2,1]
     * 示例 2：
     * 输入：head = [1,2]
     * 输出：[2,1]
     * 示例 3：
     *
     * 输入：head = []
     * 输出：[]
     * 提示：
     *
     * 链表中节点的数目范围是 [0, 5000]
     * -5000 <= Node.val <= 5000
     *
     *
     * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
     */

    /**
     * 迭代
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while(current != null){
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    /**
     * 递归
     */
    public ListNode reverseList2(ListNode head){
        if (null == head || null == head.next) {
            return head;
        }
        ListNode current = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return current;
    }
}
