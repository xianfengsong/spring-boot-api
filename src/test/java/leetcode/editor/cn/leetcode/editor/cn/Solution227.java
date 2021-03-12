package leetcode.editor.cn.leetcode.editor.cn;//给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
//
// 整数除法仅保留整数部分。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：s = "3+2*2"
//输出：7
// 
//
// 示例 2： 
//
// 
//输入：s = " 3/2 "
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：s = " 3+5 / 2 "
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 3 * 105 
// s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开 
// s 表示一个 有效表达式 
// 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内 
// 题目数据保证答案是一个 32-bit 整数 
// 
// 
// 
// Related Topics 栈 字符串 
// 👍 333 👎 0

import java.util.Stack;

//基本计算器 II
//leetcode submit region begin(Prohibit modification and deletion)
class Solution227 {
    //1-1*2+3
    public int calculate(String s) {
        Stack<Integer> st = new Stack<>();
        int num = 0;
        char sig = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            //不使用else if,因为当i==s.length()-1 ,需要在这入栈
            //i==s.length()必须计算，其他情况遇到空格不计算
            if ((!Character.isDigit(c) && c!=' ')|| i == s.length() - 1) {
                switch (sig) {
                    case '+':
                        st.push(num);
                        break;
                    case '-':
                        st.push(-num);
                        break;
                    case '*':
                        st.push(st.pop() * num);
                        break;
                    case '/':
                        st.push(st.pop() / num);
                        break;
                }
                num = 0;
                sig = c;
            }
        }
        int ans = 0;
        while (!st.isEmpty()) {
            ans += st.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution227 s = new Solution227();
        s.calculate("3/2");
    }
}
//leetcode submit region end(Prohibit modification and deletion)