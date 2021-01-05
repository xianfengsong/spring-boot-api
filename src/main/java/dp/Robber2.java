package dp;
//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
//房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,3,2]
//输出：3
//解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。

/**
 * 打家劫舍II
 * 关键是能不能想到把 连城环的房间 分治，拆分为 0 ～ n-1的链表，和1～n的链表，首尾只能选一个
 */
public class Robber2 {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] noHead = new int[nums.length - 1];
        int[] noTail = new int[nums.length - 1];
        System.arraycopy(nums, 1, noHead, 0, nums.length - 1);
        System.arraycopy(nums, 0, noTail, 0, nums.length - 1);
        return Math.max(robSub(noHead), robSub(noTail));
    }

    public int robSub(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int first = nums[0];
        int second = Math.max(first, nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = second;
            second = Math.max(nums[i] + first, second);
            first = temp;
        }
        return second;
    }
}
