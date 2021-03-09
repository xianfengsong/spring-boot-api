package leetcode.editor.cn;
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


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution132 {

    public int minCut(String s) {
        Set<String> splits = new HashSet<>();
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
            int min = s.length();
            if (splits.contains(0 + "," + i)) {
                dp[i] = 0;
            } else {
                for (int j = i - 1; j >= 0; j--) {
                    if (splits.contains((j + 1) + "," + i)) {
                        dp[i] = Math.min(dp[j] + 1, min);
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }

    public boolean checkSplits(char[] arr, int i, int j, Set<String> splits) {
        String key = i + "," + j;
        if (splits.contains(key)) {
            return true;
        }
        if (i < j) {
            if (arr[i] == arr[j] && checkSplits(arr, i + 1, j - 1, splits)) {
                splits.add(key);
                return true;
            } else {
                return false;
            }
        }else{
            splits.add(key);
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

    public static void main(String[] args) {
        Solution132 s = new Solution132();
        System.out.println("452=" + s
                .minCut("apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwhxfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbixclcxqrtniznemxeahfozp"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
