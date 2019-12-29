package search;

import java.util.Arrays;

/**
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
        Arrays.sort(B, (i, j) -> {
            return Integer.compare(A[i], A[j]);
        });

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
