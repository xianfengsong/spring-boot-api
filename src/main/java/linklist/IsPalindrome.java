package linklist;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class IsPalindrome {
    /**
     * 复制到数组中,再使用双指针检查回文
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {

        List<Integer> l = new ArrayList<>();
        while (head != null) {
            l.add(head.val);
            head = head.next;
        }

        int s = 0, e = l.size() - 1;
        while (s <= e) {
            if (!l.get(s++).equals(l.get(e--))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 不使用额外空间存储,翻转后一半,检查是否和前一半相同
     * 用快慢指针找到中点
     *
     * @param head
     * @return
     */
    public boolean isPalindrome_0(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode m = findMiddle(head);
        ListNode secondHalfStart = reverse(m.next);
        ListNode a = head;
        ListNode b = secondHalfStart;
        boolean r = true;
        while (a != null && b != null) {
            if (a.val != b.val) {
                r = false;
                break;
            }
            a = a.next;
            b = b.next;
        }
        m.next = reverse(secondHalfStart);
        return r;
    }

    private ListNode findMiddle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode t = cur.next;
            cur.next = prev;
            prev = cur;
            cur = t;
        }
        return prev;
    }
}