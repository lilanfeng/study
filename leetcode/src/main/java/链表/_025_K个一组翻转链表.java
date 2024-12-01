package 链表;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _025_K个一组翻转链表 {
    /**
     * https://leetcode.cn/problems/reverse-nodes-in-k-group/description/?envType=problem-list-v2&envId=linked-list
     * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     * 示例 1：
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[2,1,4,3,5]
     * 示例 2：

     * 输入：head = [1,2,3,4,5], k = 3
     * 输出：[3,2,1,4,5]

     * 提示：
     * 链表中的节点数目为 n
     * 1 <= k <= n <= 5000
     * 0 <= Node.val <= 1000
     */

    public ListNode reverseKGroup(ListNode head, int k) {

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 待翻转链表的头节点
        ListNode pre = dummy;
        // 待翻转链表的尾节点
        ListNode end = dummy;
        while (end.next != null) {

            // 找到待翻转链表的尾节点
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            ListNode start = pre.next;
            ListNode next = end.next;

            //后面未翻转的节点 跟 待翻转的节点 断开
            end.next = null;
            //前面的节点 跟 待翻转的节点 断开
            pre.next = null;

            //k个一组的链表进行反转 且拼接上前面已经翻转好的链表
            pre.next = reverseList(start);
            // 拼接上未翻转的节点
            start.next = next;
            // 更新待翻转链表的头节点到 到 待翻转链表的头节点
            pre = start;
            // 更新待翻转链表的尾节点 到 待翻转链表的头节点
            end = start;
        }

        return dummy.next;

    }

    /**
     * 翻转链表
     * @param head
     * @return
     */
    private ListNode reverseList(ListNode head) {
        if(null == head || null == head.next){
            return head;
        }
        ListNode current = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return current;
    }
}
