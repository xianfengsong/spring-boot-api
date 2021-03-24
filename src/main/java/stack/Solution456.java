package stack;
//给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足
//：i < j < k 和 nums[i] < nums[k] < nums[j] 。 
//
// 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。 
//
// 
//
// 进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？ 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,4]
//输出：false
//解释：序列中不存在 132 模式的子序列。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,1,4,2]
//输出：true
//解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [-1,3,2,0]
//输出：true
//解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 104 
// -109 <= nums[i] <= 109 
// 
// Related Topics 栈 
// 👍 405 👎 0


import java.util.Stack;
import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * lc.456 132模式
 * 1。贪心，n[i]<n[k]<n[j], j固定，i越小愈好，k越大越好
 * 2。用额外存储空间降低复杂度
 * 3。用treemap或单调栈来实现floor/celling的功能。
 */
class Solution456 {
    /**
     * O(N^2)比O(N^3)好一点吧，用数组记录i的最小值，然后遍历j,k
     *
     * @param nums
     * @return
     */
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int[] minLeft = new int[nums.length];
        minLeft[0] = Integer.MAX_VALUE;
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            minLeft[i] = min;
            min = Math.min(min, nums[i]);
        }
        int j = 1, k = nums.length - 1;
        while (j < k) {
            int t = j;
            while (t < k) {
                if (nums[t] > nums[k] && minLeft[t] < nums[k]) {
                    return true;
                }
                t++;
            }
            k--;
        }
        return false;
    }

    /**
     * O(nlogn)
     * 存储i，k，遍历j
     * 用数组记录j左边最小的nums[i]的值
     * 用数组记录j右边小于nums[j]的最大的nums[k]，借助treemap
     *
     * @param nums
     * @return
     */
    public boolean find132pattern_1(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        //nums[i]的最小值
        int[] minLeft = new int[nums.length];
        //小于nums[j]的最大值
        int[] rightClose = new int[nums.length];
        minLeft[0] = Integer.MAX_VALUE;
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            minLeft[i] = min;
            min = Math.min(min, nums[i]);
        }
        rightClose[nums.length - 1] = Integer.MIN_VALUE;
        TreeMap<Integer, Integer> order = new TreeMap<>();
        order.put(nums[nums.length - 1], 0);
        for (int i = nums.length - 2; i >= 0; i--) {
            //右边的k,不能等于当前的nums[i]，必须小于
            Integer floor = order.floorKey(nums[i] - 1);
            rightClose[i] = floor == null ? Integer.MIN_VALUE : floor;
            order.put(nums[i], 0);
        }
        for (int j = 1; j < nums.length - 1; j++) {
            if (nums[j] > rightClose[j] && rightClose[j] > minLeft[j]) {
                return true;
            }
        }
        return false;
    }

    /**
     * O(n) 用单调栈代替treemap,同样是存储i,k，遍历j
     * @param nums
     * @return
     */
    public boolean find132pattern_2(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        //nums[i]的最小值
        int[] minLeft = new int[nums.length - 1];
        minLeft[0] = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            minLeft[i] = min;
            min = Math.min(min, nums[i]);
        }
        // 用单调栈代替treemap,选择递减的栈（从bottom到top），
        // 这样出栈是递增的，可以找到小于nums[j]的最大值
        Stack<Integer> stack = new Stack<>();
        //记录floor:j右边，比nums[j]小的最大的nums[k]的值
        int floor = nums[nums.length - 1];
        stack.push(nums[nums.length - 1]);
        for (int j = nums.length - 2; j > 0; j--) {
            while (!stack.isEmpty() && stack.peek() < nums[j]) {
                floor = stack.pop();
            }
            stack.push(nums[j]);
            if (minLeft[j] < floor && floor < nums[j]) {
                return true;
            }
        }
        return false;
    }
    /**
     * O(n) 用单调栈代替treemap
     * 优化，不需要保存i,改成固定j,k，遍历i
     * @param nums
     * @return
     */
    public boolean find132pattern_3(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        // 用单调栈代替treemap,选择递减的栈（从bottom到top），
        // 这样出栈是递增的，可以找到小于nums[j]的最大值
        Stack<Integer> stack = new Stack<>();
        //记录k,代表nums[k]的值，比k大的保存在栈里（nums[j]）
        int k = Integer.MIN_VALUE;
        for(int i=nums.length-1;i>=0;i--){
            //已存在有nums[j]>k
            if(nums[i]<k){
                return true;
            }
            while (!stack.isEmpty()&&stack.peek()<nums[i]){
                k = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }
    public static void main(String[] args) {
        //2，1，-2
        //2， k=1 -2,2,1
        //2,
        int[] nums = {3,1,2,4,2};

        boolean result = new Solution456().find132pattern_3(nums);
        System.out.println(result);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
