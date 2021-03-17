package leetcode.editor.cn;
//请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：
// 要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
// 
//
// 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2
//, 1, 1, 0, 0]。 
//
// 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。 
// Related Topics 栈 哈希表 
// 👍 681 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution739 {

    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            for (int j = i + 1; j < T.length; j++) {
                if (T[j] > T[i]) {
                    ans[i] = j - i;
                    break;
                }
            }
        }
        return ans;
    }

    public int[] dailyTemperatures2(int[] T) {
        int[] ans = new int[T.length];
        TreeMap<Integer, List<Integer>> m = new TreeMap<>();

        for (int i = 0; i < T.length; i++) {
            if (m.containsKey(T[i])) {
                m.get(T[i]).add(i);
            } else {
                int finalI = i;
                m.put(T[i], new ArrayList<Integer>() {{
                    add(finalI);
                }});
            }
        }
        for (int i = 0; i < T.length; i++) {
            Entry<Integer, List<Integer>> e = m.ceilingEntry(T[i] + 1);
            if(e==null){
                continue;
            }
            for (int j : e.getValue()) {
                if (j > i) {
                    ans[i] = j-i;
                    break;
                }
            }
        }
        return ans;
    }
    public static void main(String []args){
        int [] t = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(new Solution739().dailyTemperatures(t)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
