package dp;
// 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
// 如果没有任何一种硬币组合能组成总金额，返回
// -1。
//
// 你可以认为每种硬币的数量是无限的。
//
//
//
// 示例 1：
//
//
//输入：coins = [1, 2, 5], amount = 11
//输出：3
//解释：11 = 5 + 5 + 1
//
// 示例 2：
//
//
//输入：coins = [2], amount = 3
//输出：-1
//
// 示例 3：
//
//
//输入：coins = [1], amount = 0
//输出：0
//
//
// 示例 4：
//
//
//输入：coins = [1], amount = 1
//输出：1
//
//
// 示例 5：
//
//
//输入：coins = [1], amount = 2
//输出：2
//
//
//
//
// 提示：
//
//
// 1 <= coins.length <= 12
// 1 <= coins[i] <= 231 - 1
// 0 <= amount <= 104
//
// Related Topics 动态规划
// 👍 1015 👎 0

import java.util.Arrays;

/**
 * 零钱兑换
 * 使用dp表格枚举所有情况，初始思路正确，还是考虑不够全面
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        //和题解相比还是不够简洁
        for (int r = 1; r <= amount; r++) {
            int min = Integer.MAX_VALUE;
            for (int val : coins) {
                if (val == r) {
                    min = 1;
                    break;
                } else if (r > val && dp[r - val] != -1) {
                    min = Math.min(dp[r - val] + 1, min);
                }
            }
            dp[r] = min == Integer.MAX_VALUE ? -1 : min;
        }
        return dp[amount];
    }
}
