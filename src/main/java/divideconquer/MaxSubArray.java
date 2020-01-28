package divideconquer;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode/
 * 最大子数组和
 * 用dp最优，这里是分治法的题解
 * 这不是二分，而是三分：
 * 最大子数组和来自 左边，右边，左右交叉三种情况
 */
public class MaxSubArray {

    /**
     * crossSum 求和时必须包含p左右两侧的元素
     *
     * @param nums
     * @param left
     * @param right
     * @param p
     * @return
     */
    public int crossSum(int[] nums, int left, int right, int p) {
        int leftSubsum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = p; i > left - 1; --i) {
            currSum += nums[i];
            leftSubsum = Math.max(leftSubsum, currSum);
        }
        int rightSubSum = Integer.MIN_VALUE;
        currSum = 0;
        //就算num[p+1]是负数，currSum也会加上nums[p+1]
        for (int i = p + 1; i < right - 1; i++) {
            currSum += nums[i];
            rightSubSum = Math.max(rightSubSum, currSum);
        }
        return leftSubsum + rightSubSum;
    }

    public int helper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int p = (left + right) / 2;
        int leftSum = helper(nums, left, p);
        int rightSum = helper(nums, p + 1, right);
        int crossSum = crossSum(nums, left, right, p);
        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    public int maxSubArray(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
}
