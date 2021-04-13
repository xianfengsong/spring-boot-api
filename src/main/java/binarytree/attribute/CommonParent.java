package binarytree.attribute;

import binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/explore/learn/card/data-structure-binary-tree/4/conclusion/19/
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 */
public class CommonParent {
    public void t() {
        TreeNode root = new TreeNode(1);
        TreeNode l = new TreeNode(2);
        TreeNode r = new TreeNode(3);
        TreeNode ll = new TreeNode(4);
        TreeNode lr = new TreeNode(5);
        TreeNode rr = new TreeNode(6);
        r.right = rr;
        l.left = ll;
        l.right = lr;
        root.left = l;
        root.right = r;
        List<TreeNode> path = pathTo(root, rr, new ArrayList<>());
        for (TreeNode t : path) {
            System.out.println(t.val);
        }
//        System.out.print(lowestCommonAncestor(root,l,r));

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> top = pathTo(root, p, new ArrayList<>());
        List<TreeNode> toq = pathTo(root, q, new ArrayList<>());
        for (int i = top.size() - 1; i >= 0; i--) {
            if (toq.contains(top.get(i))) {
                return top.get(i);
            }
        }
        return root;
    }

    private List<TreeNode> pathTo(TreeNode root, TreeNode end, List<TreeNode> path) {
        if (root == null) {
            return path;
        }
        path.add(root);
        if (root.equals(end)) {
            return path;
        }
        List<TreeNode> lpath = pathTo(root.left, end, path);
        if (lpath.contains(end)) {
            return lpath;
        }
        List<TreeNode> rpath = pathTo(root.right, end, path);
        if (rpath.contains(end)) {
            return rpath;
        }
        //关键：没找到要删除这个节点
        path.remove(root);
        return path;
    }

}

