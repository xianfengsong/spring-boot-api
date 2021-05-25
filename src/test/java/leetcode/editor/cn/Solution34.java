package leetcode.editor.cn;//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 
// 👍 1014 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution34 {

    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1, -1};
        int first = searchFirst(nums, target);
        int last = searchFirst(nums,target+1);
        ans[0] = first;
        ans[1] = last;
        return ans;
    }

    public int searchFirst(int[] arr, int t) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (arr[m] == t) {
                r = m - 1;
            } else if (arr[m] > t) {
                r = m - 1;
            } else if (arr[m] < t) {
                l = m + 1;
            }
        }
        if (l >= arr.length || arr[l] != t) {
            return -1;
        }
        return l;
    }

    public int searchLast(int[] arr, int t) {
        int l = 0;
        int r = arr.length - 1;//注意越界
        while (l <= r) {
            int m = (l + r) / 2;
            if (arr[m] == t) {
                l = m + 1;
            } else if (arr[m] > t) {
                r = m - 1;
            } else if (arr[m] < t) {
                l = m + 1;
            }
        }
        // l=m+1 l=r+1 m=r
        if (r<0 || r >= arr.length || arr[r] != t) {
            return -1;
        }
        return r;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
