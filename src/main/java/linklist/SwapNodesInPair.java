package linklist;

/**
 * 成对交换链表节点
 * 需要注意把两个交换后的pair连接起来
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 */
public class SwapNodesInPair {
    /**
     * 我的版本 遇到偶数则交换
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode cur = head, prev = null;
        if (head == null || head.next == null) {
            return head;
        }
        ListNode r = head.next;
        int i = 1;
        while (cur != null) {
            if (i % 2 == 0) {
                ListNode t = cur.next;
                cur.next = prev;
                if (t != null && t.next != null) {
                    prev.next = t.next;
                } else {
                    prev.next = t;
                }
                cur = t;
            } else {
                prev = cur;
                cur = cur.next;
            }
            i++;
        }
        return r;
    }

    /**
     * 精简版,使用dummy node做头结点
     */
    public ListNode swapPairs_0(ListNode head) {
        ListNode prev = new ListNode(-1);
        ListNode temp = prev;
        prev.next = head;
        while (temp.next != null && temp.next.next != null) {
            ListNode a = temp.next;
            ListNode b = temp.next.next;
            a.next = b.next;
            b.next = a;
            temp.next = b;
            temp = a;
        }
        return prev.next;
    }

    /**
     * 递归版本 每次递归交换一对,退出条件是最多只剩一个节点
     *
     * @param head
     * @return
     */
    public ListNode swapPairs_1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode t = head.next;
        //画图可以理解1,2,3,4 先交换3,4 这样1直接指向正确的节点4
        head.next = swapPairs_1(head.next.next);
        t.next = head;
        return t;
    }

}
