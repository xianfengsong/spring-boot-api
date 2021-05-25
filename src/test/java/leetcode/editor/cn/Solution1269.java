package leetcode.editor.cn;//有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
//
// 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。 
//
// 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。 
//
// 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。 
//
// 
//
// 示例 1： 
//
// 输入：steps = 3, arrLen = 2
//输出：4
//解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
//向右，向左，不动
//不动，向右，向左
//向右，不动，向左
//不动，不动，不动
// 
//
// 示例 2： 
//
// 输入：steps = 2, arrLen = 4
//输出：2
//解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
//向右，向左
//不动，不动
// 
//
// 示例 3： 
//
// 输入：steps = 4, arrLen = 2
//输出：8
// 
//
// 
//
// 提示： 
//
// 
// 1 <= steps <= 500 
// 1 <= arrLen <= 10^6 
// 
// Related Topics 动态规划 
// 👍 144 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1269 {

    public int numWaysV2(int steps, int arrLen) {
        int mod = 10000_0000_7;
        //保证能返程的前提下，我们最远能 走多远
        int maxLen = Math.min(steps / 2 + 1, arrLen);
        int[][] dp = new int[steps + 1][maxLen];
        dp[0][0] = 1;
        for (int i = 1; i < steps + 1; i++) {
            for (int j = 0; j < maxLen; j++) {
                int stay = dp[i - 1][j];
                int left = j + 1 < maxLen ? dp[i - 1][j + 1] : 0;
                int right = j > 0 ? dp[i - 1][j - 1] : 0;
                //不能三个数一起加，小心溢出。。
                dp[i][j] = ((stay + left) % mod + right) % mod;
            }
        }
        return dp[steps][0];
    }

    public int numWays(int steps, int arrLen) {
        int mod = 10000_0000_7;
        //保证能返程的前提下，我们最远能 走多远
        int maxLen = Math.min(steps / 2 + 1, arrLen);
        //每次计算时只用到了上一行，所以改成两个一维数组
        //int[][] dp = new int[steps + 1][maxLen];
        //保存上一行的结果
        int[] pre = new int[maxLen];
        pre[0] = 1;
        //计算steps次
        for (int i = 0; i < steps; i++) {
            //每次计算一行
            int[] dp = new int[maxLen];
            for (int j = 0; j < maxLen; j++) {
                // 等价于 stay  dp[i - 1][j]
                int stay = pre[j];
                // 加上一行的左右
                int left = j + 1 < maxLen ? pre[j + 1] : 0;
                int right = j > 0 ? pre[j - 1] : 0;
                //不能三个数一起加，小心溢出。。
                dp[j] = ((left + right) % mod + stay)%mod;
            }
            //更新pre
            pre = dp;
        }
        return pre[0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
