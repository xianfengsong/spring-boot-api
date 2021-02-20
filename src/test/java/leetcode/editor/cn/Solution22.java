package leetcode.editor.cn;
//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 回溯算法 
// 👍 1565 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution22 {
    List<String> ans = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        foo(n,n,"");
        return ans;
    }
    private void foo(int l,int r,String s){
        if(l==0&&r==0){
            ans.add(s);
        }else{
            if(l>0){
                foo(l-1,r,s+"(");
            }
            if(l<r&&r>0){
                foo(l,r-1,s+")");
            }
        }
    }
    public static void main(String []args){
        System.out.println(new Solution22().generateParenthesis(3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
