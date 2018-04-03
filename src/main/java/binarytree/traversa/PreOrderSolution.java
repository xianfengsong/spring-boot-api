package binarytree.traversa;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 * 二叉树前序遍历
 * @author root
 */
public class PreOrderSolution {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
//        travelsalRecursive(root,result);
        trvelsalIterator(root, result);
        return result;
    }

    /**
     * 非递归遍历
     * 出栈顺序即遍历顺序
     * 每次循环都是开始向下一层，（这是节点出栈时机）
     * @param root
     * @param result
     */
    private void trvelsalIterator(TreeNode root,List<Integer> result){
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if(node!=null){
                result.add(node.val);
                //可能为null下次循环检查
                stack.push(node.right);
                stack.push(node.left);
            }
        }
    }

    /**
     * 递归遍历 调用顺序和逻辑上顺序一致
     * @param root
     * @param result
     */
    private void travelsalRecursive(TreeNode root,List<Integer> result){
        if(root==null){
            return;
        }
        result.add(root.val);
        travelsalRecursive(root.left,result);
        travelsalRecursive(root.right,result);
    }
}
