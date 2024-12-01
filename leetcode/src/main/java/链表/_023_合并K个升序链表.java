package 链表;

import java.util.PriorityQueue;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _023_合并K个升序链表 {
    /**
     *
     * https://leetcode.cn/problems/merge-k-sorted-lists/description/?envType=problem-list-v2&envId=linked-list
     * 给你一个链表数组，每个链表都已经按升序排列。
     *
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * 示例 1：
     *
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     * 示例 2：
     *
     * 输入：lists = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：lists = [[]]
     * 输出：[]
     * 提示：
     *
     * k == lists.length
     * 0 <= k <= 10^4
     * 0 <= lists[i].length <= 500
     * -10^4 <= lists[i][j] <= 10^4
     * lists[i] 按 升序 排列
     * lists[i].length 的总和不超过 10^4
     *
     */

    /**
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     * @param
     * @return
     */
    // 思路: 使用优先队列合并
    class MinHeap implements Comparable<MinHeap> {
        int value;
        ListNode ptr;
        MinHeap(int value, ListNode ptr) {
            this.value = value;
            this.ptr = ptr;
        }

        @Override
        public int compareTo(MinHeap o) {
            return 0;
        }
    }


    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<MinHeap> queue = new PriorityQueue<>();
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(new MinHeap(node.val, node));
            }
        }
        ListNode  head = new ListNode(0);
        ListNode  cur = head;
        while (!queue.isEmpty()) {
            MinHeap minHeap = queue.poll();
            cur.next = minHeap.ptr;
            cur = cur.next;
            if (minHeap.ptr.next != null) {
                queue.offer(new MinHeap(minHeap.ptr.next.val, minHeap.ptr.next));
            }
        }
        return head.next;
    }

}
