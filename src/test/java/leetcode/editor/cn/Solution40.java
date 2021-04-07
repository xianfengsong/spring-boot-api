package leetcode.editor.cn;
//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 说明： 
//
// 
// 所有数字（包括目标数）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: candidates = [10,1,2,7,6,1,5], target = 8,
//所求解集为:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//]
// 
//
// 示例 2: 
//
// 输入: candidates = [2,5,2,1,2], target = 5,
//所求解集为:
//[
//  [1,2,2],
//  [5]
//] 
// Related Topics 数组 回溯算法 
// 👍 551 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * lc.40 组合总和II
 * 注意结果集要去重
 */
class Solution40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        //每个组合不考虑顺序的不同（比如122，212是同一个组合），所以先排序，减少重复结果
        Arrays.sort(candidates);
        dfs(0, candidates, target, new ArrayList<>(), ans);
        return ans;
    }

    private void dfs(int i, int[] nums, int target, List<Integer> path, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (; i < nums.length; i++) {
            if (nums[i] > target) {
                break;
            }
            path.add(nums[i]);
            dfs(i + 1, nums, target - nums[i], path, ans);
            path.remove(path.size() - 1);
            // 注意这个case,1 2 2 2 5,最后一个2会重复使用，所以1，2，2的组合有3个
            // 需要在递归函数出栈时，跳过重复的2（如果在入栈时跳过，那么不会有一个1，2，2的path出现）
            while (i < nums.length-1 && nums[i + 1] == nums[i]) {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        Solution40 s = new Solution40();
        int[] nums = {2, 5, 2, 1, 2};
        System.out.println(s.combinationSum2(nums, 5));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
