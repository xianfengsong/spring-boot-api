package leetcode.editor.cn;//给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
//
// 题目数据保证总会存在一个数值和不超过 k 的矩形区域。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,0,1],[0,-2,3]], k = 2
//输出：2
//解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[2,2,-1]], k = 3
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -100 <= matrix[i][j] <= 100 
// -105 <= k <= 105 
// 
//
// 
//
// 进阶：如果行数远大于列数，该如何设计解决方案？ 
// Related Topics 队列 二分查找 动态规划 
// 👍 233 👎 0


import java.util.Arrays;
import java.util.TreeSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution363 {

    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix.length == 0) {
            return 0;
        }
        int r = matrix.length, c = matrix[0].length;
        int[] col = new int[c];
        int ans = 0;
        for (int i = 0; i < r; i++) {
            Arrays.fill(col, 0);
            for (int j = i; j < r; j++) {
                //fill col
                for (int t = 0; t < c; t++) {
                    col[t] += matrix[j][t];
                }
                //do range sum
                TreeSet<Integer> set = new TreeSet<>();
                int sum = 0;
                for (int n : col) {
                    sum += n;
                    //sum-x<=k 则 sum-k<=x,x越小越好
                    Integer range = set.ceiling(sum - k);
                    if (range != null) {
                        ans = Math.max(ans, range);
                        if (ans == k) {
                            return ans;
                        }
                    }
                    set.add(sum);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] m = new int[][]{{1}, {2}, {3}};
        new Solution363().maxSumSubmatrix(m, 2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
