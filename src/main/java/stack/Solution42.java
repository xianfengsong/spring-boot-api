package stack;
//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 0 <= n <= 3 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 动态规划 
// 👍 2194 👎 0


import java.util.Deque;
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * lc.42 接雨水
 * 单调栈练习，变种，从后向前，使用递减栈，每次出栈时，计算当前节点和右边最近的最高点可以接到的雨水
 */
class Solution42 {
    /**
     * 暴力法 O(n^2)
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int ans = 0;
        int len = height.length;
        int[] right = new int[len];
        int max = height[len - 1];
        //set right
        for (int i = height.length - 2; i > 0; i--) {
            if (height[i] < max) {
                right[i] = max;
            } else {
                right[i] = 0;
                max = height[i];
            }
        }
        //loop l
        int left = height[0];
        for (int i = 1; i < len; i++) {
            int min = Math.min(left, right[i]);
            ans += Math.max(0, min - height[i]);
            left = Math.max(left, height[i]);
        }
        return ans;
    }

    /**
     * 使用单调栈 O(n)
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        int ans = 0;
        Deque<Integer> st = new LinkedList<>();
        for (int i = height.length - 1; i >= 0; i--) {
            //keep order
            Integer low = null;
            while (!st.isEmpty() && height[st.peek()] < height[i]) {
                low = st.pop();
                //每次遇到底点，计算雨水
                if(!st.isEmpty()){
                    int h = Math.min(height[i], height[st.peek()]) - height[low];
                    ans += (st.peek() - i - 1) * h;
                }
            }
            st.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = {4,2,0,3,2,5};
        System.out.println(new Solution42().trap1(a));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
