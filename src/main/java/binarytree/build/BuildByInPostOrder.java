package binarytree.build;

import binarytree.TreeNode;

/**
 * 已知中序和后序构造二叉树
 * timeout
 */
public class BuildByInPostOrder {
    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
        if (inOrder == null || postOrder == null) {
            return null;
        }

        return getRoot(inOrder, postOrder);
    }

    private TreeNode getRoot(int[] inOrder, int[] postOrder) {
        if (inOrder.length != postOrder.length || inOrder.length == 0) {
            return null;
        } else {
            TreeNode root = new TreeNode(postOrder[postOrder.length - 1]);
            int index = getIndex(inOrder, root.val);
            int[] left = subList(inOrder, 0, index);
            int[] right = subList(inOrder, index + 1, inOrder.length);
            root.left = getRoot(left, newPostOrder(left, postOrder));
            root.right = getRoot(right, newPostOrder(right, postOrder));
            return root;
        }
    }

    //[from,end)
    private int[] subList(int[] source, int from, int end) {
        if (end < from) {
            return new int[0];
        }
        int[] sub = new int[end - from];
        for (int i = 0; from < end; from++, i++) {
            sub[i] = source[from];
        }
        return sub;
    }

    private int[] newPostOrder(int[] newInOrder, int[] postOrder) {
        int[] sub = new int[newInOrder.length];
        int j = 0;
        for (int p : postOrder) {
            if (j < newInOrder.length) {
                for (int in : newInOrder) {
                    if (in == p) {
                        sub[j++] = p;
                    }
                }
            }
        }
        return sub;
    }

    private int getIndex(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
