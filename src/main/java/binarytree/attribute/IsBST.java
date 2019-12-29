package binarytree.attribute;

import binarytree.TreeNode;

/**
 * 验证是否是 二叉搜索树
 * 注意：
 * 只保证r.left<r && r.right>r 不能证明是二叉搜索树
 * 还要保证 r左子树所有节点都小于r,
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 */
public class IsBST {
    /**
     * 中序遍历版本
     * 中序遍历时，每个节点都大于上一个节点
     *
     * @param root
     * @return
     */
    Integer pre = null;

    /**
     * 错误版本，没考虑左右子树
     */
    public boolean wrongVersion(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.left.val >= root.val) {
            return false;
        }
        if (root.right != null && root.right.val <= root.val) {
            return false;
        }
        return wrongVersion(root.left) && wrongVersion(root.right);
    }

    /**
     * 递归版本，判断时添加上下界
     *
     * @param n   节点
     * @param min 下界
     * @param max 上界
     * @return
     */
    public boolean check(TreeNode n, Integer min, Integer max) {
        if (n == null) {
            return true;
        }
        int val = n.val;
        if (min != null && min >= val) return false;
        if (max != null && max <= val) return false;

        return check(n.left, min, val) && check(n.right, val, max);
    }
    //-------------

    public boolean isValidBST(TreeNode root) {
        return check(root, null, null);
    }

    public boolean isValidBSTV2(TreeNode root) {
        if (root == null) return true;
        if (!isValidBSTV2(root.left)) return false;
        if (pre != null && root.val <= pre) return false;
        pre = root.val;
        return isValidBSTV2(root.right);

    }
}
