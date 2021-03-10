package leetcode.editor.cn;
//实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "1 + 1"
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：s = " 2-1 + 2 "
//输出：3
// 
//
// 示例 3： 
//
// 
//输入：s = "(1+(4+5+2)-3)+(6+8)"
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 3 * 105 
// s 由数字、'+'、'-'、'('、')'、和 ' ' 组成 
// s 表示一个有效的表达式 
// 
// Related Topics 栈 数学 
// 👍 456 👎 0


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution224 {


    public int calculate(String s) {
        int ans = 0;
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (!s.startsWith("-") && !s.startsWith("+") && !s.startsWith("(")) {
            s = "+" + s;
        }
        StringBuilder bd = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                int r = getRight(s, i);
                boolean plus = i == 0 || '+' == s.charAt(i - 1);
                String in = s.substring(i + 1, r);
                String out = r + 1 < s.length() ? s.substring(r + 1) : "";
                if (plus) {
                    ans = ans + calculate(out) + calculate(in);
                } else {
                    ans = ans - calculate(in) + calculate(out);
                }
            } else if ((i != 0 && (c == '+' || c == '-')) || i == s.length() - 1) {
                int val = Integer.parseInt(bd.toString().substring(1));
                if (bd.toString().charAt(0) == '-') {
                    val *= -1;
                }
                ans += val;
                bd = new StringBuilder();
                bd.append(c);
            } else {
                bd.append(c);
            }

        }
        return ans;
    }

    public int getRight(String s, int i) {
        Stack<Character> st = new Stack<>();
        st.push('(');
        i += 1;
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.push(c);
            } else if (c == ')') {
                st.pop();
                if (st.isEmpty()) {
                    return i;
                }
            }
        }
        return i;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
