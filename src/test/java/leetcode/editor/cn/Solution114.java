package leetcode.editor.cn;//给你二叉树的根结点 root ，请你将它展开为一个单链表：
//
// 
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。 
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？ 
// Related Topics 树 深度优先搜索 
// 👍 785 👎 0


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

/**
 * lc.114 展开二叉树
 * 1。使用栈来保存右子树，需要额外空间
 * 2。使用递归思想，划分子问题：每个节点r,只要把r.left移动到右边，然后把r.right拼接到r.left（原）的尾节点就完成的子问题
 *    然后通过递归让子问题已自底向上的顺序执行，此时r的左右子树已经展开，可以保证正确性
 * 问题：对递归问题不能快速发现 & 容易陷入递归细节
 */
class Solution114 {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        //左右子树已经展开，和左右顺序无关
        flatten(root.left);
        flatten(root.right);
        //做好本节点要做的事，展开本节点的子树（子问题）
        TreeNode l = root.left;
        TreeNode r = root.right;
        root.right = l;
        root.left = null;
        //找到尾节点
        TreeNode cur = root.right;
        while (cur.right != null) {
            cur = cur.right;
        }
        cur.right = r;
    }
    public static void main(String []args){
        System.out.println(Math.sqrt(3)-(int)Math.sqrt(3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
