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

/**
 * no.354
 * 暴力法：先按照信封大小降序排序，然后i从大到小，向前找能装下i的信封，判断是否应该加入进去，加入后的信封数量最大
 * 最优解：这个一般想不到，有点扯
 * -- 1。 先化简问题（重点），如果信封只有宽度 并且 宽度都不一样，那么按照宽度排序选择最长上升序列的长度即可
 * -- 2。 处理长度： 那就先让信封按宽度排序，然后在信封长度组成的数组中，寻找上升序列（严格的上升序列）
 * -- 3。 处理宽度相同的问题：比如[1,2][1,3][1,4]的情况，让信封按宽度升序排序后，再按长度倒叙，这样长度数组上升序列的最大值就从3变成1
 *                       （很难想到，没有其他办法？）
 * -- 4。优化 长度数组中最长上升序列的计算过程，参考 no.300 使用dp + 二分法 得到 O(nlogn)
 */
class Solution354 {
    /**
     * 暴力法：想到了面积排序，不错了，不过复杂度变化不大
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        Integer [] index = new Integer[envelopes.length];
        for(int i=0;i<envelopes.length;i++){
            index[i]=i;
        }
        //按面积从大到下排序
        Arrays.sort(index, comparingInt(o -> envelopes[o][0] * envelopes[o][1]));
        int ans = 0;
        //辅助存储，记录信封i作为套娃最内层时，套娃中的信封总数
        int [] mem = new int[index.length];
        for(int i=0;i<index.length-1;i++){
            int w = envelopes[index[i]][0];
            int h = envelopes[index[i]][1];
            //在面积大的信封中寻找最应该加入的
            for(int j=i-1;j>=0;j--){
                int wj = envelopes[index[j]][0];
                int hj = envelopes[index[j]][1];
                //实际不知道那个最优，所以0～i-1都要遍历
                if(wj>w&&hj>h){
                    //加入j还是保持现状
                    mem[i]=Math.max(mem[j]+1,mem[i]);
                }
            }
            //更新套娃最大值
            ans = Math.max(mem[i],ans);
        }
        //从0开始，记得加一
        return ans+1;
    }

    /**
     * 牛逼的排序+dp+二分
     * @param env
     * @return
     */
    public int maxEnvelopesV2(int[][] envelopes){
        if(envelopes.length==0){
            return 0;
        }
        //sort(width:1,height:-1)  排序
        Arrays.sort(envelopes, (o1, o2) -> {
            if(o1[0]!=o2[0]){
                return o1[0]-o2[0];
            }else{
                return o2[1]-o1[1];
            }
        });
        // 参考 lc.300
        List<Integer> tails = new ArrayList<>();
        for (int[] anEnv : envelopes) {
            int t = anEnv[1];
            int size = tails.size();
            if (size == 0 || tails.get(size - 1) < t) {
                //tails都比t小
                tails.add(t);
            } else {
                //在tails里搜索有没有能顶替的位置
                int l = 0, r = size - 1;
                while (l < r) {
                    int m = (l + r) / 2;
                    if (tails.get(m) < t) {
                        l = m + 1;
                    } else {
                        r = m;
                    }
                }
                tails.set(l, t);
            }
        }
        return tails.size();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
