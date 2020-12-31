package search;

public class CalculateMinimumHP {
    int[][] mark;

    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        if (col == 0) {
            return -1;
        }
        mark = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mark[i][j] = -1;
            }
        }
        return dfs(0, 0, row, col, dungeon) + 1;
    }

    /**
     * get minHp at arr[r][c]
     *
     * @param r
     * @param c
     * @param arr
     * @return
     */
    private int dfs(int r, int c, int row, int col, int[][] arr) {
        if (r >= row || c >= col) {
            return Integer.MAX_VALUE;
        }
        if (mark[r][c] != -1) {
            return mark[r][c];
        }
        if (r == row - 1 && c == col - 1) {
            return 0 - Math.min(0, arr[r][c]);
        }
        int right = dfs(r + 1, c, row, col, arr);
        int down = dfs(r, c + 1, row, col, arr);
        int nextHp = Math.min(right, down);
        int curHp = Math.max(0, nextHp - arr[r][c]);
        mark[r][c] = curHp;
        return curHp;
    }
}
