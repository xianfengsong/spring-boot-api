package leetcode.editor.cn;
//给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的
//句子。 
//
// 说明： 
//
// 
// 分隔时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//输出:
//[
//  "cats and dog",
//  "cat sand dog"
//]
// 
//
// 示例 2： 
//
// 输入:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//输出:
//[
//  "pine apple pen apple",
//  "pineapple pen apple",
//  "pine applepen apple"
//]
//解释: 注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 输入:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出:
//[]
// 
// Related Topics 动态规划 回溯算法 
// 👍 415 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution140 {

    List<String> ans = new ArrayList<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return ans;
        }
        foo(s, wordDict, new StringBuilder());
        return ans;
    }

    private void foo(String s, List<String> dict, StringBuilder r) {
        if (s.length() == 0) {
            ans.add(r.toString());
        } else {
            for (String w : dict) {
                if (s.startsWith(w)) {
                    String t = r.toString();
                    r.append(w).append(" ");
                    foo(s.substring(w.length()), dict, r);
                    r = new StringBuilder(t);
                }
            }

        }
    }

    public static void main(String[] args) {
        String s = "pineapplepenapple";
        List<String> l = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(new Solution140().wordBreak(s, l));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
