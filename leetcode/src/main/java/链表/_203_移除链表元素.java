package 链表;

import java.util.List;

/**
 *  给你一个链表的头节点head和一个整数val，请你删除链表中所有满足Node.val == val的节点，并返回新的头节点。
 *  示例1：
 *  输入：head = 【1，2，6，3，4，5，6】，val=6
 *  输出：【1，2，3，4，5】
 *  示例2：
 *  输入：head = 【】，val = 1
 *  输出：【】
 *  示例3：
 *  输入：head = 【7，7，7，7】，val=7
 *  输出：【】
 * 提示：
 *  列表中的节点数目在范围 [0, 104] 内
 *      1 <= Node.val <= 50
 *      0 <= val <= 50
 * @author lilanfeng
 */
public class _203_移除链表元素 {

    /**
     *  优先考虑头结点如何删除处理
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        //删除值相同的头结点后，可能新的头结点也值相等，用循环解决
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    /**
     *  创建一个虚拟头结点，不需要考虑头结点问题了
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode head,int val){
        //创建一个虚拟头结点
        ListNode dummyNode = new ListNode(val-1);
        dummyNode.next = head;

        ListNode prev = dummyNode;

        while (prev.next != null){
            if(prev.next.val == val){
                prev.next = prev.next.next;
            }else {
                prev = prev.next;
            }
        }

        return dummyNode.next;
    }

    /**
     *  递归调用进行删除处理
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements3(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        head.next = removeElements3(head.next, val);

        if (head.val == val) {
            //return removeElements3(head.next,val);
            return head.next;
        } else {
            return head;
        }
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);

        _203_移除链表元素 ll = new _203_移除链表元素();
        System.out.println(head);
        ListNode result = ll.removeElements3(head,6);
        System.out.println(result);

        ListNode head1 = new ListNode();
        System.out.println(head1);
        ListNode result1 = ll.removeElements3(head1,1);
        System.out.println(result1);


        ListNode head2 = new ListNode(7);
        head2.next = new ListNode(7);
        head2.next.next = new ListNode(7);
        head2.next.next.next = new ListNode(7);

        System.out.println(head2);
        ListNode result2 = ll.removeElements3(head2,7);
        System.out.println(result2);

        ListNode head3 = new ListNode(6);
        head3.next = new ListNode(6);
        head3.next.next = new ListNode(7);

        System.out.println(head3);
        ListNode result3 = ll.removeElements3(head3,6);
        System.out.println(result3);


    }
}
