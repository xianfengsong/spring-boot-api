package leetcode.editor.cn;//给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第
//一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。 
//
// 示例 1: 
//
// 
//输入: [1,2,1]
//输出: [2,-1,2]
//解释: 第一个 1 的下一个更大的数是 2；
//数字 2 找不到下一个更大的数； 
//第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
// 
//
// 注意: 输入数组的长度不会超过 10000。 
// Related Topics 栈 
// 👍 407 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Arrays;
import java.util.Stack;

/**
 * lc.503 下一个更大元素II
 * 单调栈练习，环形数组，遍历两遍解决
 */
class Solution503 {

    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        Stack<Integer> st = new Stack<>();
        for (int i = len * 2 - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= nums[i % len]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                ans[i % len] = st.peek();
            } else {
                ans[i % len] = -1;
            }
            st.push(nums[i % len]);
        }
        return ans;
    }

    /**
     * 优化
     * @param nums
     * @return
     */
    public int[] nextGreaterElements1(int[] nums) {
        int n = nums.length;
        int [] res = new int[n];
        //默认-1
        Arrays.fill(res, -1);
        //保存索引，不保存值
        Stack <Integer> stack = new Stack<>();
        for (int i = 0; i < n*2; i++){
            int num = nums[i % n];
            //逆向：prevSmaller 前一个小于i的值
            while(!stack.isEmpty() && num > nums[stack.peek()]){
                res[stack.pop()] = num;
            }
            //减少内存啊
            if(i < n) stack.add(i);
        }
        return res;
    }
    public static void main(String []args){
        int [] n = {1,2,1};
        System.out.println(new Solution503().nextGreaterElements(n));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
