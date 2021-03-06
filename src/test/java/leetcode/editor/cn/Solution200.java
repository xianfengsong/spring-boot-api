package leetcode.editor.cn;
//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 991 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution200 {
    int ans =0;
    char m = '-';
    public int numIslands(char[][] grid) {
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                t(grid,i,j,false);
            }
        }
        return ans;
    }
    public void t(char[][]arr,int i,int j,boolean mk){
        if(i<0||i>=arr.length||j<0||j>=arr[0].length){
            return;
        }
        char c = arr[i][j];
        if(m==c){
            return;
        }
        arr[i][j]=m;
        if('1'==c){
            t(arr,i+1,j,true);
            t(arr,i-1,j,true);
            t(arr,i,j-1,true);
            t(arr,i,j+1,true);
            if(!mk){
                ans+=1;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
