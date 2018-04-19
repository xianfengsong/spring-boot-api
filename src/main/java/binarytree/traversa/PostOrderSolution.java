package binarytree.traversa;

import binarytree.TreeNode;

import java.util.*;

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
        iteratorVisit(root,re);
        return re;
    }

    /**
     * 非递归版本一（浪费空间，保存访问记录版）
     * 左 右 中
     * (1)先把最左边这一支全部入栈
     * (2)如果栈顶元素（最左节点）有右节点，执行(3)，否则执行(4)
     * (3) 右拐，从右节点开始执行(1)
     * (4)如果没有那么出栈并保存，然后执行(2)
     *
     * 对于   1  元素4出栈后，访问2然后又会继续去访问4，导致无限循环
     *     2
     *         4
     *  所以 步骤（2）要改为如果没有右节点或者已经被访问过，那么都执行(4)
     *
     * @param root root
     * @param re 结果
     */
    private void iteratorVisit(TreeNode root,List<Integer> re){
        Stack<TreeNode> stack=new Stack<>();
        Set<TreeNode> visited=new HashSet<>();
        putAllLeft(root,stack);
        while(!stack.isEmpty()){
            TreeNode pointer=stack.peek();
            if(pointer.right==null||visited.contains(pointer)){
                re.add(pointer.val);
                stack.pop();
            }else {
                visited.add(pointer);
                putAllLeft(pointer.right,stack);
            }
        }
    }

    /**
     * 遍历左节点&入栈（包括本节点）
     * @param node
     * @param stack
     */
    private TreeNode putAllLeft(TreeNode node,Stack<TreeNode> stack){
        while (true){
            stack.push(node);
            if(node.left!=null){
                node=node.left;
            }else{
                return node;
            }
        }
    }

    /**
     * 非递归（反向遍历版）
     * 需要顺序： 左 右 中
     * 倒过来：（中 右 左）
     * 具体实现类似前序非递归遍历，调整入栈顺序
     * 优点：老子从右边开始不用额外的保存数据了！！（不需要从右边回头）
     * 在后序遍历时，先去最左边节点，实际走的路径更长了
     原始树
        1
      2   3
         4  5
     每次循环下了一层
     1
     2 3 (1)
     2 4 5 (13)
     2 4 (135)
     2(1354)
     (13542) 翻转-> (24531)

     * @param root
     * @return
     */
    public List<Integer> iterator(TreeNode root) {
        Stack<TreeNode> traversal = new Stack<TreeNode>();
        List<Integer> res = new LinkedList<Integer>();

        if(root == null)
            return res;

        traversal.push(root);
        while(!traversal.isEmpty()){
            TreeNode top = traversal.pop();
            //反向插入（头插其他元素后移）
            res.add(0, top.val);
            //和前序遍历差不多
            if(top.left != null){
                traversal.push(top.left);
            }

            if(top.right != null){
                traversal.push(top.right);
            }
        }
        return res;
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
