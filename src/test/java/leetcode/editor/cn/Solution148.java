package leetcode.editor.cn;//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
//
// 进阶： 
//
// 
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 5 * 104] 内 
// -105 <= Node.val <= 105 
// 
// Related Topics 排序 链表 
// 👍 1122 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

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
 * 链表排序
 * 使用归并排序实现，注意用遍历把链表分成两部分
 */
class Solution148 {
    public static void main(String[] args) {
        int[] arr = {4, 19, 14, 5, -3, 1, 8, 5, 11, 15};
        arr = new int[]{4,2,3,1};
        ListNode head = new ListNode();
        ListNode dum = head;
        for (int i : arr) {
            ListNode n = new ListNode(i);
            head.next = n;
            head = n;
        }
        ListNode n = new Solution148().sortList(dum.next);
        while (n != null) {
            System.out.println(n.val);
            n = n.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode sortList(ListNode head) {
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        return sort(head, len);
    }

    /**
     * 使用迭代的方式减少递归栈空间使用
     * 递归是从上到下，迭代是从下到上
     * @param head
     * @return
     */
    public ListNode sortListV2(ListNode head) {
        if (head == null) {
            return null;
        }

        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            count++;
        }
        ListNode dum = new ListNode();
        dum.next = head;
        int step = 1;
        //从1开始，每次按顺序合并2个长为step的节点，step 从1，2，4，，，到超过count
        while (count > step) {
            //每次按step合并排序后，重新指向新链表的头部
            ListNode prev = dum, curr = dum.next;
            //curr是遍历链表的索引，每个循环处理2段长为step的链表，直到末尾
            while (curr != null) {
                //first block
                ListNode first = curr;
                for (int i = 1; curr.next != null && i < step; i++) {
                    curr = curr.next;
                }
                ListNode second = curr.next;
                //切断链表
                curr.next = null;
                curr = second;
                //second block
                for (int i = 1; curr != null && curr.next != null && i < step; i++) {
                    curr = curr.next;
                }
                // next two
                ListNode next = curr != null ? curr.next : null;
                if (curr != null) {
                    curr.next = null;
                }
                //构造新链表
                prev.next = merge(first, second);
                //更新prev，准备链接下一段
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
            step <<= 1;
        }
        return dum.next;
    }

    public ListNode sort(ListNode head, int len) {
        if (len == 1) {
            return head;
        }
        //注意移动距离
        int h = len / 2 - 1;
        ListNode next = head;
        while (h-- > 0) {
            next = next.next;
        }
        ListNode half = next.next;
        next.next = null;
        return merge(sort(head, len / 2), sort(half, len - len / 2));
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode t = new ListNode();
        ListNode head = t;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                t.next = l1;
                l1 = l1.next;
            } else {
                t.next = l2;
                l2 = l2.next;
            }
            t = t.next;
        }
        t.next = l1 != null ? l1 : l2;
        return head.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
