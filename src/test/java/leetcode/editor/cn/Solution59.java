package leetcode.editor.cn;
//给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：[[1,2,3],[8,9,4],[7,6,5]]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 
// Related Topics 数组 
// 👍 377 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution59 {
    public int[][] generateMatrix(int n) {
        int t=0,b=n-1;
        int l=t,r=b;
        int [][] ans = new int[n][n];
        int val = 1;
        while (val<=n*n){
            for(int i=l;i<=r;i++){
                ans[t][i]=val++;
            }
            t++;
            for(int i=t;i<=b;i++){
                ans[i][r]=val++;
            }
            r--;
            for(int i=r;i>=l;i--){
                ans[b][i]=val++;
            }
            b--;
            for(int i=b;i>=t;i--){
                ans[i][l]=val++;
            }
            l++;
        }
        return ans;
    }

    public static void main(String [] args){
        int [] [] ans = new Solution59().generateMatrix(3);
        for( int [] a:ans){
            System.out.println(Arrays.toString(a));
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
