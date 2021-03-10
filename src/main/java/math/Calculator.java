package math;
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

/**
 * no.224 输入字符串表达式 计算结果
 * 重点：
 * 1： 计算字符串时，使用 Character.isDigital() ;使用 int i= c - '0'; 使用 *10进位
 * 2： 考虑括号前的-和+号对结果的影响 ，比如1-(2-3)，此时括号内的符号要反转
 * 3： 使用栈来识别括号分割出的区间
 * 2： 这么简洁的思路想不到啊，使用标记为记录当前数值的正负，使用stack记录区间的正负
 */
public class Calculator {
    public int calculate(String s) {
        int ans = 0;
        //记录区间的符号
        Stack<Integer> st = new Stack<>();
        //第一个字符可能没有符号，先把区间和当前符号标记为 正
        st.push(1);
        //sig记录当前数字的符号，last负责char转int进位
        int last = 0, sig = 1;
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                //进位都想不到吗
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    last = last * 10 + (s.charAt(i) - '0');
                    i++;
                }
                ans += last * sig;
                last = 0;
            } else if (c == '+') {
                // + 号 和当前区间符号相同即可，不用反转
                sig = st.peek();
                i++;
            } else if (c == '-') {
                // - 号 区间符号 * -1
                sig = -st.peek();
                i++;
            } else if (c == '(') {
                //新区间开始记录符号
                st.push(sig);
                i++;
            } else if (c == ')') {
                //区间结束
                st.pop();
                i++;
            }else if (c == ' ') {
                i++;
            }
        }
        return ans;
    }
}
