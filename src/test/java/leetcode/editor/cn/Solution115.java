package leetcode.editor.cn;
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


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution115 {
    char[] source;

    public int numDistinct(String s, String t) {
        source = s.toCharArray();
        if (s.length() < t.length()) {
            return 0;
        }
        allSub(new StringBuilder(), 0, t.length());
        System.out.println(ans);
        int c = 0;
        for (String sub : ans) {
            if (t.equals(sub)) {
                c++;
            }
        }
        return c;
    }

    List<String> ans = new ArrayList<>();


    public void allSub(StringBuilder prefix, int start, int len) {
        if (len == 0) {
            ans.add(prefix.toString());
            return;
        }
        for (int i = start; i + len - 1 < source.length; i++) {
            prefix.append(source[i]);
            allSub(prefix, i + 1, len - 1);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public static void main(String[] args) {
        Solution115 s = new Solution115();
        int result = s.numDistinct("rabbbit", "rabit");
        System.out.println(result);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
