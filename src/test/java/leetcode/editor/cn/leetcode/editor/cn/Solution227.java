package leetcode.editor.cn.leetcode.editor.cn;
//给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
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
// 👍 272 👎 0

import java.util.Stack;

//基本计算器 II
//leetcode submit region begin(Prohibit modification and deletion)
class Solution227 {
    public int calculate(String s) {
        Stack<Integer> num = new Stack<>();
        Stack<Character> signal = new Stack<>();
        num.push(0);
        signal.push('+');
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int last = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    last = last * 10 + (s.charAt(i) - '0');
                    i++;
                }
                num.push(last);
                if (signal.peek() == '*') {
                    num.push(num.pop() * num.pop());
                    signal.pop();
                } else if (signal.peek() == '/') {
                    num.pop();
                    if (last != 0) {
                        num.push(num.pop() / last);
                    }
                    signal.pop();
                } else if (i<s.length() && s.charAt(i)!=' ') {
                    signal.push(s.charAt(i));
                }
                i++;
            } else if (c == ' ') {
                i++;
            } else {
                signal.push(c);
                i++;
            }
        }
        while (num.size() != 1) {
            int b = signal.pop() == '+' ? num.pop() : -num.pop();
            int a = num.pop();
            num.push(a + b);
        }
        return num.pop();
    }
    public static void main(String []args){
       Solution227 s = new Solution227();
       s.calculate(" 3+5 / 2 ");
    }
}
//leetcode submit region end(Prohibit modification and deletion)
