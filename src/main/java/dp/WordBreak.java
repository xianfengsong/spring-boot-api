package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
//
// 说明：
//
//
// 拆分时可以重复使用字典中的单词。
// 你可以假设字典中没有重复的单词。
//
//
// 示例 1：
//
// 输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
//
//
// 示例 2：
//
// 输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
//     注意你可以重复使用字典中的单词。
//
//
// 示例 3：
//
// 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
//  cats,dog,and,og
// Related Topics 动态规划
// 👍 827 👎 0

/**
 * no.139 单词拆分 记忆化回溯 和 dp的经典
 */
public class WordBreak {
    /**
     * 递归回溯法，结果超时
     * 思路：遍历词典中的单词w，如果字符串s以w为前缀，则让s`=s-w执行递归
     * 递归条件：s以w为前缀
     * 结束条件：s为空 或者 词典遍历完成
     * 回溯：因为字符串的特点，不需要主动处理
     * 超时case: s='aa..ab' w=[a,aa,aaa..a,...],除了最后一位b,前缀都是正确的，所以做了大量重复计算
     *
     * @param s
     * @param wordDict
     * @return
     */

    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict == null || wordDict.size() == 0) {
            return false;
        }
        return foo(s, wordDict);
    }

    private boolean foo(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        for (String w : wordDict) {
            if (s.startsWith(w)) {
                String ns = s.substring(w.length());
                if (foo(ns, wordDict)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 动态规划解法，画递归表格发现要遍历s的所有字串
     * dp[i]=ans ,表示s从0到i的字符串是否可拆分
     * dp[0]=true
     * dp[j]=dp[i]&s[i:j]的子串在字典中
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreakII(String s, List<String> wordDict) {
        if (wordDict == null || wordDict.size() == 0) {
            return false;
        }
        boolean [] dp = new boolean[s.length()+1];
        dp[0]=true;
        int l = s.length();
        //遍历所有字串，i起点，j终点
//        for(int i=0;i<l;i++){
//            //注意dp数组边界，和substring左开右闭
//            for(int j=i+1;j<=l;j++){
//                //避免true被false替换
//                if(dp[i] && wordDict.contains(s.substring(i,j))){
//                    dp[j]=true;
//                }
//            }
//        }
        //比上面少了一些遍历
        for(int i=1;i<=l;i++){
            for(int j=0;j<i;j++){
                //找到任意让dp[i]为true的位置j即可
                if(dp[j]&&wordDict.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[l];
    }

    /**
     * 记忆化递归
     * 思路：在前缀递归基础上改进，自己推导一遍递归流程，就会发现s作为foo2的参数被多次执行，可以保存结果
     * 举例：aab [a,aa] 使用单词a时就已经知道foo2(b)返回false,但是使用aa时还会执行foo2(b)
     * @param s
     * @param wordDict
     * @return
     */
    private Map<String,Boolean> m;
    public boolean wordBreakIII(String s, List<String> wordDict){
        if(wordDict==null||wordDict.size()==0){
            return false;
        }
        m=new HashMap<>();
        return foo2(s,wordDict);
    }

    private boolean foo2(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        if(m.containsKey(s)){
            return m.get(s);
        }
        for (String w : wordDict) {
            if (s.startsWith(w)) {
                String ns = s.substring(w.length());
                if (foo2(ns, wordDict)) {
                    m.put(s,true);
                    return true;
                }
            }
        }
        m.put(s,false);
        return false;
    }
    public static void main(String[] args) {
        //aaab  a aa aaa
        String s = "aaab";
        List<String> wl = Arrays.asList("a","aa","aaa");
        System.out.println(new WordBreak().wordBreakIII(s,wl));
    }
}
