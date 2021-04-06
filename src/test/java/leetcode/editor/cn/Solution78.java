package leetcode.editor.cn;
//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// nums 中的所有元素 互不相同 
// 
// Related Topics 位运算 数组 回溯算法 
// 👍 1113 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(0, nums, new ArrayList<>(), ans);
        return ans;
    }

    public void dfs(int i, int[] nums, List<Integer> path, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(path));
        for (; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(i + 1, nums, path, ans);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 位运算 nums可以转成0,1组成的数组bits,nums[i]=0表示,元素i不加入path
     * bits组成的二进制数，最大值是k=2^nums.length-1，遍历0~k
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets1(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        int len = 1 << nums.length;
        List<List<Integer>> ans = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            List<Integer> path = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                //这儿算了半天。。。
//                if (((i>>j)&1)==1) {
                //这个也可以
                if (((1<<j)&i)!=0) {
                    path.add(nums[j]);
                }
            }
            ans.add(path);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] n = new int[]{1, 2, 3};
        System.out.println(new Solution78().subsets1(n));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
