package linklist;
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

import java.util.Stack;

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
 * lc.92 链表，容易出错，要手动debug
 */
class ReverserList2 {
    /**
     * 使用栈记录要反转的部分，前后边界容易出错
     * @param head
     * @param left
     * @param right
     * @return
     */
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
                i++;
            }
            ListNode tail = cur==null?null:cur.next;
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

    /**
     * 使用完整链表反转的方法代替栈
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetweenV2(ListNode head, int left, int right) {
        ListNode cur = new ListNode();
        cur.next = head;
        ListNode dum = cur;
        int i = 0;

        if (cur.next != null) {
            while (++i < left) {
                cur = cur.next;
            }
            ListNode nHead = cur;
            ListNode p = cur.next;
            ListNode prev = null;
            while (i >= left && i <= right && p!=null) {
                ListNode n = p.next;
                p.next = prev;
                prev = p;
                p = n;
                i++;
            }
            if(nHead.next!=null){
                //2->5
                nHead.next.next = p;
                //1->4
                nHead.next = prev;
            }
        }
        return dum.next;
    }
    /**
     * 最nb的解法，双指针 + 头插法
     * 一个指向反转节点p的前一个节点g，一个指向p
     * 然后把p后面的节点提拔到p前面（头插），再把g的next指向p,然后g向后移动，p的next向后移动，循环n-m+1次
     作者：mu-yi-cheng-zhou-2
     链接：https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/java-shuang-zhi-zhen-tou-cha-fa-by-mu-yi-cheng-zho/
     **/
    public ListNode reverseBetweenV3(ListNode head, int m, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode g = dummyHead;
        ListNode p = dummyHead.next;

        int step = 0;
        while (step < m - 1) {
            g = g.next; p = p.next;
            step ++;
        }

        for (int i = 0; i < n - m; i++) {
            //先删除p.next, 所以移动的是p.next,不是p
            ListNode removed = p.next;
            //再链接p和后半部分
            p.next = p.next.next;
            //再插到p前面
            removed.next = g.next;
            //移动g
            g.next = removed;
        }

        return dummyHead.next;
    }

    public ListNode reverse(ListNode h){
        ListNode prev = null;
        ListNode c = h;
        while (c!=null){
            ListNode n = c.next;
            c.next = prev;
            prev = c;
            c = n;
        }
        return prev;
    }
    public static void main(String []args){
        ListNode n = new ListNode(1);
        ListNode t = n;
        int i=0;
        while (i<0){
            n.next = new ListNode(i+2);
            n = n.next;
            i++;
        }

        ListNode ans = new ReverserList2().reverseBetweenV2(t,1,1);
        while (ans!=null){
            System.out.println(ans.val);
            ans = ans.next;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
