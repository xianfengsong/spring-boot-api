package leetcode.editor.cn;
//给定一个整数数组 A，坡是元组 (i, j)，其中 i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。
//
// 找出 A 中的坡的最大宽度，如果不存在，返回 0 。 
//
// 
//
// 示例 1： 
//
// 输入：[6,0,8,2,1,5]
//输出：4
//解释：
//最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
// 
//
// 示例 2： 
//
// 输入：[9,8,1,0,1,9,4,0,4,1]
//输出：7
//解释：
//最大宽度的坡为 (i, j) = (2, 9): A[2] = 1 且 A[9] = 1.
// 
//
// 
//
// 提示： 
//
// 
// 2 <= A.length <= 50000 
// 0 <= A[i] <= 50000 
// 
//
// 
// Related Topics 数组 
// 👍 109 👎 0


import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution962 {
    //[9,8,1,0,1,9,4,0,4,1]
    public int maxWidthRamp(int[] A) {
        int len = A.length-1;
        while (len>0){
            int i=0;
            while (i+len<A.length){
                if(A[i]<=A[i+len]){
                    return len;
                }
                i++;
            }
            len--;
        }
        return 0;
    }
    public int maxWidthRamp1(int[] A) {
        Integer [] sorted = new Integer[A.length];
        for (int i=0;i<sorted.length;i++){
            sorted[i]=i;
        }
        Arrays.sort(sorted, Comparator.comparingInt(o -> A[o]));
        int min = Integer.MAX_VALUE;
        int ans = 0;
        for(int n:sorted){
            if(n>min){
                ans = Math.max(n-min,ans);
            }else{
                min = n;
            }
        }
        return ans;
    }
    public static void main(String []args){
        int [] n ={9,8,1,0,1,9,4,0,4,1};
        System.out.println(new Solution962().maxWidthRamp1(n));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
