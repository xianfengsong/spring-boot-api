package binarytree;

import binarytree.attribute.IsBalance;
import binarytree.traversa.LevelOrderSolution;
import binarytree.traversa.PostOrderSolution;

import java.util.List;

/**
 * Created by root on 18-4-3.
 */
public class Main {
    public static void main(String []args){

        /*
         1
         2 3
            4 5
         */
        TreeNode root=new TreeNode(1);
        TreeNode left=new TreeNode(2);
        TreeNode right=new TreeNode(3);
        right.left=new TreeNode(4);
        right.right=new TreeNode(5);

        root.left=left;
        root.right=right;

//        LevelOrderSolution l=new LevelOrderSolution();
//        l.levelOrder(root);
//        PostOrderSolution p=new PostOrderSolution();
//        List<Integer>re=p.postorderTraversal(root);
//        System.out.println(re);
        IsBalance b=new IsBalance();
        System.out.println(b.isBalanceSolution(root));
//        InOrderSolution s=new InOrderSolution();
//        List<Integer> result=s.inorderTraversal(root);
//        System.out.println(result.toString());
    }
}
