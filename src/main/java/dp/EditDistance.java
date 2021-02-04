package dp;
//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作：
//
//
// 插入一个字符
// 删除一个字符
// 替换一个字符
//
//
//
//
// 示例 1：
//
//
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
//
//
// 示例 2：
//
//
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
//
//
//
//
// 提示：
//
//
// 0 <= word1.length, word2.length <= 500
// word1 和 word2 由小写英文字母组成
//
// Related Topics 字符串 动态规划
// 👍 1386 👎 0

/**
 * lc no.72 最小编辑距离
 * dp二维表画出来了，但是状态转移方程有问题，只通过部分case
 * 关键：思考状态转移方程，它一定是有道理的，可以用语言描述的，而不是强行找规律！
 */
public class EditDistance {
    //错误版本
    public int minDistance_(String word1, String word2) {
        int l1 = word1.length(), l2 = word2.length();
        if (l1 == 0) {
            return l2;
        }
        if (l2 == 0) {
            return l1;
        }
        int[][] dp = new int[l1 + 1][l2 + 1];
        //初始值不对，随便找的最大值，不符合实际意义
        int max = Math.max(l1, l2) + 1;
        for (int i = 0; i <= l1; i++) {
            dp[i][0] = max;
        }
        for (int j = 0; j <= l2; j++) {
            dp[0][j] = max;
        }
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                int min = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    //状态转移方程不对
                    //当字符word1[i]==word2[j]时，那么应该直接让dp[i][j]=dp[i-1][j-1]
                    // 因为两边都增加相同字符不会产生影响
                    dp[i][j] = min == max ? 0 : min;
                } else {
                    dp[i][j] = min == max ? 1 : min + 1;
                }
            }
        }
        return dp[l1][l2];
    }
    //正确版本
    public int minDistance(String word1, String word2) {
        int l1 = word1.length(), l2 = word2.length();
        if (l1 == 0) {
            return l2;
        }
        if (l2 == 0) {
            return l1;
        }
        int[][] dp = new int[l1 + 1][l2 + 1];
        //原因：第一列，word2为空字符，那么dp[i][j]的值就等于word1的长度
        for (int i = 0; i <= l1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= l2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    //原因：两边最后一个字符相等，那么都word1/work2同时后退即可，[i-1][j-1]的值就是同时后退一位的最小编辑距离～
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //原因：两边最后一个字符不相等，那么编辑距离肯定加一，从 word1后退/word2后退/同时后退 三种状态选择最小值
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[l1][l2];
    }
}
