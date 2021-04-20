package leetcode.editor.cn;//设计和构建一个“最近最少使用”缓存，该缓存会删除最近最少使用的项目。缓存应该从键映射到值(允许你插入和检索特定键对应的值)，并在初始化时指定最大容量。
// 当缓存被填满时，它应该删除最近最少使用的项目。
//
// 它应该支持以下操作： 获取数据 get 和 写入数据 put 。 
//
// 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。 
//写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新
//的数据值留出空间。 
//
// 示例: 
//
// LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // 返回  1
//cache.put(3, 3);    // 该操作会使得密钥 2 作废
//cache.get(2);       // 返回 -1 (未找到)
//cache.put(4, 4);    // 该操作会使得密钥 1 作废
//cache.get(1);       // 返回 -1 (未找到)
//cache.get(3);       // 返回  3
//cache.get(4);       // 返回  4
// 
// Related Topics 设计 
// 👍 83 👎 0

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {

    class Node {

        Node prev;
        Node next;
        int val;
        int key;

        public Node(int key, int val) {
            this.val = val;
            this.key = key;
        }
    }

    Node head, tail;
    int capacity, size;
    Map<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-2, -2);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node r = map.get(key);
            moveToHead(r);
            return r.val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            Node n = new Node(key, value);
            addHead(n);
            map.put(key, n);
            size++;
            if (size > capacity) {
                removeTail();
                size--;
            }
        } else {
            map.get(key).val = value;
            moveToHead(map.get(key));
        }
    }

    private void remove(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
        n = null;
    }

    private void addHead(Node n) {
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
    }

    private void removeTail() {
        Node del = tail.prev;
        tail.prev = del.prev;
        tail.prev.next = tail;
        map.remove(del.key);
        del = null;
    }

    private void moveToHead(Node n) {
        remove(n);
        addHead(n);
    }
//    public static void main(String[] args) {
//        LRUCache cache = new LRUCache(2);
//        cache.put(2, 1);
//        cache.put(2, 2);
//        Assert.assertEquals(cache.get(2),1);       // 返回  1
//        cache.put(1, 1);    // 该操作会使得密钥 2 作废
//        cache.put(4, 1);    // 该操作会使得密钥 2 作废
//        cache.get(2);       // 返回 -1 (未找到)
//        Assert.assertEquals(cache.get(1), -1);       // 返回 -1 (未找到)
//        Assert.assertEquals(cache.get(3), 3);       // 返回  3
//        Assert.assertEquals(cache.get(4), 4);       // 返回  4
//    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
