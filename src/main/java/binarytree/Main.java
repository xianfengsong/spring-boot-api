package binarytree;

import binarytree.traversa.InOrderSolution;
import binarytree.traversa.TreeNode;

import java.util.List;

/**
 * Created by root on 18-4-3.
 */
public class Main {
    public static void main(String []args){
        TreeNode root=new TreeNode(1);
        TreeNode right=new TreeNode(2);
//        right.left=new TreeNode(3);
        root.left=right;

        InOrderSolution s=new InOrderSolution();
        List<Integer> result=s.inorderTraversal(root);
        System.out.println(result.toString());
    }
}
