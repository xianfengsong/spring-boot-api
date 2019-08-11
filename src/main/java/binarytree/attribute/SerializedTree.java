package binarytree.attribute;

import binarytree.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的序列化与反序列化
 * https://leetcode-cn.com/explore/learn/card/data-structure-binary-tree/4/conclusion/20/
 */
public class SerializedTree {

    @Test
    public void testSerialized() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        System.out.println(serialize(root));
        System.out.println("V2:" + serializeV2(root));
    }

    @Test
    public void testDeserialized() {
        String data = "1,2,null,null,3,4,null,null,5,null,null,";
        Assert.assertEquals(data, serialize(deserialize(data)));
    }

    /**
     * 用深度优先遍历(前序遍历)，缺点是null比较多
     *
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder result = new StringBuilder();
        preOrderDFS(root, result);
        return result.toString();
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        List<String> queue = new ArrayList<>(Arrays.asList(data.split(",")));
        return fetch(queue);

    }

    private void preOrderDFS(TreeNode root, StringBuilder result) {
        if (root != null) {
            result.append(root.val).append(",");
            preOrderDFS(root.left, result);
            preOrderDFS(root.right, result);
        } else {
            result.append("null").append(",");
        }
    }

    private TreeNode fetch(List<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        TreeNode root = parse(queue.get(0));
        queue.remove(0);
        if (root != null) {
            root.left = fetch(queue);
            root.right = fetch(queue);
        }
        return root;
    }

    private TreeNode parse(String val) {
        if ("null".equals(val)) {
            return null;
        } else {
            return new TreeNode(Integer.valueOf(val));
        }
    }

    /**
     * 用层序遍历编码，最后的几个null可以去掉
     *
     * @param root
     * @return
     */
    public String serializeV2(TreeNode root) {
        if (root == null) {
            return "null";
        }
        StringBuilder result = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node != null) {
                    result.append(node.val).append(",");
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    result.append("null").append(",");
                }
                size--;
            }
        }
        return result.toString();
    }

    /**
     * 反序列化：用一个list用层序访问的顺序保存节点，遍历list中的节点a
     * 规律 left(a)=v[i+1]，那么 right(a)=v[i+2]
     *
     * @param data
     * @return
     */
    public TreeNode deserializeV2(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        List<TreeNode> nodes = new ArrayList<>();
        String[] values = data.split(",");
        //rootIndex保存nodes中的位置，valueIndex保存编码位置
        int nIndex = 0, vIndex = 0;
        //第一个编码得到根节点
        TreeNode root = parse(values[vIndex++]);
        //先把根节点保存到nodes
        nodes.add(root);
        //如果所有节点都被添加到nodes说明解析完成，退出
        while (nIndex < vIndex) {
            //取当前根节点
            TreeNode node = nodes.get(nIndex++);

            if (vIndex < values.length) {
                node.left = parse(values[vIndex++]);
                node.right = parse(values[vIndex++]);
            }
            //按层序访问顺序保存到nodes
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
        return root;
    }
}
