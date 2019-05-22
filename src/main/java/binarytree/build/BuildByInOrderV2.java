package binarytree.build;

import binarytree.TreeNode;
import org.junit.Test;

//timeout
public class BuildByInOrderV2 {
    @Test
    public void test() {
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        int[] postorder = new int[]{9, 15, 7, 20, 3};
        buildTree(inorder, postorder);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) {
            return null;
        }
        if (inorder.length != postorder.length || inorder.length == 0) {
            return null;
        }
        return getRoot(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode getRoot(int[] inOrder, int inStart, int inEnd, int[] postOrder, int pStart, int pEnd) {
        if (inEnd < inStart || pEnd < pStart) {
            return null;
        }
        int value = postOrder[pEnd];
        TreeNode root = new TreeNode(value);
        int index = inStart;
        while (inOrder[index] != value) {
            index++;
        }
        int leftSize = index - inStart;
        //????
        root.left = getRoot(inOrder, inStart, index - 1, postOrder, pStart, pStart + (index - inStart) - 1);
        root.right = getRoot(inOrder, index + 1, inEnd, postOrder, pStart + leftSize, pEnd - 1);
        return root;
    }
}
