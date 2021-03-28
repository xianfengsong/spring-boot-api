package stack;//实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
// 
// 
// 
// BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出
//。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。 
// boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。 
// int next()将指针向右移动，然后返回指针处的数字。 
// 
//
// 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。 
// 
// 
//
// 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。 
//
// 
//
// 示例： 
//
// 
//输入
//["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext
//", "next", "hasNext"]
//[[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
//输出
//[null, 3, 7, true, 9, true, 15, true, 20, false]
//
//解释
//BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
//bSTIterator.next();    // 返回 3
//bSTIterator.next();    // 返回 7
//bSTIterator.hasNext(); // 返回 True
//bSTIterator.next();    // 返回 9
//bSTIterator.hasNext(); // 返回 True
//bSTIterator.next();    // 返回 15
//bSTIterator.hasNext(); // 返回 True
//bSTIterator.next();    // 返回 20
//bSTIterator.hasNext(); // 返回 False
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [1, 105] 内 
// 0 <= Node.val <= 106 
// 最多调用 105 次 hasNext 和 next 操作 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以设计一个满足下述条件的解决方案吗？next() 和 hasNext() 操作均摊时间复杂度为 O(1) ，并使用 O(h) 内存。其中 h 是树的高
//度。 
// 
// Related Topics 栈 树 设计 
// 👍 419 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
 * lc.173 二叉搜索树迭代器
 * 考察二叉树中序遍历的非递归实现（使用栈）
 */
class BSTIterator {
    //超过空间复杂度要求
    private List<Integer> inOrder= new LinkedList<>();
    private Iterator<Integer> iterator;
    private Deque<TreeNode> st;
    /**
     * cur用来保存遍历中遇到的右节点
     */
    private TreeNode cur;
    public BSTIterator(TreeNode root) {
        st = new LinkedList<>();
        cur = root;
    }

    /**
     * 如果cur不为空把cur的左子树入栈
     * @return
     */
    public int next() {
        //左子树入栈
        while (cur!=null){
            st.push(cur);
            cur = cur.left;
        }
        cur = st.pop();
        int ans = cur.val;
        //指向右子树，右边还未入栈
        cur = cur.right;
        return ans;
    }

    public boolean hasNext() {
        //stack空，左边空 ，cur空，右边空
        return !st.isEmpty() || cur!=null;
    }

    private void inOrder(TreeNode r){
        if(r==null){
            return;
        }
        inOrder(r.left);
        inOrder.add(r.val);
        inOrder(r.right);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
//leetcode submit region end(Prohibit modification and deletion)
