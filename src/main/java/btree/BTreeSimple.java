package btree;


//import org.junit.Assert;

/**
 * 一个特殊的B树实现 <br>
 * 限制条件
 * - Assumes M is even and M >= 4 <br>
 * - should b be an array of children or list (it would help with
 * casting to make it a list) <br>
 * - 叶子节点保存了完整数据，一般B树叶子节点为空
 * <p>
 * 代码来源 https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/BTree.java.html <br>
 * 关键点：<br>
 * 实现上使用了哨兵键(每个Node的entry[0]代表哨兵)，哨兵键在输出被忽略，得到正常的B树结构 <br>
 * 通过哨兵键，entry既可以保存节点关键字，又可以指向子节点<br>
 * <p>
 * 对于m阶的b树
 * ┌m/2┐ <= k <= m ； 每个节点的关键字个数为k-1,子节点个数为k<br>
 * 根结点至少有两个子女，所有的叶子结点都位于同一层<br>
 * 保存N个节点时，b数的最大高度 = log┌m/2┐((N+1)/2 )+1。<br>
 *
 * <br>
 * The {@code BTreeSimple} class represents an ordered symbol table of generic
 * key-value pairs.
 * It supports the <em>put</em>, <em>get</em>, <em>contains</em>,
 * <em>size</em>, and <em>is-empty</em> methods.
 * A symbol table implements the <em>associative array</em> abstraction:
 * when associating a value with a key that is already in the symbol table,
 * the convention is to replace the old value with the new value.
 * Unlike {@link java.util.Map}, this class uses the convention that
 * values cannot be {@code null}—setting the
 * value associated with a key to {@code null} is equivalent to deleting the key
 * from the symbol table.
 * <p>
 * This implementation uses a B-tree. It requires that
 * the key type implements the {@code Comparable} interface and calls the
 * {@code compareTo()} and method to compare two keys. It does not call either
 * {@code equals()} or {@code hashCode()}.
 * The <em>get</em>, <em>put</em>, and <em>contains</em> operations
 * each make log<sub><em>m</em></sub>(<em>n</em>) probes in the worst case,
 * where <em>n</em> is the number of key-value pairs
 * and <em>m</em> is the branching factor.
 * The <em>size</em>, and <em>is-empty</em> operations take constant time.
 * Construction takes constant time.
 * <p>
 * For additional documentation, see
 * <a href="https://algs4.cs.princeton.edu/62btree">Section 6.2</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */
public class BTreeSimple<Key extends Comparable<Key>, Value> {

    // max children per B-tree node = M-1
    // (must be even and greater than 2)
    // 4阶树 每个node的子节点不超过3，并且大于2（算上哨兵节点）
    private static final int M = 4;

    private Node root;       // root of the B-tree
    private int height;      // height of the B-tree
    private int n;           // number of key-value pairs in the B-tree

    /**
     * Initializes an empty B-tree.
     */
    public BTreeSimple() {
        root = new Node(0);
    }

    /**
     * Unit tests the {@code BTreeSimple} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        BTreeSimple<String, String> st = new BTreeSimple<String, String>();

        for (int i = 1; i < 33; i++) {
            st.put(String.valueOf(i), String.valueOf(i));
            System.out.println(st);
        }
        System.out.println("5=" + st.get("5"));

        System.out.println("size:    " + st.size());
        System.out.println("height:  " + st.height());

        System.out.println("删除---");
        for (int i = 1; i > 0; i--) {
//            System.out.println(st);
        }

    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Returns the height of this B-tree (for debugging).
     *
     * @return the height of this B-tree
     */
    public int height() {
        return height;
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     * and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        return search(root, key, height);
    }

    private Value search(Node x, Key key, int ht) {
        Entry[] children = x.children;

        // external node
        if (ht == 0) {
            for (int j = 0; j < x.m; j++) {
                if (eq(key, children[j].key)) {
                    return (Value) children[j].val;
                }
            }
        }

        // internal node
        else {
            for (int j = 0; j < x.m; j++) {
                //从j+1开始查找，因为children[0]是哨兵键
                if (j + 1 == x.m || less(key, children[j + 1].key)) {
                    return search(children[j].next, key, ht - 1);
                }
            }
        }
        return null;
    }

    public void remove(Key key) {
        if (key == null) {
            return;
        }
        Node n = delete(root, key, height);
    }

