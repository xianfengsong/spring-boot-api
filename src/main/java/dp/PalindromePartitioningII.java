package dp;
//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
//
// 返回符合要求的 最少分割次数 。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：1
//解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：s = "ab"
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 2000 
// s 仅由小写英文字母组成 
// 
// 
// 
// Related Topics 动态规划 
// 👍 348 👎 0


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * lc.132 有思路，但是实现有问题
 */
class PalindromePartitioningII {
    /**
     * 官方实现
     * 用bool[][]数组替换了map
     * 遍历子串更简洁
     * @param s
     * @return
     */
    public int minCut(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];
        //fill g[i][i] true,单一字符不用处理了
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], true);
        }
        //怎么想到的？
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                //i由大到小，j由小到大，保证 g[i + 1][j - 1] 有值
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }
        //f就是dp
        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < n; ++i) {
            if (g[0][i]) {
                f[i] = 0;
            } else {
                for (int j = 0; j < i; ++j) {
                    if (g[j + 1][i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }

        return f[n - 1];
    }

    /**
     * 看题解后自己实现（击败5%。。map应该可以优化）
     * dp[i]表示字符串s在i处的最小分割次数
     * 1. dp[i]=dp[j+1]+1 (0<=j<i) s[j+1,i]是回文串
     * 2. dp[i]=0 s[0,i] 全是回文串
     * 判断s[l,r]是否回文，枚举s所有子串，记录回文子串
     * @param s
     * @return
     */
    public int minCutLongTime(String s) {
        Map<String,Boolean> splits = new HashMap<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                checkSplits(arr, i, j, splits);
            }
        }
        //00 01 11 22
        int[] dp = new int[s.length()];
        dp[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            dp[i] = s.length();
            if (splits.get(0 + "," + i)) {
                dp[i] = 0;
            } else {
                for (int j = i - 1; j >= 0; j--) {
                    if (splits.get((j + 1) + "," + i)) {
                        dp[i] = Math.min(dp[j] + 1, dp[i]);
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }

    public boolean checkSplits(char[] arr, int i, int j, Map<String,Boolean> splits) {
        String key = i + "," + j;
        if (splits.containsKey(key)) {
            return splits.get(key);
        }
        if (i < j) {
            if (arr[i] == arr[j] && checkSplits(arr, i + 1, j - 1, splits)) {
                splits.put(key,true);
                return true;
            } else {
                splits.put(key,false);
                return false;
            }
        }else{
            splits.put(key,true);
            return true;
        }
    }

    private int part = 0;

    /**
     * 思路：
     * 按回文串长度从大到小枚举出s中的回文子串ans，然后计算要得到ans需要的分割次数n
     * ans只保存最长的回文子串，所以n最小
     * 问题：有个case不过，没发现哪里有问题
     */
    public int minCutFail(String s) {
        int len = s.length();
        if (len > 0) {
            String ans = clean(s, len);
            while (ans.length() == 0) {
                len--;
                ans = clean(s, len);
            }
            part += 1;
            if (!s.equals(ans) && s.length() != 1) {

                int st = s.indexOf(ans);
                int ed = st + ans.length();

                if (st != 0) {
                    minCutFail(s.substring(0, st));
                }
                if (ed <= s.length() - 1) {
                    minCutFail(s.substring(ed));
                }
            }
        }
        return Math.max(part - 1, 0);
    }

    public String clean(String s, int len) {
        if (len > s.length()) {
            return s;
        }
        int i = 0, j = i + len - 1;
        char[] arr = s.toCharArray();
        while (i <= j && j < s.length()) {
            if (valid(arr, i, j)) {
                return s.substring(i, j + 1);
            }
            i++;
            j++;
        }
        return "";
    }

    public boolean valid(char[] arr, int i, int j) {
        while (i < j && arr[i] == arr[j]) {
            i++;
            j--;
        }
        return i >= j;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
