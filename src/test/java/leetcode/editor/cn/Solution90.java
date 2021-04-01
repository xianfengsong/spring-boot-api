package leetcode.editor.cn;//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
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
// 
// 
// 
// Related Topics 数组 回溯算法 
// 👍 541 👎 0


import java.util.*;
import java.util.stream.Collectors;


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * lc.90 子集II
 * 回溯问题可以从树的思想出发
 * 回溯剪枝问题练习
 */
class Solution90 {
    //-----------暴力法：树完整遍历---------------
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //为了处理，顺序不同，但是元素相同的子集
        Arrays.sort(nums);
        //前序遍历二叉树，左右子树是否把当前节点加入list，树高度由数组大小决定
        preorder(0, nums, new ArrayList<>());
        Set<String> trim = new HashSet<>();
        System.out.println(result);
        System.out.println("size="+result.size());

        return result.stream().filter(l -> trim.add(l.toString())).collect(Collectors.toList());
    }

    private void preorder(int i, int[] nums, List<Integer> list) {
        if (i < nums.length) {
            list.add(nums[i]);
            preorder(i + 1, nums, list);
            list.remove(list.size() - 1);
            preorder(i + 1, nums, list);
        } else {
            //复制新对象
            List<Integer> t = new ArrayList<>(list);
            result.add(t);
        }
    }

    //----------暴力法遍历+缓存-----------------------
    Map<Integer, List<List<Integer>>> mem = new HashMap<>();

    /**
     * 缓存结果？还是要去重，可以节省时间（作用不明显），代码爆炸
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        //为了处理，顺序不同，但是元素相同的子集,比如 [1,2] & [2,1]
        Arrays.sort(nums);
        //修改了递归返回值，让结果可以缓存
        List<List<Integer>> ans = foo(0, nums, new ArrayList<>());
        //没解决重复问题
        Set<String> trim = new HashSet<>();
        return ans.stream().filter(l -> trim.add(l.toString())).collect(Collectors.toList());
    }

    public List<List<Integer>> foo(int i, int[] nums, List<Integer> path) {
        if (i >= nums.length) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(new ArrayList<>(path));
            return ans;
        } else {
            if (mem.containsKey(i)) {
                List<List<Integer>> ans = mem.get(i);
                List<List<Integer>> copy = new ArrayList<>();
                for (List<Integer> a : ans) {
                    List<Integer> pathc = new ArrayList<>(path);
                    pathc.addAll(a);
                    copy.add(pathc);
                }
                mem.put(i, null);
                return copy;
            }
            List<List<Integer>> left = foo(i + 1, nums, path);
            path.add(nums[i]);
            left.addAll(foo(i + 1, nums, path));
            path.remove(path.size() - 1);
            mem.put(i, left);
            return left;
        }
    }

    //-------------剪枝遍历-----------------------
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        //为了处理，顺序不同，但是元素相同的子集
        Arrays.sort(nums);
        //前序遍历二叉树，左右子树是否把当前节点加入list，树高度由数组大小决定
        dfs(0, nums, new ArrayList<>());
        System.out.println(result);
        System.out.println("size="+result.size());

        return result;
    }

    private void dfs(int i, int[] nums, List<Integer> list) {
        if(i==nums.length){
            result.add(new ArrayList<>(list));
        }
        for(;i<nums.length;i++){
            list.add(nums[i]);
            dfs(i+1,nums,list);
            list.remove(list.size()-1);
            dfs(i+1,nums,list);
        }
    }

    public static void main(String[] args) {
        int[] n = {1,2};
        List<List<Integer>> result = new Solution90().subsetsWithDup2(n);
        System.out.println(result);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
