package leetcode.editor.cn;//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
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
// 1 <= m, n <= 100 
// -104 <= matrix[i][j], target <= 104 
// 
// Related Topics 数组 二分查找 
// 👍 391 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * lc.74 17:15~18:00
 * 二分查找练习，注意这里的二分查找，支持返回邻近大于/小于target的结果
 */
class Solution74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int[] headOfRow = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            headOfRow[i] = matrix[i][0];
        }
        int row = find(headOfRow, target);
        if (row < 0) {
            return false;
        }
        int ans = find(matrix[row], target);
        return matrix[row][ans] == target;
    }

    /**
     * 二分能不能bugfree啊，这里改了几次
     * @param arr
     * @param t
     * @return 如果t在arr中，返回下标，否则返回arr中小于t的最近元素的下标
     */
    public int find(int[] arr, int t) {
        int i = 0, j = arr.length - 1;
        //二叉树固定套路
        while (i <= j) {
            int m = (i + j) / 2;
            if (arr[m] == t) {
                return m;
            } else if (arr[m] > t) {
                j = m - 1;
            } else {
                i = m + 1;
            }
        }
        // 返回小于t的最近元素，如果没有比t小的，返回-1
        // 想要大于t的最近元素，返回i,此时i==j,arr[i-1] < t < arr[i]
        return i - 1;
    }

    /**
     * 想形成一位数组，一次二分查找完成
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int x = matrix[mid / n][mid % n];
            if (x < target) {
                low = mid + 1;
            } else if (x > target) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int [] arr = {1,3,5,8};
        int result = new Solution74().find(arr,7);
        System.out.println(arr[result]);
//        int[][] n = {{1, 3, 5, 7},{10,11,16,20},{23,30,34,60}};
//        boolean success = new Solution74().searchMatrix(n,3);
//        System.out.println("success="+success);
//        boolean fail = new Solution74().searchMatrix(n,15);
//        System.out.println("fail="+fail);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
