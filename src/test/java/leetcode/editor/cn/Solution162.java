package leetcode.editor.cn;
//峰值元素是指其值大于左右相邻值的元素。
//
// 给你一个输入数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。 
//
// 你可以假设 nums[-1] = nums[n] = -∞ 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,1]
//输出：2
//解释：3 是峰值元素，你的函数应该返回其索引 2。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,1,3,5,6,4]
//输出：1 或 5 
//解释：你的函数可以返回索引 1，其峰值元素为 2；
//     或者返回索引 5， 其峰值元素为 6。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// -231 <= nums[i] <= 231 - 1 
// 对于所有有效的 i 都有 nums[i] != nums[i + 1] 
// 
//
// 
//
// 进阶：你可以实现时间复杂度为 O(logN) 的解决方案吗？ 
// Related Topics 数组 二分查找 
// 👍 381 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 有点tricky的二分case, 虽然不完全满足0~n都是单调的,但是题目限制了：
 * 1.不存在相邻的两个值相等
 * 2.左右的边界的点只需要大于一边就算峰值
 * 所以nums[n]在坐标系上是一个有波峰波谷的曲线（有多个峰值），或者单调的直线（有一个峰值）
 * 想象成爬山，在任何一点，可以向左或向右，那么只要往高走就一定能到达一个峰值（边界也是峰值）
 *
 * 因为题目的限制条件，可以即使二分法放弃一半的解空间，最后也能得到结果
 */
class Solution162 {
    public int findPeakElement(int[] nums) {
        int i=0,j=nums.length-1;
        //二分法要注意所有等号是否该使用？
        while (i<j){
            int m=(i+j)/2;
            if(nums[m]<nums[m+1]){
                i=m+1;
            }else{
                j=m;
            }
        }
        return i;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
