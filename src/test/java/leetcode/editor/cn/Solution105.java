package leetcode.editor.cn;//根据一棵树的前序遍历与中序遍历构造二叉树。
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组 
// 👍 984 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import binarytree.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    public TreeNode build(int[] pre, int pl, int pr, int[] in, int il, int ir) {
        if (pl > pr || il > ir) {
            return null;
        }
        TreeNode r = new TreeNode(pre[pl]);
        int im = indexOf(in, r.val,il,ir);
        int llen = im - il;
        r.left = build(pre, pl + 1, pl + llen, in, il, im - 1);
        r.right = build(pre, pl + llen + 1, pr, in, im + 1, ir);
        return r;
    }

    private int indexOf(int[] arr, int t,int l,int r) {
        for (int i = l; i <=r; i++) {
            if (arr[i] == t) {
                return i;
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
