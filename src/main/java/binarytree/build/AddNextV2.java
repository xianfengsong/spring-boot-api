package binarytree.build;

import java.util.ArrayList;
import java.util.List;

/**
 * 117 : 和116的区别 不再是完全二叉树
 * 给定一个二叉树
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * <p>
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddNextV2 {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        connect(root, 0, new ArrayList<Node>());
        return root;
    }

    /**
     * 按照前序遍历递归
     * size==level 当前节点是本层第一个节点，保存到list
     * size!=level list[level]是本节点prev节点，更新next，顶掉这个位置
     *
     * @param node  node被访问节点
     * @param level 记录当前访问节点层数
     * @param list  记录各层上次访问节点
     */
    private void connect(Node node, int level, List<Node> list) {
        if (node == null) {
            return;
        }
        if (list.size() == level) {
            list.add(node);
        } else {
            Node prev = list.get(level);
            prev.next = node;
            list.set(level, node);
        }
        connect(node.left, level + 1, list);
        connect(node.right, level + 1, list);
    }

    class Node {
        int val;
        Node left;
        Node right;
        Node next;
    }
}
