package binarytree.build;

import binarytree.TreeNode;
import org.junit.Test;

//timeout

/**
 * 改进了超时的问题
 * 1. 传参时添加了 start end 避免了创建新数组
 * 2. 之前忽略了一个规则 ：在中序遍历找到root的左右子树节点后，如果左子树有x个节点
 * 后序数组的前x个节点也一定在左子树
 */
public class BuildByInOrderV2 {
    @Test
    public void test() {
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        int[] postorder = new int[]{9, 15, 7, 20, 3};
        TreeNode t = buildTree(inorder, postorder);
        System.out.println(t);
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
        //根据上面的规则，找到后序数组的起止位置
        root.left = getRoot(inOrder, inStart, index - 1, postOrder, pStart, pStart + leftSize - 1);
        root.right = getRoot(inOrder, index + 1, inEnd, postOrder, pStart + leftSize, pEnd - 1);
        return root;
    }
}
