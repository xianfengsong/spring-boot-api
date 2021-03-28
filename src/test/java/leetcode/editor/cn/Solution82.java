package leetcode.editor.cn;//存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
//
// 返回同样按升序排列的结果链表。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,1,1,2,3]
//输出：[2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围 [0, 300] 内 
// -100 <= Node.val <= 100 
// 题目数据保证链表已经按升序排列 
// 
// Related Topics 链表 
// 👍 575 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import linklist.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution82 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        //重复片段的上一个节点
        ListNode prev = dummy;
        while (head != null) {
            //如果head是移动过的，那么head本身也要被删除
            boolean move = false;
            while (head.next != null && head.next.val == head.val) {
                move = true;
                head = head.next;
            }
            if (move) {
                //丢掉head
                prev.next = head.next;
            }else{
                //prev只有在非重复，或者到尾节点时才移动
                prev = prev.next;
            }
            head = head.next;
        }
        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
