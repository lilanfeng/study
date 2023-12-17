package HW;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 *
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * 示例 2：
 *
 *
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 */
public class _061_旋转链表 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()){
            int[] nodeDataArr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int k = Integer.parseInt(sc.nextLine());
            ListNode head = new ListNode(nodeDataArr[0]);
            ListNode temp = head;
            for (int i = 1; i < nodeDataArr.length; i++) {
                ListNode tempNode = new ListNode(nodeDataArr[i]);
                temp.next = tempNode;
                temp = temp.next;
            }

            ListNode resultNode = rotateRight(head,k);

            while (resultNode.next != null){

                System.out.print(resultNode.val+" ");
                resultNode = resultNode.next;
            }
        }

    }


    public static ListNode rotateRight(ListNode head,int k){
        if(k == 0 || head == null || head.next == null){
            return head;
        }
        int length = 1;
        ListNode temp = head;
        while (temp.next != null){
            temp = temp.next;
            length++;
        }

        int add = length - k % length;
        if(add == length){
            return head;
        }

        //头和尾部连在一起处理
        temp.next = head;

        while (add-- >  0){
            temp = temp.next;
        }

        ListNode ret = temp.next;
        temp.next = null;
        return ret;

    }
}
