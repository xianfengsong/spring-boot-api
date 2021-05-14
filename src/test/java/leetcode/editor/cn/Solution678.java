package leetcode.editor.cn;//给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
//
// 
// 任何左括号 ( 必须有相应的右括号 )。 
// 任何右括号 ) 必须有相应的左括号 ( 。 
// 左括号 ( 必须在对应的右括号之前 )。 
// * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。 
// 一个空字符串也被视为有效字符串。 
// 
//
// 示例 1: 
//
// 
//输入: "()"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "(*)"
//输出: True
// 
//
// 示例 3: 
//
// 
//输入: "(*))"
//输出: True
// 
//
// 注意: 
//
// 
// 字符串大小将在 [1，100] 范围内。 
// 
// Related Topics 字符串 
// 👍 241 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution678 {

    private final static Character L = '(', R = ')';

    public boolean checkValidString(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        char[] arr = s.toCharArray();
        // 当前为匹配的左括号可能的数量[min,max],遍历结束后0在这个区间内即可
        // 不能让min小于0（不太理解，可能是方便最后的 min == 0 判断，因为min小于0,说明0在区间中）
        int min = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            char a = arr[i];
            //维护min & max
            if (L.equals(a)) {
                min++;
                max++;
            } else if (R.equals(a)) {
                //取了剩余左括号，不能让min小于0
                min = min > 0 ? min - 1 : 0;
                max--;
            } else {
                // *变右括号，这就是贪心的策略？ 遇到*有没匹配的左括号就变右括号匹配上？
                // 不能让min小于0
                min = min > 0 ? min - 1 : 0;
                // *变左括号
                max++;
            }
            //左括号没有了，如果这里是*或（,则max不会小于0,所以此处是没有匹配上的 ）
            if (max < 0) {
                return false;
            }
        }
        return min == 0;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
