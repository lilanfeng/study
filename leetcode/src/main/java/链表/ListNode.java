package 链表;

/**
 * @author lilanfeng
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(){}
    ListNode(int val){
        this.val = val;
    }
    ListNode(int val,ListNode node) {
        this.val = val;
        this.next = node;
    }

}
