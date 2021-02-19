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


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution140 {
    Map<String, List<String>> m = new HashMap<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) {
            return new ArrayList<>();
        }
        return foo(s, wordDict);
    }
    private List<String> foo(String s, List<String> dict) {
        List<String> ans = new ArrayList<>();
        if (s.length() != 0) {
            if (m.containsKey(s)) {
                return m.get(s);
            }
            for (String w : dict) {
                if (s.startsWith(w)) {
                    List<String> subAns = foo(s.substring(w.length()), dict);
                    for (String sub : subAns) {
                        String a=w + " " + sub;
                        ans.add(a.trim());
                    }
                    m.put(s, ans);
                }
            }
        }else{
            ans.add("");
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "catsandog";
        List<String> l = Arrays.asList("cats","dog","sand","and","cat");
        System.out.println(new Solution140().wordBreak(s, l));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
