package linklist;

import org.junit.Test;

public class LinkListProblems {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /* *****************
     * 反转一个单链表。
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     * *****************/

    private ListNode newHead = new ListNode(0);

    /**
     * 我的版本
     * 因为递归函数的返回值是新链表的前驱节点，所以只能用额外的对象保存新头节点
     * @param head h
     * @return n
     */
    public ListNode reverseListV1(ListNode head) {
        next(head);

        if (head != null) {
            head.next = null;
        }
        return newHead;
    }

    private ListNode next(ListNode node) {
        if (node == null || node.next == null) {
            newHead = node;
            return node;
        }
        ListNode n = next(node.next);
        n.next = node;
        return node;
    }

    /**
     * 题解：使用递归，让返回值始终指向头节点，同时保证head节点(反转后的尾节点)的next值为null
     *
     * @param head
     * @return
     */
    public ListNode reverseListV2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //递归开始head一直向后移动
        //退出递归时，head一直在向前移动，最终指向新的尾节点
        ListNode prev = reverseListV2(head.next);
        //此时head的next节点到尾节点都已经反转，只需要在反转head.next和head
        head.next.next = head;
        //避免产生环，把head.next值为null(在下个递归，head.next会更新成head.prev)
        head.next = null;
        return prev;
    }

    /**
     * 双指针解法遍历 ：从前向后修改节点，最后的节点就是反转后链表的头节点
     * 实际有两个指针prev和cur,prev始终指向cur的前驱，和cur同时向后移动
     * prev的作用是保存cur的前驱，让cur.next=prev
     *
     * @param head
     * @return
     */
    public ListNode reverseListV3(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextTemp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nextTemp;
        }
        return prev;
    }

    @Test
    public void test() {
        ListNode h = new ListNode(1);
        h.next = new ListNode(2);
        h.next.next = new ListNode(3);
        ListNode r = reverseListV1(h);
        while (r != null) {
            System.out.println(r.val);
            r = r.next;
        }
    }
}
