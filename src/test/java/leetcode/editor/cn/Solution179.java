package leetcode.editor.cn;
//给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
//
// 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [10,2]
//输出："210" 
//
// 示例 2： 
//
// 
//输入：nums = [3,30,34,5,9]
//输出："9534330"
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出："1"
// 
//
// 示例 4： 
//
// 
//输入：nums = [10]
//输出："10"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 109 
// 
// Related Topics 排序 
// 👍 629 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 30min,fail
 * 思路简单，但是细节比较多，要考虑各种case
 */
class Solution179 {
    public String largestNumber(int[] nums) {
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = Integer.toString(nums[i]);
        }
        //直接比较，o2o1和o1o2的大小，因为长度相等，所有字符大小==数字大小
        Arrays.sort(arr, (o1, o2) -> {
            return (o2 + o1).compareTo(o1 + o2);
        });
        //最大的数是0，则后面也是0，那么直接返回0
        if ("0".equals(arr[0])) {
            return "0";
        }
        return String.join("", arr);
    }

    public static void main(String[] args) {
        //特殊case1
        int[] nums = {84, 845};
        //特殊case2
        int[] nums1 = {0, 0};
        System.out.println(new Solution179().largestNumber(nums));
        System.out.println(new Solution179().largestNumber(nums1));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
