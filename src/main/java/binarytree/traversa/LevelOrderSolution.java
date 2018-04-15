package binarytree.traversa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 层序遍历
 */
public class LevelOrderSolution {
    LinkedList<TreeNode> q=new LinkedList<>();


    //有点麻烦了，标记每层最后一个元素R(R出队即一层结束）
    // 我为什么认定了每次循环只出队一个元素呢？
    public List<List<Integer>> levelOrderMy(TreeNode root) {
        List<List<Integer>> result=new ArrayList<>();
        if(root==null){
            return result;
        }
        q.offer(root);
        TreeNode endOfLevel=root;

        List<Integer> temp=new ArrayList<>();
        while(!q.isEmpty()){
            TreeNode n=q.poll();
            temp.add(n.val);

            if(n.left!=null){
                q.offer(n.left);
            }
            if(n.right!=null) {
                q.offer(n.right);
            }

            if(endOfLevel.equals(n)){
                result.add(temp);
                temp=new ArrayList<>();
                endOfLevel=q.peekLast();
            }
        }
        return result;
    }
    //正解 标记每层元素个数N即可 出队N次即一层
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result=new ArrayList<>();
        if(root==null){
            return result;
        }
        q.offer(root);
        //每次循环就是一层
        while(!q.isEmpty()){
            List<Integer> temp=new ArrayList<>();
            //关键，在出队前保存当前队列元素个数（这一层的元素数）
            int currentNodes=q.size();
            for(int i=0;i<currentNodes;i++){
                TreeNode n=q.poll();
                temp.add(n.val);
                if(n.left!=null){
                    q.offer(n.left);
                }
                if(n.right!=null) {
                    q.offer(n.right);
                }
            }
            result.add(temp);
        }
        return result;
    }

}
