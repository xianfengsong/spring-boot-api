package leetcode.editor.cn;
//给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链
//表节点，返回 反转后的链表 。
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], left = 2, right = 4
//输出：[1,4,3,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [5], left = 1, right = 1
//输出：[5]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目为 n 
// 1 <= n <= 500 
// -500 <= Node.val <= 500 
// 1 <= left <= right <= n 
// 
//
// 
//
// 进阶： 你可以使用一趟扫描完成反转吗？ 
// Related Topics 链表 
// 👍 803 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Optional;
import java.util.Stack;
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

/**
 * 03-18 20:26
 */
class Solution92 {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode cur = new ListNode();
        cur.next = head;
        ListNode dum = cur;
        int i = 0;
        Stack<ListNode> st = new Stack<>();
        if (cur.next != null) {
            while (++i < left) {
                cur = cur.next;
            }
            ListNode temp = cur;

            while (i >= left && i <= right) {
                st.push(cur.next);
                cur = cur.next;
                if(cur==null){
                    break;
                }
                i++;
            }
            ListNode tail = Optional.ofNullable(cur).map(c->c.next).orElse(null);
            while (!st.isEmpty()) {
                temp.next = st.pop();
                temp = temp.next;
            }
            if(temp!=null){
                temp.next = tail;
            }

        }
        return dum.next;
    }
    public static void main(String []args){
        ListNode n = new ListNode(1);
        ListNode t = n;
        int i=0;
        while (i<4){
            n.next = new ListNode(i+2);
            n = n.next;
            i++;
        }

        ListNode ans = new Solution92().reverseBetween(t,2,4);
        while (ans!=null){
            System.out.println(ans.val);
            ans = ans.next;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
