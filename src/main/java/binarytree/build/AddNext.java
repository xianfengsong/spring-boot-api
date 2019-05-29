package binarytree.build;

import java.util.LinkedList;


/**
 * 116. 填充每个节点的下一个右侧节点指针
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * <p>
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 */
public class AddNext {

    /**
     * 使用队列 按层次遍历的方式
     * 先把队列中的节点next赋值(不出队)，然后出队 子节点入队
     *
     * @param root
     * @return
     */
    public Node connectV1(Node root) {
        //注意检查
        if (root == null) {
            return null;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                //注意越界[0,size-1]
                if (i + 1 < size - 1) {
                    //先不要poll,会改变index
                    queue.peek().next = queue.get(i + 1);
                }
                Node n = queue.poll();
                if (n.left != null) {
                    queue.offer(n.left);
                }
                if (n.right != null) {
                    queue.offer(n.right);
                }
            }
        }
        return root;
    }

    /**
     * 递归法
     * 通过找到root.next节点
     * 执行root.right.next=root.next.left 将不同根节点的节点连起来
     */
    public Node connectV2(Node root) {
        if (root == null) return null;
        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null) {
                root.right.next = root.next.left;
            }
        }
        connectV2(root.left);
        connectV2(root.right);
        return root;
    }

    /**
     * 复制节点遍历法
     * 先创建游标current：从root开始，沿着左节点，逐层向下移动
     * 下到一层时开始遍历，在遍历前，创建游标n，横向移动
     * 这样current的位置不变，向下时current=current.left就行
     */
    public Node connectV3(Node root) {
        if (root == null) return null;
        //复制root,root还要用来返回
        Node current = root;
        while (current.left != null) {
            //只移动current的复制游标
            Node n = current;
            //横着移动，更新next指向
            while (n != null) {
                if (n.left != null) {
                    n.left.next = n.right;
                    if (n.next != null) {
                        n.right.next = n.next.left;
                    }
                }
                n = n.next;
            }
            //去下一层
            current = current.left;
        }
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
