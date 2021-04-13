package leetcode.editor.cn;//给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
//
// 两棵树重复是指它们具有相同的结构以及相同的结点值。 
//
// 示例 1： 
//
//         1
//       / \
//      2   3
//     /   / \
//    4   2   4
//       /
//      4
// 
//
// 下面是两个重复的子树： 
//
//       2
//     /
//    4
// 
//
// 和 
//
//     4
// 
//
// 因此，你需要以列表的形式返回上述重复子树的根结点。 
// Related Topics 树 
// 👍 256 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

//import binarytree.TreeNode;

import binarytree.TreeNode;

import java.util.*;

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

/**
 * lc.652 查找重复的子树
 * 练习二叉树的遍历
 * 使用后序遍历得到子树的结构，使用hashmap统计是否重复，是否是第一次重复
 * 注意读题，子树重复的含义是左右子树遍历结果相同
 */
class Solution652 {
    Map<String,Integer> childPath = new HashMap<>();
    List<TreeNode> ans = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        postOrder(root);
        return ans;
    }

    public String postOrder(TreeNode n) {
        if (n == null) {
            return "#";
        }
        String left = postOrder(n.left);
        String right = postOrder(n.right);
        String child = getPath(left,right,n);
        if (childPath.getOrDefault(child,0)==1) {
            ans.add(n);
        }
        childPath.put(child,childPath.getOrDefault(child,0)+1);
        return child;
    }

    private String getPath(String left, String right, TreeNode r) {
        return "L" + left + "," +
                "R" + right + "," +
                "M" + r.val;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
