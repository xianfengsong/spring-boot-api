package binarysearch;
//给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
//
// 设计一个算法使得这 m 个子数组各自和的最大值最小。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [7,2,5,10,8], m = 2
//输出：18
//解释：
//一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
//因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4,5], m = 2
//输出：9
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,4,4], m = 3
//输出：4
// 0~20 10 ans
// 0~9 4 ans
// 0~3 1
// 2~3 2
// 3~3 2
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// 0 <= nums[i] <= 106 
// 1 <= m <= min(50, nums.length) 
// 
// Related Topics 二分查找 动态规划 
// 👍 437 👎 0

/**
 * lc.410 把数组分割成n份，使每份的和的最大值最小
 * 实数二分
 * 问题转化：在实数空间内求最小的ans,ans就是每份和的最大值
 * 二分判定条件 如果mid可以把数组分成n分（n<=m),那么mid可以继续缩小，直到mid无法把数组分成m份
 * 是否可分的条件： 数组里小于mid的组合，数量等于或小于m （就是不能大于，小于可以，举例子体会下）
 */
class Solution410 {
    public int splitArray(int[] nums, int m) {
        int l=0,r=Integer.MAX_VALUE;
        int ans = 0;
        while (l<=r){
            int mid = (l+r)/2;
            if(splittable(mid,nums,m)){
                r = mid-1;
                ans = mid;
            }else{
                l = mid + 1;
            }
        }
        return ans;
    }
    private boolean splittable(int min,int [] nums,int m){
        int sum=0;
        int count=1;
        for(int n:nums){
            if(n>min){
                return false;
            }
            sum+=n;
            if(sum>min){
                sum=n;
                count+=1;
            }
        }
        return count<=m;
    }
    public static void main(String []args){
        Solution410 s = new Solution410();
        int [] arr={2};
        System.out.println(s.splitArray(arr,1));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
