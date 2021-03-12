package math;

import java.util.Stack;

/**
 * lc.227 思路不对，用两个栈，一个存数字，一个存符号，没处理好倒序计算的问题
 * copy题解才完成
 */
public class CaculatorII {
    /**
     * 全部转成加法，保存到栈，遇到乘除出栈并计算
     * @param s
     * @return
     */
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
}
