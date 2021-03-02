package leetcode.editor.cn;
//给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
//
// 
//上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。 
//
// 
//
// 示例： 
//
// 
//给定 matrix = [
//  [3, 0, 1, 4, 2],
//  [5, 6, 3, 2, 1],
//  [1, 2, 0, 1, 5],
//  [4, 1, 0, 1, 7],
//  [1, 0, 3, 0, 5]
//]
//
//sumRegion(2, 1, 4, 3) -> 8
//sumRegion(1, 1, 2, 2) -> 11
//sumRegion(1, 2, 2, 4) -> 12
// 
//
// 
//
// 提示： 
//
// 
// 你可以假设矩阵不可变。 
// 会多次调用 sumRegion 方法。 
// 你可以假设 row1 ≤ row2 且 col1 ≤ col2 。 
// 
// Related Topics 动态规划 
// 👍 215 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class NumMatrix {
    int [] dp;
    int i,j;
    public NumMatrix(int[][] matrix) {
        i=matrix.length;
        j=matrix[0].length;
        dp=new int[i*j];
        for(int r=0;r<i;r++){
            for(int c=0;c<j;c++){
                int idx=r*j+c;
                if(idx==0){
                    dp[0]=matrix[0][0];
                }else{
                    dp[idx]=dp[idx-1]+matrix[r][c];
                }
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int x1=row1*i+col1;
        int y1=row1*i+col2;
        int sum = dp[x1]-dp[y1-1];
        int x2=row2*i+col2;
        int y2=row2*i+col1;
        sum += dp[x2]-dp[y2-1];
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
//leetcode submit region end(Prohibit modification and deletion)
