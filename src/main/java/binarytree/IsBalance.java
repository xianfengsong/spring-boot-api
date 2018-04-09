package binarytree;

import binarytree.traversa.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 是不是平衡二叉树
 */
public class IsBalance {
    boolean isBalance=true;
    public boolean isBalance2(TreeNode node){
        getDepth(node);
        return isBalance;
    }

    /**
     * 类似后序遍历 左右子树遍历完对比高度，超过2不平衡
     * @param node
     * @return
     */
    public int getDepth(TreeNode node){
        if(node==null){
            return 0;
        }
        int leftH=getDepth(node.left);
        int rightH=getDepth(node.right);
        if(Math.abs(leftH-rightH)>=2){
            isBalance=false;
        }
        //+1 是加根节点
        if(rightH>leftH){
            return rightH+1;
        }else {
            return leftH+1;
        }

    }
    public boolean isBalance(TreeNode node){
        if(node==null){
            return true;
        }
        boolean thisLevel=Math.abs(getHeight(node.left)-getHeight(node.right))<2;
        return thisLevel&&isBalance(node.left)&&isBalance(node.right);
    }
    public int getHeight(TreeNode node){
        if(node==null){
            return 0;
        }
        return 1+Math.max(getHeight(node.left),getHeight(node.right));
    }

}
