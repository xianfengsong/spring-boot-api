package dp;

/**
 * 爬楼梯问题:返回所有唯一可能走法
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/23/dynamic-programming/54/
 */
public class ClimbStairs {
    /**
     * 记忆递归法  O(n)
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] r = new int[n + 1];
        return climb(0, n, r);
    }

    /**
     * @param i current
     * @param n target
     * @param r 记录走法，减少重复计算
     * @return
     */
    public int climb(int i, int n, int r[]) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        //去重
        if (r[i] > 0) {
            return r[i];
        }
        r[i] = climb(i + 1, n, r) + climb(i + 2, n, r);
        return r[i];
    }

    /**
     * dp[n]=dp[n-1]+dp[n-2]
     */
    public int climbStairsDp(int n) {
        //防止n=1 时,dp[2]越界
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];

    }
}