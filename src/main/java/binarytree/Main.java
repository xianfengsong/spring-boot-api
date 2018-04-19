package binarytree;

import binarytree.traversa.InOrderSolution;
import binarytree.traversa.LevelOrderSolution;
import binarytree.traversa.PostOrderSolution;
import binarytree.traversa.TreeNode;

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
         1
         2 3 (1)
         2 4 5 (13)
         2 4 (135)
         2(1354)
         (13542)->(24531)

         */
        TreeNode root=new TreeNode(1);
        TreeNode left=new TreeNode(2);
        TreeNode right=new TreeNode(3);
        right.left=new TreeNode(4);
        right.right=new TreeNode(5);

        root.left=left;
        root.right=right;

        LevelOrderSolution l=new LevelOrderSolution();
        l.levelOrder(root);
        PostOrderSolution p=new PostOrderSolution();
        List<Integer>re=p.postorderTraversal(root);
        System.out.println(re);
//        InOrderSolution s=new InOrderSolution();
//        List<Integer> result=s.inorderTraversal(root);
//        System.out.println(result.toString());
    }
}
