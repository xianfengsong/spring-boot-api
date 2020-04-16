package binarytree;

import binarytree.attribute.IsBalance;

/**
 * Created by root on 18-4-3.
 */
public class Main {
    public static void main(String[] args) {

        /*
         1
         2 3
            4 5
         */
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        right.left = new TreeNode(4);
        right.right = new TreeNode(5);

        root.left = left;
        root.right = right;

//        LevelOrderSolution l=new LevelOrderSolution();
//        l.levelOrder(root);
//        PostOrderSolution p=new PostOrderSolution();
//        List<Integer>re=p.postorderTraversal(root);
//        System.out.println(re);
        IsBalance b = new IsBalance();
        System.out.println(b.isBalanceSolution(root));
//        InOrderSolution s=new InOrderSolution();
//        List<Integer> result=s.inorderTraversal(root);
//        System.out.println(result.toString());
    }

    public static boolean hasPathSum(TreeNode root, int sum) {

        if (root == null) {
            return false;
        }

        if (root.val == sum && root.left == null && root.right == null) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);
    }
}
