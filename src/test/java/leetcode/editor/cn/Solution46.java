package leetcode.editor.cn;//给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法 
// 👍 1258 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, new ArrayList<>(), ans);
        return ans;
    }

    public void dfs(int[] nums, List<Integer> path, List<List<Integer>> ans) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
        }
        for (int num : nums) {
            if (path.contains(num)) {
                continue;
            }
            path.add(num);
            dfs(nums, path, ans);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] n = new int[]{1, 2, 3};
        System.out.println(new Solution46().permute(n));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
