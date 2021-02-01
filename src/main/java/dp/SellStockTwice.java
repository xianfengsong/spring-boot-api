package dp;
//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
//
// 示例 1:
//
//
//输入：prices = [3,3,5,0,0,3,1,4]
//输出：6
//解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
//
// 示例 2：
//
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
//
//
// 示例 3：
//
//
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
//
// 示例 4：
//
//
//输入：prices = [1]
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= prices.length <= 105
// 0 <= prices[i] <= 105
//
// Related Topics 数组 动态规划
// 👍 669 👎 0

//买卖股票的最佳时机 III

/**
 * lc no.123 卖出两次股票
 * tip: O(n^2)会超时，使用 三维数组 dp[days][hold][tradeTimes]=资产
 * 难点是tradeTimes的状态转移
 * 用二维数组无法表示交易次数时，要想到增加一个纬度（或者简化变量）
 */
public class SellStockTwice {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int[][][] dp = new int[prices.length][2][3];
        for (int i = 0; i < prices.length; i++) {
            int p = prices[i];
            for (int t = 1; t <= 2; t++) {
                if (i == 0) {
                    dp[0][0][t] = 0;
                    dp[0][1][t] = -p;
                } else {
                    // 昨天也空仓/卖出昨天持仓（交易次数不变）
                    dp[i][0][t] = Math.max(dp[i - 1][0][t], dp[i - 1][1][t] + p);
                    // 昨天已经持仓 / 昨天空仓，今天买入（交易次数+1）
                    dp[i][1][t] = Math.max(dp[i - 1][0][t - 1] - p, dp[i - 1][1][t]);
                }
            }
        }
        return dp[prices.length - 1][0][2];
    }
}
