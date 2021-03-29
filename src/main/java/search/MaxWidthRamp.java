package search;

import java.util.Arrays;
import java.util.Comparator;
//给定一个整数数组 A，坡是元组 (i, j)，其中 i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。
//
// 找出 A 中的坡的最大宽度，如果不存在，返回 0 。
//
//
//
// 示例 1：
//
// 输入：[6,0,8,2,1,5]
//输出：4
//解释：
//最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
//
//
// 示例 2：
//
// 输入：[9,8,1,0,1,9,4,0,4,1]
//输出：7
//解释：
//最大宽度的坡为 (i, j) = (2, 9): A[2] = 1 且 A[9] = 1.
//
//
//
//
// 提示：
//
//
// 2 <= A.length <= 50000
// 0 <= A[i] <= 50000
//
//
//
// Related Topics 数组
// 👍 109 👎 0
/**
 * lc.962 最大宽度坡
 * 数组问题，涉及大小比较，需要枚举所有pair时，考虑先排序，减少枚举次数
 * 使用 Arrays.sort(B, Comparator.comparingInt(i -> A[i]));
 * https://leetcode-cn.com/problems/maximum-width-ramp/
 */
public class MaxWidthRamp {
    /**
     * 看了题解
     * 关键是创建B，重建A的索引
     * B的值是A的index
     * B的index不是A的值，而是按A的值排序后的序号（索引了A中值的大小关系）
     *
     * @param A
     * @return
     */
    public int maxWidthRamp(int[] A) {
        int n = A.length;
        Integer[] B = new Integer[n];
        for (int i = 0; i < n; i++) {
            B[i] = i;
        }
        //按照A中值的大小为B的元素排序
        Arrays.sort(B, Comparator.comparingInt(i -> A[i]));

        //max width
        int ans = 0;
        // min index
        int m = n;
        for (int b : B) {
            ans = Math.max(ans, b - m);
            m = Math.min(m, b);
        }
        return ans;
    }
}
