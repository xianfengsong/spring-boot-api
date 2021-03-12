package leetcode.editor.cn;
//序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
//
//      _9_
//    /   \
//   3     2
//  / \   / \
// 4   1  #  6
/// \ / \   / \
//# # # #   # #
// 
//
// 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。 
//
// 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。 
//
// 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。 
//
// 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。 
//
// 示例 1: 
//
// 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
//输出: true 
//
// 示例 2: 
//
// 输入: "1,#"
//输出: false
// 
//
// 示例 3: 
//
// 输入: "9,#,#,1"
//输出: false 
// Related Topics 栈 
// 👍 246 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution331 {

    /**
     * 60mins,看提示才想到用栈，但是思路不清晰，看题解以构造树的过程为思路，完成下面代码
     * 有错误：case= 1，#，#，1 过不了
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        Stack<String> st = new Stack<>();
        String[] arr = preorder.split(",");
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            if (!"#".equals(s)) {
                st.push(s);
            } else {
                if (i + 1 < arr.length) {
                    st.pop();
                    String next = arr[i + 1];
                    if (!"#".equals(next)) {
                        st.push(next);
                    }
                    i++;
                }
            }
        }
        return !st.isEmpty() && !"#".equals(st.peek());
    }
    /**
     * 使用递归，模拟前序遍历过程，如果正确会刚好遍历到字符结尾
     */
    public boolean isValidSerializationV1(String preorder) {
        String [] nodes = preorder.split(",");
        dfs(nodes);
        //字符串不够用，或太长都不行
        return valid && i>=nodes.length;
    }
    private int i=0;
    private boolean valid=true;
    public void dfs(String [] nodes){
        if(i<nodes.length){
            if("#".equals(nodes[i])){
                i++;
            }else{
                i++;
                dfs(nodes);
                dfs(nodes);
            }
        }else{
            //字符串不够用
            valid = false;
        }
    }

    /**
     * 统计出入度
     * 思路：对未填写#的树，每个叶子节点会有两个出度，用#填充全部叶节点后出度为0
     * 如果字符串是前序遍历，沿着字符串计算树的出度，遍历结束应该为0
     */
    public boolean isValidSerializationV2(String preorder) {
        String [] nodes = preorder.split(",");
        //从1开始，遇到root后出度为2
        int count = 1;
        for(String n:nodes){
            //未遍历完，不能为0
            if(count==0){
                return false;
            }
            if("#".equals(n)){
                count--;
                if(count<0){
                    return false;
                }
            }else{
                count++;
            }
        }
        return count==0;
    }
    public static void main(String []args){
        System.out.println("true?"+new Solution331().isValidSerialization("1,2,4,#,#,#,3,#,#"));
        System.out.println("true?"+new Solution331().isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        System.out.println("false?"+new Solution331().isValidSerialization("1,#,#,1"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
