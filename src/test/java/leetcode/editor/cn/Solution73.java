package leetcode.editor.cn;

public class Solution73 {
    /**
     * 用位运算，有问题，貌似越界
     * row,col的大小取决于matrix的大小
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int row = 0;
        int col = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    int mi = 1 << i;
                    int mj = 1 << j;
                    if ((row & mi) != mi) {
                        row += mi;
                    }
                    if ((col & mj) != 1) {
                        col += mj;
                    }
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int mi = 1 << i;
                int mj = 1 << j;
                if ((row & mi) == mi || (col & mj) == mj) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 把除了第一行，第一列外所有出现0的位置归纳到第一行，第一列
     * 原本第一行，第一列是否有0，用两个变量表示
     * @param matrix
     */
    public void setZeroesV2(int[][] matrix) {
        boolean mkRow = false,mkCol = false;
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[i][0]==0){
                mkCol = true;
            }
        }
        for (int j = 0; j < matrix[0].length; j++) {
            if(matrix[0][j]==0){
                mkRow = true;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }
        //注意从1开始
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        //如果第一行/列原本有0，处理下
        if (mkCol) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[i][0] = 0;
            }
        }
        if (mkRow) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    /**
     * 用第一列的第一个元素，代替mkRow，节省一个变量
     * @param matrix
     */
    public void setZeroesV3(int[][] matrix) {
        boolean mkCol = false;
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[i][0]==0){
                mkCol = true;
            }
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }
        //这里同样不会处理第一行/列原本的0
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < matrix[0].length; j++) {
            if(matrix[0][0]==0){
                matrix[0][j]=0;
            }
        }
        if (mkCol) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
    public static void main(String[] args) {
        int[][] m = {};

        System.out.println("1>>0=" + (1 << 0));
        System.out.println("1>>3=" + (1 << 3));
    }
}
