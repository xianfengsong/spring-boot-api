package binarytree.traversa;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 后序遍历
 */
public class PostOrderSolution {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> re=new ArrayList<Integer>();
        if(root==null){
            return re;
        }
//        recursive(root,re);
        iterator(root,re);
        return re;
    }
    private void iterator(TreeNode root,List<Integer> re){
        Stack<TreeNode> stack=new Stack<>();
        root=putAllLeft(root,stack);
        while(!stack.isEmpty()){
            if(root.right!=null){
                root=putAllLeft(root.right,stack);
            }else{
                TreeNode center=stack.pop();
                re.add(center.val);
            }
        }
    }
    /**
     * 遍历左节点&入栈（包括本节点）
     * @param node
     * @param stack
     */
    private TreeNode putAllLeft(TreeNode node,Stack<TreeNode> stack){
        TreeNode last=node;
        while (node!=null){
            stack.push(node);
            last=node;
            node=node.left;
        }
        return last;

    }

    //递归后序遍历
    public void recursive(TreeNode root,List<Integer> re){
        if(root.left!=null){
            recursive(root.left,re);
        }
        if(root.right!=null){
            recursive(root.right,re);
        }
        re.add(root.val);
    }

}
