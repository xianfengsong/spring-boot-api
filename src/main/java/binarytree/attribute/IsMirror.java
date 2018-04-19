package binarytree.attribute;

import binarytree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 是不是镜像对称 值也要一样
 */
public class IsMirror{
    public boolean isMirror(TreeNode root){

        return recursive(root,root);
    }

    /**
     * 递归内容：
     * 给定两个节点对称（相等），且两个节点各自左右节点对称，返回 true
     * 递归退出条件：
     * 两个节点各自子树判断完成 或者 某个节点到达叶子节点
     * @param left
     * @param right
     * @return
     */
    private boolean recursive(TreeNode left,TreeNode right){
        //到了叶节点，不用比了
        if(left==null&&right==null){
            return true;
        }
        if(left==null||right==null){
            return false;
        }
        //都没到叶节点
        boolean isSame=left.val==right.val;
        return isSame
                &&recursive(left.left,right.right)
                    &&recursive(left.right,right.left);

    }

    Queue<TreeNode> q=new LinkedList<>();
    private boolean byQueue(TreeNode left,TreeNode right){
        q.offer(left);
        q.offer(right);
        while(!q.isEmpty()){
            //一次取一对儿
            TreeNode n1=q.poll();
            TreeNode n2=q.poll();
            if(n1==null&&n2==null) continue;
            if(n1==null||n2==null) return false;
            if(n1.val==n2.val){
                //下一层入队
                q.offer(n1.left);
                q.offer(n2.right);
                q.offer(n1.right);
                q.offer(n2.left);
            }else {
                return false;
            }
        }
        return true;
    }
}
