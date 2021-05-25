package leetcode.editor.cn;//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
//
// 
// 每行的元素从左到右升序排列。 
// 每列的元素从上到下升序排列。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 5
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 20
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= n, m <= 300 
// -109 <= matix[i][j] <= 109 
// 每行的所有元素从左到右升序排列 
// 每列的所有元素从上到下升序排列 
// -109 <= target <= 109 
// 
// Related Topics 二分查找 分治算法 
// 👍 631 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 利用二分查找思想，在数组中找到一个起点A，从起点A出发刚好把数组分成两部分，一部分比A大，一部分比A小
 * 这样就有了执行二分的条件，这个起点是矩阵的左下角或右上角
 */
class Solution240 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int i = row - 1, j = 0;
        while (i >= 0 && j < col) {
            int cur = matrix[i][j];
            if (cur == target) {
                return true;
            } else if (cur < target) {
                j++;
            } else if (cur > target) {
                i--;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
