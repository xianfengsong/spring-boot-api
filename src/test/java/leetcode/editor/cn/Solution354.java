package leetcode.editor.cn;//给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
//
// 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。 
//
// 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。 
//
// 注意：不允许旋转信封。 
// 
//
// 示例 1： 
//
// 
//输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
//输出：3
//解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。 
//
// 示例 2： 
//
// 
//输入：envelopes = [[1,1],[1,1],[1,1]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= envelopes.length <= 5000 
// envelopes[i].length == 2 
// 1 <= wi, hi <= 104 
// 
// Related Topics 二分查找 动态规划 
// 👍 443 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparingInt;

//俄罗斯套娃信封问题
//leetcode submit region begin(Prohibit modification and deletion)
class Solution354 {
    public int maxEnvelopes(int[][] envelopes) {
        Integer [] index = new Integer[envelopes.length];
        for(int i=0;i<envelopes.length;i++){
            index[i]=i;
        }
        Arrays.sort(index, comparingInt(o -> envelopes[o][0] * envelopes[o][1]));
        int ans = 0;
        int [] mem = new int[index.length];
        for(int i=0;i<index.length-1;i++){
            int w = envelopes[index[i]][0];
            int h = envelopes[index[i]][1];
            //
            for(int j=i-1;j>=0;j--){
                int wj = envelopes[index[j]][0];
                int hj = envelopes[index[j]][1];
                if(wj>w&&hj>h){
                    mem[i]=Math.max(mem[j]+1,mem[i]);
                }
            }
            ans = Math.max(mem[i],ans);
        }
        return ans+1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
