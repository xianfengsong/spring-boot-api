package binarytree.attribute;

import binarytree.TreeNode;

/**
 * 是不是平衡二叉树
 * 左右节点高度差不超过2，且所有子树都是平衡二叉树
 */
public class IsBalance {

    /**
     * 方案二 自顶向下 巧用全局变量(减少了getHeight或getDepth递归次数)
     * 左右子树高度求得之后 更新isBalance变量
     */
    boolean isBalance = true;

    public boolean isBalanceSolution(TreeNode root) {
        return isBalance(root) && isBalance2(root);

    }

    /**
     * 方案一 自顶向下 递归大法好
     *
     * @param node n
     * @return r
     */
    private boolean isBalance(TreeNode node) {

        if (node == null) {
            return true;
        }
        boolean thisLevel = Math.abs(getHeight(node.left) - getHeight(node.right)) < 2;
        return thisLevel && isBalance(node.left) && isBalance(node.right);
    }

    public int getHeight(TreeNode node) {
        System.out.println("#");
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private boolean isBalance2(TreeNode node) {
        getDepth(node);
        return isBalance;
    }

    /**
     * 返回子树的最大高度
     * <p>
     * 类似后序遍历 左右子树遍历完对比高度，超过2不平衡
     *
     * @param node
     * @return
     */
    public int getDepth(TreeNode node) {

        if (node == null) {
            return 0;
        }
        System.out.println("*");
        //左子树最大高度
        int leftH = getDepth(node.left);
        //右子树最大高度
        int rightH = getDepth(node.right);

        if (Math.abs(leftH - rightH) >= 2) {
            isBalance = false;
        }

        //+1 是加根节点
        // 返回子树的最大高度
        if (rightH > leftH) {
            return rightH + 1;
        } else {
            return leftH + 1;
        }

    }


}
