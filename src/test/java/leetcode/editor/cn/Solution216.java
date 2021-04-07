package leetcode.editor.cn;
//找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
//
// 说明： 
//
// 
// 所有数字都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: k = 3, n = 7
//输出: [[1,2,4]]
// 
//
// 示例 2: 
//
// 输入: k = 3, n = 9
//输出: [[1,2,6], [1,3,5], [2,3,4]]
// 
// Related Topics 数组 回溯算法 
// 👍 289 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 和 lc.39/lc.40类似，注意解集是否允许重复，每个解是否允许重复数字
 */
class Solution216 {
    private static int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int len = 0;

    public List<List<Integer>> combinationSum3(int k, int n) {
        len = k;
        List<List<Integer>> ans = new ArrayList<>();
        dfs(0, new ArrayList<>(), ans, n);
        return ans;
    }

    public void dfs(int i, List<Integer> path, List<List<Integer>> ans, int target) {
        if (target == 0) {
            if (path.size() == len) {
                ans.add(new ArrayList<>(path));
            }
            return;
        }
        for (; i < nums.length; i++) {
            if (nums[i] > target || path.size() > len) {
                break;
            }
            path.add(nums[i]);
            dfs(i + 1, path, ans, target - nums[i]);
            path.remove(path.size() - 1);
        }
    }

    public boolean existInRotatedArray(int[] nums, int target) {
        return search(nums,0,nums.length-1,target);
    }

    private boolean search(int[] nums, int l, int r, int target) {
        if (l > r) {
            return false;
        }
        while (l <= r) {
            int m = (l + r) / 2;

            if (nums[m] == target) {
                return true;
            }
            else {
                //[1,0,1,1,1] m和i相等，不知道哪边单调，移动i,去重，重新计算
                if(nums[l]==nums[m]){
                    l++;
                    continue;
                }
                if (nums[l] < nums[m]) {
                    if (target < nums[m] && target >= nums[l]) {
                        r = m - 1;
                    } else {
                        l = m + 1;
                    }
                } else {
                    if (target > nums[m] && target <= nums[r]) {
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //System.out.println(new Solution216().combinationSum3(3, 15));
        int [] nums = {1,2,3,0,0};
        System.out.println(new Solution216().existInRotatedArray(nums, 4));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
