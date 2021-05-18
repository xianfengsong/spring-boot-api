package leetcode.editor.cn;//给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
//
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。 
//
// 
//
// 示例 1： 
//
// 输入："abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"
// 
//
// 示例 2： 
//
// 输入："aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa" 
//
// 
//
// 提示： 
//
// 
// 输入的字符串长度不会超过 1000 。 
// 
// Related Topics 字符串 动态规划 
// 👍 576 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution647 {

    /**
     * 中心扩散
     * 前提：
     * 任何一个回文串都可以找到中心点c，c向外扩散n个相同字符就得到回文串
     * 比如 a 向外扩散1个b,得到bab
     * 但是中心点还可以为两个元素，比如baab,不存在一个元素组成的中心点
     * 思路：
     * 长度为n的回文串，可能由n个单元素的中心点c，或者n-1个双元素的中心点
     * 中心点总数 n+n-1 = 2n-1, 枚举s中所有可能的中心点，尝试扩散，如果可以构成回文串则ans+1
     *
     * O(n^2) 空间 O(1)
     */
    public int countSubstrings(String s) {
        int ans = 0;
        for (int center = 0; center < 2 * s.length() - 1; center++) {
            //中心点为奇数l=r, 偶数l+1=r
            int left = center / 2;
            int right = left + center % 2;
            //判断有几个回文
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
