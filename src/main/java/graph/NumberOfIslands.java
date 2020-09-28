package graph;

/**
 * No.200 岛屿数量
 * <p>
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumberOfIslands {
    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 5};
        long x = Long.MIN_VALUE;
        for (int i : a) {
            x = x | i;
        }
        for (int i = 0; i < 64; i++) {
            System.out.print(x >> i & 1);
        }

    }

    public int numIslands_uf(char[][] grid) {
        long a = Long.MIN_VALUE;

        if (grid == null || grid.length == 0) {
            return 0;
        }
        if (grid[0].length == 0) {
            return 0;
        }
        int h = grid.length, w = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                grid[i][j] = '0';
                union(uf, i, j, i - 1, j, w, h, grid);
                union(uf, i, j, i + 1, j, w, h, grid);
                union(uf, i, j, i, j - 1, w, h, grid);
                union(uf, i, j, i, j + 1, w, h, grid);
            }
        }
        return uf.count();
    }

    private void union(UnionFind uf, int p, int q, int i, int j, int w, int h, char[][] grid) {
        if (i < h && j < w && i >= 0 && j >= 0 && grid[i][j] == '1') {
            uf.union(getIndex(p, q, w), getIndex(i, j, w));
            grid[i][j] = '0';
        }
    }

    private int getIndex(int i, int j, int w) {
        return i * w + j;
    }

    class Solution {
        public int trap(int[] height) {
            if (height == null || height.length == 0) {
                return 0;
            }

            int w = 0;
            while (true) {
                int prev = -1, z = 0, min = Integer.MAX_VALUE;
                for (int i : height) {
                    if (i == 0) {
                        z++;
                    } else if (i < min) {
                        min = i;
                    }
                }
                if (z == height.length) {
                    break;
                }
                for (int i = 0; i < height.length; i++) {
                    if (height[i] > 0) {
                        height[i] -= min;
                        if (prev >= 0) {
                            w += (i - prev - 1) * min;
                        }
                        prev = i;
                    }
                }
            }
            return w;
        }
    }

}
