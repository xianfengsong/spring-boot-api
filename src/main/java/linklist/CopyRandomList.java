package linklist;

import java.util.HashMap;
import java.util.Map;

/**
 * No.138 复制带随机指针的链表
 * 主要问题如果每次都为遍历到的节点的random节点生成拷贝，会产生重复对象，还有循环引用问题
 */
public class CopyRandomList {
    private Map<Node, Node> existMap = new HashMap<>();

    public static void main(String[] args) {
        CopyRandomList copyRandomList = new CopyRandomList();
        Node a = new Node(1);
        Node b = new Node(2);
        a.next = b;
        a.random = b;
        b.random = a;
        copyRandomList.copyRandomList_0(a);
    }

    /**
     * 第一版： 使用额外存储空间，保存已经生成的copy
     *
     * @param head
     * @return
     */
    public Node copyRandomList_0(Node head) {
        if (head == null) {
            return null;
        }
        Node nhead = new Node(head.val);
        existMap.put(head, nhead);
        Node cur = head;
        while (cur != null) {
            nhead.next = getCopy(cur.next);
            nhead.random = getCopy(cur.random);
            cur = cur.next;
            nhead = nhead.next;
        }
        return existMap.get(head);
    }

    private Node getCopy(Node n) {
        if (n != null) {
            return existMap.computeIfAbsent(n, node -> new Node(node.val));
        } else {
            return null;
        }
    }

    /**
     * 第二版：利用链表的性质，先把next的copy挂到原始链表上，然后处理random，最后再分裂出来
     *
     * @param head
     * @return
     */
    public Node copyRandomList_1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        while (cur != null) {
            Node copy = new Node(cur.val);
            copy.next = cur.next;
            cur.next = copy;
            cur = copy.next;
        }
        cur = head;
        while (cur != null && cur.next != null) {
            cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        cur = head;
        Node nhead = cur.next;
        while (cur != null && cur.next != null) {
            //cur=1
            //copy=1`
            Node copy = cur.next;
            //1->2 or 1->null
            cur.next = copy.next;
            //2 or null
            cur = cur.next;
            //1`->2`
            copy.next = cur == null ? null : cur.next;
        }
        return nhead;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}

