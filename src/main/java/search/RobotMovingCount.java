package search;

/**
 * 剑指 Offer 13. 机器人的运动范围
 * <p>
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * <p>
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RobotMovingCount {
    // 注意审题，有些格子虽然小于k,但是到达路径被截断了
    int r = 0, c = 0;
    boolean[][] visit;

    public int movingCount(int m, int n, int k) {
        visit = new boolean[m][n];
        r = m;
        c = n;
        return dfs(0, 0, k);
    }

    private int dfs(int m, int n, int k) {
        if (m >= r || n >= c || visit[m][n] || !valid(m, n, k)) {
            return 0;
        }
        visit[m][n] = true;
        return 1 + dfs(m - 1, n, k) + dfs(m + 1, n, k) + dfs(m, n - 1, k) + dfs(m, n + 1, k);
    }

    private boolean valid(int m, int n, int k) {
        m = m / 10 + m % 10;
        n = n / 10 + n % 10;
        return m + n <= k;
    }
}
