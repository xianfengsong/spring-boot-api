package leetcode.editor.cn;
//给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
//
// 示例 1: 
//
// 输入: 2
//输出: [0,1,1] 
//
// 示例 2: 
//
// 输入: 5
//输出: [0,1,1,2,1,2] 
//
// 进阶: 
//
// 
// 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？ 
// 要求算法的空间复杂度为O(n)。 
// 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。 
// 
// Related Topics 位运算 动态规划 
// 👍 622 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution338 {

    int[] ans;

    public int[] countBits(int num) {
        ans = new int[num + 1];
        Arrays.fill(ans, -1);
        for (int i = 0; i <= num; i++) {
            count(i, ans);
        }
        return ans;
    }

    public void count(int num, int[] ans) {
        int n = 0;
        int copy = num;
        while (num > 0) {
            n += num & 1;
            num = num >> 1;
            if (ans[num] != -1) {
                n += ans[num];
                break;
            }
        }
        ans[copy] = n;
    }

    public static void main(String[] args) {
        Solution338 s = new Solution338();
        System.out.println(Arrays.toString(s.countBits(5)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
