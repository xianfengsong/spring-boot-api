package leetcode.editor.cn;
//假设按照升序排序的数组在预先未知的某个点上进行了旋转。
//
// ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。 
//
// 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。 
//
// 示例 1: 
//
// 输入: nums = [2,5,6,0,0,1,2], target = 0
//输出: true
// 
//
// 示例 2: 
//
// 输入: nums = [2,5,6,0,0,1,2], target = 3
//输出: false 
//
// 进阶: 
//
// 
// 这是 搜索旋转排序数组 的延伸题目，本题中的 nums 可能包含重复元素。 
// 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？ 
// 
// Related Topics 数组 二分查找 
// 👍 293 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution81 {
    /**
     * 二分法，把折半条件细分成4种
     * 有重复情况，需要自己举例证明什么情况会让solution33的判断逻辑失效
     * 没有想到case
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        int i=0,j=nums.length-1;
        while (i<=j) {
            int m = (i + j) / 2;
            if(nums[m]==target){
                return true;
            }
            //[1,0,1,1,1] m和i相等，不知道哪边单调，移动i,去重，重新计算
            if(nums[i]==nums[m]){
                i++;
                continue;
            }
            if (nums[i] < nums[m]) {
                if (target >= nums[i] && target < nums[m]) {
                    j = m - 1;
                } else {
                    i = m + 1;
                }
            } else {
                if (target > nums[m] && target <= nums[j]) {
                    i = m + 1;
                } else {
                    j = m - 1;
                }
            }
        }
        return false;
    }

    public static void main(String []args){
        int [] arr= new int[]{1,0,1,1,1};
        System.out.println(new Solution81().search(arr,0));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
