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
    //用特殊值处理第一个节点
    int i = 0, j = -1;
    List<Integer> ans;

    public List<Integer> spiralOrder(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        int[][] direct = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        //保存上下/左右两种方向上的节点个数
        int[] lens = new int[]{c, r};

        ans = new ArrayList<>();
        int idx = 0, idxl = 0;
        while (ans.size() < r * c) {
            int len = lens[idxl];
            do {
                i += direct[idx][0];
                j += direct[idx][1];
                ans.add(matrix[i][j]);
            } while (--len > 0);
            idx = (idx + 1) % direct.length;
            idxl = (idxl + 1) % 2;
            //遍历完左右方向后，上下方向上数量少一个
            lens[idxl]--;
        }
        return ans;
    }
    public static void main(String []args){
        int [][] m ={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        System.out.println(new Solution54().spiralOrder(m));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
