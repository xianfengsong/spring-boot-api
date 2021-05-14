package string;

import java.util.Deque;
import java.util.LinkedList;
import org.junit.Assert;

/**
 * author Xianfeng <br/>
 * date 2021/5/14 下午4:03 <br/>
 * Desc:
 * lc.678 有效的括号字符串
 * 一个由若干 （ ，）和 * 组成的字符串，判断是否是合法字符串
 * 合法定义 （ 和 ） 数量相等，（ 在 ） 前出现， * 可能为 空 （ 或 ）
 */
public class ValidBrackets {

    private final static Character L = '(', R = ')', STAR = '*';
    int[][] m;

    public boolean valid(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        char[] arr = str.toCharArray();
        m = new int[arr.length + 1][arr.length + 1];
        return dfsV2(arr, 0, 0);
    }

    // ================贪心=====================

    /**
     * 没发现哪贪心了，理解无能
     * @param s
     * @return
     */
    public boolean validV3(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        char[] arr = s.toCharArray();
        // 当前为匹配的左括号可能的数量[min,max],遍历结束后0在这个区间内即可
        // 不能让min小于0（不太理解，可能是方便最后的 min == 0 判断，因为min小于0,说明0在区间中）
        int min = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            char a = arr[i];
            //维护min & max
            if (L.equals(a)) {
                min++;
                max++;
            } else if (R.equals(a)) {
                //取了剩余左括号，不能让min小于0
                min = min > 0 ? min - 1 : 0;
                max--;
            } else {
                // *变右括号，这就是贪心的策略？ 遇到*有没匹配的左括号就变右括号匹配上？
                // 不能让min小于0
                min = min > 0 ? min - 1 : 0;
                // *变左括号
                max++;
            }
            //左括号没有了，如果这里是*或（,则max不会小于0,所以此处是没有匹配上的 ）
            if (max < 0) {
                return false;
            }
        }
        return min == 0;
    }
    // ===============两次遍历================

    /**
     * 遍历两遍，正着遍历判断右括号够不够，反着遍历判断左括号够不够，左右都够就是true。至于*就是个混儿
     * 这个挺秀的
     */
    public boolean valid4(String s) {
        int left = 0;
        int right = 0;
        int all = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '*') {
                all++;
            } else if (arr[i] == '(') {
                left++;
            } else {//到这里说明是右括号，判断后面有没有对应的左括号
                if (left > 0) {
                    left--;
                } else if (all > 0) {
                    all--;
                } else {//这个右括号后面没东西了。所以false
                    return false;
                }
            }
        }
        all = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == '*') {
                all++;
            } else if (arr[i] == ')') {
                right++;
            } else {//到这里说明是左括号，判断后面有没有对应的右括号
                if (right > 0) {
                    right--;
                } else if (all > 0) {
                    all--;
                } else {//这个左括号后面没东西了。所以false
                    return false;
                }
            }
        }
        return true;
    }

    // ==============两个栈===================
    //不要用Stack, 因为peek()空栈的时候会直接异常
    private Deque<Integer> bk = new LinkedList<>();
    private Deque<Integer> st = new LinkedList<>();

    /**
     * 一开始用两个栈分别保存*和( ,但是无法区分 **(( 和 ((** 两种case
     * 因为判断是还要考虑顺序，所以用栈来保存下标
     * 最好理解的
     * O(n)
     */
    public boolean validV2(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char a = arr[i];
            if (L.equals(a)) {
                bk.push(i);
            } else if (R.equals(a)) {
                if (bk.peek() != null) {
                    bk.pop();
                } else if (st.peek() != null) {
                    st.pop();
                } else {
                    //not match
                    return false;
                }
            } else {
                st.push(i);
            }
        }
        //先比较下数量，否则超时
        if (bk.size() > st.size()) {
            return false;
        }
        //clean st bk
        while (!bk.isEmpty() && !st.isEmpty()) {
            //如果* 在 （ 前，则不可能匹配
            if (st.peek() > bk.peek()) {
                bk.pop();
                st.pop();
            } else {
                //快速返回，否则超时
                return false;
            }
        }
        return bk.isEmpty();
    }

    // ==============暴力求解=================

    /**
     * 暴力dfs,复杂度 O(3^n)
     * 超时！不行！
     */
    public boolean dfs(char[] arr, int i, int count) {

        System.out.println("dfs" + getSpan(i) + "i=" + i + ",count=" + count);
        if (count < 0) {
            return false;
        }
        if (i >= arr.length) {
            return count == 0;
        }
        if (L.equals(arr[i])) {
            return dfs(arr, i + 1, count + 1);
        } else if (R.equals(arr[i])) {
            return dfs(arr, i + 1, count - 1);
        } else if (STAR.equals(arr[i])) {
            return dfs(arr, i + 1, count) | dfs(arr, i + 1, count + 1) | dfs(arr, i, count - 1);
        } else {
            return false;
        }
    }

    /**
     * 记忆化递归 复杂度不知道
     * 超时！不行！
     */
    public boolean dfsV2(char[] arr, int i, int count) {
        if (count < 0) {
            return false;
        }
        if (m[i][count] != 0) {
            return m[i][count] == 1;
        }
        System.out.println("dfsV2" + getSpan(i) + "i=" + i + ",count=" + count);

        if (i >= arr.length) {
            if (count == 0) {
                m[i][count] = 1;
            } else {
                m[i][count] = -1;
            }
            return count == 0;
        }
        if (L.equals(arr[i])) {
            return dfsV2(arr, i + 1, count + 1);
        } else if (R.equals(arr[i])) {
            return dfsV2(arr, i + 1, count - 1);
        } else if (STAR.equals(arr[i])) {
            return dfsV2(arr, i + 1, count) | dfsV2(arr, i + 1, count + 1) | dfsV2(arr, i, count - 1);
        } else {
            return false;
        }
    }

    private String getSpan(int i) {
        StringBuilder span = new StringBuilder(" ");
        for (int j = 0; j <= i; j++) {
            span.append(" ");
        }
        return span.toString();
    }

    public static void main(String[] args) {
        ValidBrackets v = new ValidBrackets();
        String[] validSucc = {"**", "(*)", "((***", "**))"};
        String[] validFail = {")(", "*((", "(**))))", "())", "(", ""};
        for (String s : validSucc) {
            Assert.assertTrue(s + " not pass", v.valid(s));
        }
        for (String s : validFail) {
            Assert.assertFalse(s + " not pass", v.valid(s));
        }
    }
}
