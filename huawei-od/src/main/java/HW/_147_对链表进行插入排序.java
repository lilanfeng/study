package HW;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * https://leetcode.cn/problems/insertion-sort-list/
 * 给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头 。
 *
 * 插入排序 算法的步骤:
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * 下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。
 *
 * 对链表进行插入排序。
 *
 * 示例 1：
 *
 * 输入: head = [4,2,1,3]
 * 输出: [1,2,3,4]
 * 示例 2：
 *
 * 输入: head = [-1,5,3,4,0]
 * 输出: [-1,0,3,4,5]
 *
 * 提示：
 *
 * 列表中的节点数在 [1, 5000]范围内
 * -5000 <= Node.val <= 5000
 */
public class _147_对链表进行插入排序 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            int[] arr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ListNode head = new ListNode(arr[0]);
            ListNode temp = head;
            for (int i = 1; i < arr.length; i++) {
                temp.next = new ListNode(arr[i]);
                temp = temp.next;
            }
            System.out.println(insertionSortList(head));
        }

    }

    public static ListNode insertionSortList(ListNode head){

        if(head == null){
            return head;
        }
        ListNode sortedNode= new ListNode(0);
        sortedNode.next = head;
        ListNode current = head.next;
        ListNode lastSorted = sortedNode;
        while (current != null){
            if(lastSorted.val <= current.val){
                lastSorted = lastSorted.next;
            }else {
                ListNode pre = sortedNode;
                while (pre.next.val <= current.val){
                    pre = pre.next;
                }
                lastSorted.next = current.next;
                current.next = pre.next;
                pre.next = current;
            }
            current = lastSorted.next;
        }

        return sortedNode.next;
    }
}