    public Node delete(Node n, Key key, int ht) {
        int index = 0;
        if (ht == 0) {
            for (int j = 0; j < n.m; j++) {
                if (eq(key, n.children[j].key)) {
                    index = j;
                    break;
                }
            }
        } else {
            //非叶子节点
            for (int j = 0; j < n.m; j++) {
                if (j + 1 == n.m || less(key, n.children[j + 1].key)) {
//                    Assert.assertEquals("非叶子节点的key应该和子节点第一个key相同？", n.children[j].key,
//                            n.children[j].next.children[0].key);
                    Node u = delete(n.children[j].next, key, ht - 1);
                    //删除后子节点关键字变化
                    if (n.children[j].key != u.children[0].key) {
                        n.children[j].key = u.children[0].key;
                    }
                }
            }
        }
        //index后面节点开始向前移动
        for (int i = index; i + 1 < n.m; i++) {
            n.children[i] = n.children[i + 1];
        }
        n.m -= 1;
        if (n.m > M / 2 - 1) {
            //正常
        } else {
            //调整
        }
        return n;
    }

    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is {@code null}, this effectively deletes the key from the symbol table.
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("argument key to put() is null");
        }
        Node u = insert(root, key, val, height);
        n++;
        if (u == null) {
            return;
        }

        // need to split root
        Node t = new Node(2);
        //复制root的哨兵键，作为新root节点的哨兵键
        t.children[0] = new Entry(root.children[0].key, null, root);
        t.children[1] = new Entry(u.children[0].key, null, u);
        root = t;
        height++;
    }

    /**
     * 插入键值对 Entry
     *
     * @param h   保存键值对的节点
     * @param key key
     * @param val value
     * @param ht  当前高度
     * @return 如果不需要root分裂返回null，反之，返回保存键值对的分裂出来的新节点
     */
    private Node insert(Node h, Key key, Value val, int ht) {
        //h中保存键值对的位置
        int j;
        Entry t = new Entry(key, val, null);

        // external node,h是叶子节点，为键值对找到合适的位置j
        if (ht == 0) {
            for (j = 0; j < h.m; j++) {
                if (less(key, h.children[j].key)) {
                    break;
                }
            }
        }

        // internal node，h是非叶子节点
        else {
            for (j = 0; j < h.m; j++) {
                //插入一个新键值对，最后肯定要创建一个叶子节点，所以利用递归去下一层寻找合适位置
                if ((j + 1 == h.m) || less(key, h.children[j + 1].key)) {
                    Node u = insert(h.children[j++].next, key, val, ht - 1);
                    if (u == null) {
                        return null;
                    }
                    //子节点发生了分裂，u是分裂出的新节点，把u的第一个关键字提到当前这层(替换t的key)
                    t.key = u.children[0].key;
                    //然后把新键值对t的next指向u，这时t.value没有意义，t是非叶子节点的entry
                    t.next = u;
                    break;
                }
            }
        }
        //如果j在children中间位置，移动j之后的元素，腾空位置
        for (int i = h.m; i > j; i--) {
            h.children[i] = h.children[i - 1];
        }

        h.children[j] = t;
        h.m++;
        //节点的关键字个数是否小于M
        if (h.m < M) {
            return null;
        } else {
            return split(h);
        }
    }

    // split node in half
    // 返回后半部分，那里保存了新键值对
    private Node split(Node h) {
        Node t = new Node(M / 2);
        h.m = M / 2;
        for (int j = 0; j < M / 2; j++) {
            t.children[j] = h.children[M / 2 + j];
        }
        return t;
    }

    /**
     * Returns a string representation of this B-tree (for debugging).
     *
     * @return a string representation of this B-tree.
     */
    public String toString() {
        return toString(root, height, "") + "\n";
    }

    private String toString(Node h, int ht, String indent) {
        StringBuilder s = new StringBuilder();
        Entry[] children = h.children;

        if (ht == 0) {
            for (int j = 0; j < h.m; j++) {
                s.append(indent + children[j].key + " " + children[j].val + "\n");
            }
        } else {
            for (int j = 0; j < h.m; j++) {
                if (j > 0) {
                    s.append(indent + "(" + children[j].key + ")\n");
                }
                s.append(toString(children[j].next, ht - 1, indent + "     "));
            }
        }
        return s.toString();
    }

    // comparison functions - make Comparable instead of Key to avoid casts
    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }

    // helper B-tree node data type
    private static final class Node {

        // 节点的关键字的个数（不算哨兵键），根据m确定children数组中有效元素的个数
        private int m;
        /**
         * children数组大小是4，但是保存的关键字从entry[1]开始，entry[0]是哨兵键
         * Node是叶子节点时，children数组中没有哨兵键，每个entry对应一个键值对
         */
        private Entry[] children = new Entry[M];

        // create a node with k children
        private Node(int k) {
            m = k;
        }
    }

    // internal nodes: only use key and next
    // external nodes: only use key and value
    private static class Entry {

        private final Object val;
        private Comparable key;
        //指向子节点
        private Node next;     // helper field to iterate over array entries

        public Entry(Comparable key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

}
