package leetcode.editor.cn;
//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。 
//
// 
// 
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[["Q"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 9 
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。 
// 
// 
// 
// Related Topics 回溯算法 
// 👍 826 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 回溯练习，先确定需要可用解的思路，用语言讲出来
 *
 */
class Solution51 {

    private final char QUEEN = 'Q';
    private final char EMPTY = '.';

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] nums = new char[n][n];
        dfs(0, 0, nums, ans);
        return ans;
    }

    private void dfs(int i, int j, char[][] nums, List<List<String>> ans) {
        if (i >= nums.length) {
            List<String> path = new ArrayList<>();
            for (char[] num : nums) {
                StringBuilder b = new StringBuilder();
                for (int c = 0; c < nums.length; c++) {
                    b.append(num[c]==QUEEN?QUEEN:EMPTY);
                }
                path.add(b.toString());
            }
            ans.add(path);
            return;
        }
        for (int c=0; c < nums.length; c++) {
            if (!check(nums, i, c)) {
                continue;
            }
            nums[i][c] = QUEEN;
            dfs(i + 1, (c + 1) % nums.length, nums, ans);
            nums[i][c] = EMPTY;
        }
    }

    private boolean check(char[][] nums, int i, int j) {
        //up/down
        for (int r = 0; r < nums.length; r++) {
            if (r != i && nums[r][j] == QUEEN) {
                return false;
            }
        }
//        //left/right
//        for (int c = 0; c < nums.length; c++) {
//            if (c != j && nums[i][c] == QUEEN) {
//                return false;
//            }
//        }
        //topleft
        int r = i, c = j;
        while (--r >= 0 && --c >= 0) {
            if (nums[r][c] == QUEEN) {
                return false;
            }
        }
        //bottomright
        r = i;
        c = j;
        while (++r < nums.length && ++c < nums.length) {
            if (nums[r][c] == QUEEN) {
                return false;
            }
        }
        //topright
        r = i;
        c = j;
        while (--r >= 0 && ++c < nums.length) {
            if (nums[r][c] == QUEEN) {
                return false;
            }
        }
        //bottom left
        r = i;
        c = j;
        while (--c >= 0 && ++r < nums.length) {
            if (nums[r][c] == QUEEN) {
                return false;
            }
        }
        return true;
    }
    public static void main(String []args){
        System.out.println(new Solution51().solveNQueens(1));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
