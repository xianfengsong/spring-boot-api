package linklist;

import org.junit.Test;

public class LinkListProblems {
    private ListNode newHead = new ListNode(0);

    /* *****************
     * 反转一个单链表。
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     * *****************/

    /**
     * 我的版本
     * 因为递归函数的返回值是新链表的前驱节点，所以只能用额外的对象保存新头节点
     *
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

    /**
     * 反转链表中指定范围的节点
     * 问题：范围内的节点反转后，要和原来的链表重新连接
     * 思路：
     * 1. 反转链表还是使用双指针法
     * 2. 变量beforeM记录m-1位置的节点，beforeM.next=反转后的头节点
     * 变量last记录m位置的节点，反转后 last.next=n+1位置的节点
     * <p>
     * Reverse a linked list from position m to n. Do it in one-pass.
     * Note: 1 ≤ m ≤ n ≤ length of list.
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        ListNode prev = null, cur = head;
        //这个m和n的控制，不好理解
        //让cur指向位置m的节点
        while (m > 1) {
            m--;
            n--;
            prev = cur;
            cur = cur.next;
        }
        //beforeM指向位置m前一个节点，last指向位置m的节点反转后变成尾节点
        ListNode beforeM = prev, last = cur;
        //此时m=1,让n退到1，prev指向最后一个被修改的节点
        ListNode oldNext = null;
        while (n > 0) {
            oldNext = cur.next;
            cur.next = prev;
            prev = cur;
            cur = oldNext;
            n--;
        }
        //m=1,head就是最后一个被修改的节点
        if (beforeM == null) {
            head = prev;
        } else {
            beforeM.next = prev;
        }
        last.next = cur;
        return head;
    }

    //-------------------------------------------------------

    /**
     * 递归解法
     * <p>
     * 递归公式
     * l1<l2
     * result = l1[0] + merge(l1[1:],l2)
     * else
     * result = l2[0] + merge(l1,l2[1:])
     * 退出条件
     * l1==null or l2 ==null
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoListsV1(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            //递归的特性保证最后返回的l1是头节点
            l1.next = mergeTwoListsV1(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsV1(l1, l2.next);
            return l2;
        }
    }


    //-------------------------------------

    /**
     * Merge two sorted linked lists and return it as a new list.
     * The new list should be made by splicing together the nodes of the first two lists.
     * Example:
     * <p>
     * Input: 1->2->4, 1->3->4
     * Output: 1->1->2->3->4->4
     */

    /**
     * 迭代法：两个变量 一个是头节点，一个是遍历l1,l2的指针
     * 问题：如果声明变量head做返回值，一开始head是null,还要为它赋值
     * 思路：声明一个prevHead节点，初始化任意值，最后返回prevHead的next即可
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoListsV2(ListNode l1, ListNode l2) {
        ListNode prevHead = new ListNode(0);
        ListNode cur = prevHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur = l1;
                l1 = l1.next;
            } else {
                cur = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        //处理剩余节点
        cur.next = l1 == null ? l2 : l1;
        return prevHead.next;
    }

    /**
     * 删除链表的倒数第N个节点
     * <p>
     * 说明：
     * 给定的 n 保证是有效的。
     * <p>
     * 进阶：
     * 你能尝试使用一趟扫描实现吗？
     * <p>
     * 问题：
     * 1.不要用两趟遍历
     * 2.如果删除的就是head，或者链表只有一个节点会怎么样
     * <p>
     * 技巧：
     * 1.使用快慢指针，一个指向0,一个指向n+1，然后一起向后移动，当快指针到null，慢指针刚好指向倒数第N个
     * 2.使用dummy node作为head的前一个节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            second = second.next;
            first = first.next;
        }
        second.next = second.next.next;

        return dummy.next;
    }

    //----------------------------

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
