package leetcode.editor.cn;

//在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
//
// 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足： 
//
// 
// nums1[i] == nums2[j] 
// 且绘制的直线不与任何其他连线（非水平线）相交。 
// 
//
// 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。 
//
// 以这种方法绘制线条，并返回可以绘制的最大连线数。 
//
// 
//
// 示例 1： 
//
//
// 
//输入：nums1 = [1,4,2], nums2 = [1,2,4]
//输出：2
//解释：可以画出两条不交叉的线，如上图所示。 
//但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相
//交。
// 
//
// 
// 示例 2： 
//
// [2,5,1,2,5]
//输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
//输出：3
// 
//
// 
// 示例 3： 
//
// 
//输入：nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
//输出：2 
//
// 
// 
// 
//
// 提示： 
//
// 
// 1 <= nums1.length <= 500 
// 1 <= nums2.length <= 500 
// 1 <= nums1[i], nums2[i] <= 2000 
// 
//
// 
// Related Topics 数组 
// 👍 173 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * lc.1035
 * 最长公共子序列变种
 * 或者用记忆化的dfs解决
 */
class Solution1035 {

    public int maxUncrossedLinesV0(int[] nums1, int[] nums2) {
        int ans = 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }

    Integer[][] mem;

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        mem = new Integer[nums1.length][nums2.length];
        return dfs(0, 0, nums1, nums2,0);
    }

    private int dfs(int i, int j, int[] nums1, int[] nums2,int depth) {
        if (i >= nums1.length || j >= nums2.length) {
            return 0;
        }
        StringBuilder s= new StringBuilder();
        for(int a=0;a<depth;a++){
            s.append("-");
        }
        System.out.println(s+"i="+i+",j="+j);
        if (mem[i][j] != null) {
            return mem[i][j];
        }
        //每个节点三个选择
        // 当前i=j匹配，同时移动，
        // 移动i
        // 移动j
        // 因为是递增的所以不会交叉
        int r = 0;
        if (nums1[i] == nums2[j]) {
            r = dfs(i + 1, j + 1, nums1, nums2,depth+1) + 1;
        }
        int moveI = dfs(i + 1, j, nums1, nums2,depth+1);
        int moveJ = dfs(i, j + 1, nums1, nums2,depth+1);
        r = Math.max(r, Math.max(moveI, moveJ));
        mem[i][j] = r;
        return r;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
