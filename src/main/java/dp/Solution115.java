package dp;
//给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
//
// 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列
//，而 "AEC" 不是） 
//
// 题目数据保证答案符合 32 位带符号整数范围。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "rabbbit", t = "rabbit"
//输出：3
//解释：å
//如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
//(上箭头符号 ^ 表示选取的字母)
//rabbbit
//^^^^ ^^
//rabbbit
//^^ ^^^^
//rabbbit
//^^^ ^^^
// 
//
// 示例 2： 
//
// 
//输入：s = "babgbag", t = "bag"
//输出：5
//解释：
//如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。 
//(上箭头符号 ^ 表示选取的字母)
//babgbag
//^^ ^
//babgbag
//^^    ^
//babgbag
//^    ^^
//babgbag
//  ^  ^^
//babgbag
//    ^^^ 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length, t.length <= 1000 
// s 和 t 由英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 441 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 第一种递归，无法记忆化，因为递归没有返回值。。而且参数i,len会对应多个解
 * 第二种递归，记忆化之后，和dp复杂度类似
 * 可以先从递归出发，向dp优化，但是要注意参数和解的选择
 * 实现dp后，还可以考虑空间优化，二维是否可以用一维代替
 * <p>
 * 递归的思维是自顶向下，执行过程是自底向上
 * dp的思维是自底向上，执行也是自底向上
 */
class Solution115 {
    char[] source;
    char[] target;
    int ans = 0;

    /**
     * 一开始的想到的方法：用回溯法，递归遍历s所有长度等于len(t)的子序列保存到list,计算list中等于t的数量
     * 优化1：如果发现字符不再匹配，跳过递归
     * 优化2：执行1后，发现不需要保存list,因为递归到最后（len=0）的都相等，所以统计到递归结束的数量即可，也不需要回溯
     * <p>
     * 但是超时了，只能用dp
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        source = s.toCharArray();
        target = t.toCharArray();
        if (s.length() < t.length()) {
            return 0;
        }
        allSub(0, t.length());
        return ans;
    }


    public void allSub(int start, int len) {
        //当len为0说明全部字符配对
        if (len == 0) {
            ans++;
            return;
        }
        //不断更新起点，直到起点到结束的长度超过s的长度
        for (int i = start; i + len - 1 < source.length; i++) {
            if (source[i] != target[target.length - len]) {
                continue;
            }
            allSub(i + 1, len - 1);
        }
    }

    /**
     * 另一种递归，返回不同i,j对应的有效解的数量
     *
     * @param s
     * @param t
     * @return
     */
    int lenS = 0, lenT = 0;

    public int numDistinct_0(String s, String t) {
        lenS = s.length();
        lenT = t.length();
        source = s.toCharArray();
        target = t.toCharArray();
        return count(0, 0);
    }

    /**
     * 返回s[0:i]中t[0:j]的数量
     * 退出条件：i或j走到末尾
     *
     * @param i
     * @param j
     * @return
     */
    public int count(int i, int j) {
        if (j >= lenT) {
            return 1;
        }
        //j<lenT而i到s末尾，没有解
        if (i >= lenS) {
            return 0;
        }
        if (source[i] == target[j]) {
            //两种选择
            //i，j匹配，i+1,j+1说明接受s[i]作为解的一部分
            // i+1,j不变，说明虽然相等，但不接受s[i]
            return count(i + 1, j + 1) + count(i + 1, j);
        } else {
            //不匹配，在s中继续寻找
            return count(i + 1, j);
        }
    }

    /**
     * 看题解，发现使用dp表格即可，状态转移规律/原理不太好发现
     * dp[i][j] 表示s[0:j] 中等于 t[0:i]的子序列数量
     * 动手画一下，得到状态转移方程
     * 如果s[j]==t[i] dp[i][j] == dp[i-1][j-1] + dp[i][j-1]
     * 否则 dp[i][j] = dp[i][j-1]
     * 原理是如果s[j],t[i]字符相等，那么子序列数量= s[0:j-1]包含t[i]的数量A（dp[i][j-1]）
     * + s[0:j-1]包含t[i-1]的数量B（增加t[i]后，B不变）
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct_1(String s, String t) {
        int r = t.length() + 1;
        int c = s.length() + 1;
        if (c < r) {
            return 0;
        }
        int[][] dp = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (i == 0) {
                    dp[i][j] = 1;
                } else if (j != 0) {
                    //每次只需要上一行和左边的数据，可以优化，上一行用temp保存，左边数据累加？
                    int before = dp[i - 1][j - 1];
                    int left = dp[i][j - 1];
                    dp[i][j] = s.charAt(j - 1) == t.charAt(i - 1) ? before + left : left;
                }
            }
        }
        return dp[r - 1][c - 1];
    }

    /**
     * 实现dp 优化空间，无法用人类语言解释理解，只是代码变量上的优化
     * @param s
     * @param t
     * @return
     */
    public int numDistinct_3(String s, String t) {
        int m = s.length(), n = t.length();
        int[] dp = new int[n + 1];
        //t长度为0，总有一个解
        dp[0] = 1;
        for (int i = 1; i <= m; i++) {
            int temp = dp[0];
            for (int j = 1; j <= n; j++) {
                // 更新前保存旧值，更新j+1时要用，代表的是dp[i-1][j-1]
                int old = dp[j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    //temp是更新前的j-1位置的值
                    dp[j] += temp;
                }
                temp=dp[j-1];
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
        Solution115 s = new Solution115();
//        int result = s.numDistinct_2("aabdbaabeeadcbbdedacbbeecbabebaeeecaeabaedadcbdbcdaabebdadbbaeabdadeaabbabbecebbebcaddaacccebeaeedababedeacdeaaaeeaecbe", "bddabdcae");
        int result = s.numDistinct_3("baba", "bba");
        System.out.println(result);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
