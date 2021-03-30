package array;


/**
 * author Xianfeng <br/>
 * date 20-1-16 下午7:15 <br/>
 * Desc:
 * https://leetcode-cn.com/problems/game-of-life/
 * state live (1) or dead (0)
 */
public class GameOfLife {

    /**
     * td: live to dead 1->3 1%2==3%2==1
     * tl: dead to live 0->2 0%2==2%2==0
     */
    private int td = 3, tl = 2;

    public void gameOfLife(int[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int a = board[i][j];
                int sum = 0;

                for (int s = i - 1; s <= i + 1; s++) {
                    for (int e = j - 1; e <= j + 1; e++) {
                        if (s == i && e == j) {
                            continue;
                        }
                        try {
                            if (board[s][e] % 2 == 1) {
                                sum++;
                            }
                        } catch (IndexOutOfBoundsException ignored) {
                        }
                    }
                }
                if (board[i][j] == 1) {
                    if (sum < 2) {
                        board[i][j] = td;
                    } else if (sum > 3) {
                        board[i][j] = td;
                    }
                } else if (sum == 3) {
                    board[i][j] = tl;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int a = board[i][j];
                if (a == td) {
                    board[i][j] = 0;
                }
                if (a == tl) {
                    board[i][j] = 1;
                }
            }
        }
    }

    public void test() {
        int[][] board = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        int[][] out = new int[][]{{0, 0, 0}, {1, 0, 1}, {0, 1, 1}, {0, 1, 0}};
        gameOfLife(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.println(board[i][j]==out[i][j]);
            }
        }
    }
}
