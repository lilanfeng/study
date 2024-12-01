package 链表;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class _092_反转链表II {

    /**
     *
     */

    /**
     *
     * @param head
     * @param left
     * @param right
     * @return
     */

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode index = new ListNode(-1);
        index.next = head;

        ListNode pre = index;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        ListNode rightNode = pre;
        for(int i = 0; i < right - left + 1; i++){
            rightNode = rightNode.next;
        }

        ListNode midNode = pre.next;
        ListNode current = rightNode.next;

        //切断左边跟中间需要反转的数据
        pre.next = null;
        //切断中间需要反转的数据跟右边
        rightNode.next = null;

        midNode = reverseLinkedList(midNode);

        pre.next = midNode;
        rightNode.next = current;

        return index.next;
    }

    /**
     * 反转链表
     * @param head
     */
    private ListNode reverseLinkedList(ListNode head){
        if(null == head || null == head.next){
            return head;
        }
        ListNode current = reverseLinkedList(head.next);
        head.next.next = head;
        head.next = null;
        return current;
    }

    /**
     * 迭代法
     */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        // 1. 创建虚拟头节点
        ListNode dummyNode = new ListNode(-1);

        // 2 虚拟头节点指向头节点
        dummyNode.next = head;

        // 3. 创建指针，指向虚拟头节点
        ListNode pre = dummyNode;

        // 4, 创建一个指针，指向头节点
        ListNode cur = head;

        // 5. 遍历链表，找到待反转链表的前一个节点
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }

        cur = pre.next;

        // 6, 开始翻转这些节点 直到 right数量的 节点
        for (int i = 0; i < right - left; i++) {
            //设置临时节点，保存当前需要翻转节点的下一个节点
            ListNode temp = cur.next;
            cur.next = cur.next.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return dummyNode.next;
    }


}
