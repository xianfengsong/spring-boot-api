package binarytree.traversa;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中序遍历（左中右）
 */
public class InOrderSolution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        travelsalIterator(root, result);
        return result;
    }

    /**
     * 非递归遍历
     * 先沿着左节点找到最左叶子节点
     * 每次循环都是左子树已经遍历完成（这是节点出栈时机）
     * 可以出栈，然后处理右子树
     * （此时节点看做根节点）
     * @param root
     * @param result
     */
    private void travelsalIterator(TreeNode root, List<Integer> result) {
        Stack<TreeNode> stack = new Stack<>();
        //左节点遍历完成
        putAllLeft(root,stack);
        while (!stack.isEmpty()) {
            TreeNode node=stack.pop();
            result.add(node.val);
            putAllLeft(node.right,stack);
        }
    }

    /**
     * 遍历左节点&入栈（包括本节点）
     * @param node
     * @param stack
     */
    private void putAllLeft(TreeNode node,Stack<TreeNode> stack){
        while (node!=null){
            stack.push(node);
            node=node.left;
        }
    }

    /**
     * 递归中序遍历
     *
     * @param root
     * @param result
     */
    private void travelsalRecursive(TreeNode root, List<Integer> result) {
        //异常参数检查
        if (root == null) {
            return;
        }
        //下次调用会检查
//        if (root.left != null) {
        travelsalRecursive(root.left, result);
        //重要规则： 左子树结束遍历 或者 左子树为空 保存节点的值
        result.add(root.val);
        travelsalRecursive(root.right, result);

    }
}
