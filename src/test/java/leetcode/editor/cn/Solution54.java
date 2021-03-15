package leetcode.editor.cn;
//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics 数组 
// 👍 696 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution54 {

    int i = 0, j = 0;
    List<Integer> ans;

    public List<Integer> spiralOrder(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        int[][] direct = new int[4][2];
        direct[0] = new int[]{0, 1};
        direct[1] = new int[]{1, 0};
        direct[2] = new int[]{0, -1};
        direct[3] = new int[]{-1, 0};
        int[] lens = new int[]{c - 1, r - 1};

        ans = new ArrayList<>();
        ans.add(matrix[0][0]);
        int idx = 0, idxl = 0;
        while (ans.size() < r * c) {
            int len = lens[idxl++ % 2];
            trace(len, direct[idx], matrix);
            idx = (idx + 1) % direct.length;
            if (idx == direct.length - 1) {
                lens[0]--;
                lens[1]--;
            }
        }
        return ans;
    }

    public void trace(int len, int[] direction, int[][] matrix) {
        while (len-- > 0) {
            i += direction[0];
            j += direction[1];
            ans.add(matrix[i][j]);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
