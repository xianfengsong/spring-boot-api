package leetcode.editor.cn;
//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 深度优先搜索 递归 字符串 回溯算法 
// 👍 1113 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution17 {

    Map<Character, List<String>> m = new HashMap<Character, List<String>>() {{
        put('2', Arrays.asList("a", "b", "c"));
        put('3', Arrays.asList("d", "e", "f"));
        put('4', Arrays.asList("g", "h", "i"));
        put('5', Arrays.asList("j", "k", "l"));
        put('6', Arrays.asList("m", "o", "n"));
        put('7', Arrays.asList("p", "q", "r", "s"));
        put('8', Arrays.asList("t", "u", "v"));
        put('9', Arrays.asList("w", "x", "y", "z"));
    }};

    public static void main(String[] args) {
        System.out.println(new Solution17().letterCombinations("23"));
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null) {
            return new ArrayList<>();
        }
        int total = 1;
        for (char c : digits.toCharArray()) {
            total *= m.get(c).size();
        }
        int j = 0;
        List<String> ans = new ArrayList<>(total);
        while (j < digits.length()) {
            List<String> l = m.get(digits.charAt(j));
            int i = 0;
            while (i < total) {
                if (ans.size() < total) {
                    ans.add(l.get(i % l.size()));
                } else {
                    String a = ans.get(i) + l.get(i % l.size());
                    ans.set(i, a);
                }
                i++;
            }
            j++;
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
