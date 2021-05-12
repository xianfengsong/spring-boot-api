package leetcode.editor.cn;//给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
//
// 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你
//设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。 
//
// 返回分配方案中尽可能 最小 的 最大工作时间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：jobs = [3,2,3], k = 3
//输出：3
//解释：给每位工人分配一项工作，最大工作时间是 3 。
// 
//
// 示例 2： 
//
// 
//输入：jobs = [1,2,4,7,8], k = 2
//输出：11
//解释：按下述方式分配工作：
//1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
//2 号工人：4、7（工作时间 = 4 + 7 = 11）
//最大工作时间是 11 。 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= jobs.length <= 12 
// 1 <= jobs[i] <= 107 
// 
// Related Topics 递归 回溯算法 
// 👍 201 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * no.1723 把数组划分为k个最平均的子集
 * 考察对暴力回溯不断优化
 */
class Solution1723 {
    int ans = Integer.MAX_VALUE;
    int[] works;

    /**
     * 方法一：回溯，通过记录当前最大公时max,如果已超过max,则退出递归，实现剪枝
     */
    public int minimumTimeRequired(int[] jobs, int k) {
        works = new int[k];
        dfs1(jobs, 0, 0);
        Set<String> s= new HashSet<>();
        s.remove("");
        return ans;
    }

    /**
     * 这里没想到，只会套模板，看下
     * 每次选择的子节点是工人的集合，根节点是任务
     * @param jobs
     * @param i
     * @param max
     */
    public void dfs(int[] jobs, int i, int max) {
        if (max >= ans) {
            //剪枝：ans只能减小
            return;
        }
        if (i == jobs.length) {
            ans = max;
            System.out.println("ans=max=" + max +" ,works="+ Arrays.toString(works));
            return;
        }
        //让i随每次调用递增就行！
        for (int j = 0; j < works.length; j++) {
            works[j] += jobs[i];
            System.out.println("i=" + i + ",j=" + j + ",hour=" + works[j]);
            //更新当前最大公时
            dfs(jobs, i + 1, Math.max(max, works[j]));
            works[j] -= jobs[i];
        }
    }

    /**
     * 调整分配策略，从原来的按顺序分，改成按空闲程度分，因为目的就是越平均越好
     * @param jobs
     * @param i
     * @param max
     */
    public void dfs1(int[] jobs, int i, int max) {
        if (max >= ans) {
            //剪枝：ans只能减小
            return;
        }
        if (i == jobs.length) {
            ans = max;
            System.out.println("ans=max=" + max +" ,works="+ Arrays.toString(works));
            return;
        }
        //让i随每次调用递增就行！
        Integer [] index = new Integer[works.length];
        for(int j=0;j<works.length;j++){
            index[j]=j;
        }
        Arrays.sort(index, Comparator.comparingInt(o -> works[o]));
        for (int j = 0; j < index.length; j++) {
            works[index[j]] += jobs[i];
            System.out.println("i=" + i + ",j=" + j + ",hour=" + works[index[j]]);
            //更新当前最大公时
            dfs1(jobs, i + 1, Math.max(max, works[index[j]]));
            works[index[j]] -= jobs[i];
        }
    }

    public static void main(String[] args) {
        Solution1723 s = new Solution1723();
        int[] jobs = {1, 2,3};
        int ans = s.minimumTimeRequired(jobs, 2);
        System.out.println(ans);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
