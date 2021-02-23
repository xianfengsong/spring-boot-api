package leetcode.editor.cn;//给你一个链表数组，每个链表都已经按升序排列。
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
// Related Topics 堆 链表 分治算法 
// 👍 1148 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import linklist.ListNode;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

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
class Solution23 {

    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> q = new PriorityQueue<>(lists.length, Comparator.comparingInt(o -> o.val));
        for (ListNode n : lists) {
            q.offer(n);
        }
        ListNode c = new ListNode();
        ListNode ans = c;
        while (!q.isEmpty()) {
            ListNode min = q.poll();
            c.next = new ListNode(min.val);
            c = c.next;
            if (min.next != null) {
                q.offer(min.next);
            }
        }
        return ans.next;
    }
    public static void main(String []args){
        ListNode a = new ListNode(1,new ListNode(1,new ListNode(2)));
        ListNode b = new ListNode(1,new ListNode(3,new ListNode(4)));
        ListNode c = new ListNode(2,new ListNode(5));
        ListNode d = new Solution23().mergeKLists(new ListNode[]{a, b, c});
        while (d!=null){
            System.out.println(d.val);
            d=d.next;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
