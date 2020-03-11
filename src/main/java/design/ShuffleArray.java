package design;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 打散数组
 * 1：用swap 构造随机数组 避免重复
 * 2：shuffle有N的阶乘种结果，算法刚好了模拟N!的计算过程
 * https://leetcode-cn.com/problems/shuffle-an-array/solution/xi-pai-suan-fa-shen-du-xiang-jie-by-labuladong/
 */
public class ShuffleArray {
    /**
     * 测试shuffle后的分布，不是100%的随机
     * 如果测试次数不够多，case不会通过
     */
    @Test
    public void test() {
        int s = 3;
        int[] arr = new int[s];
        for (int i = 0; i < s; i++) {
            arr[i] = i + 1;
        }
        Solution sl = new Solution(arr);
        Map<String, Integer> st = new HashMap<>();
        //s的阶乘：对应所有可能
        long allCase = 1L;
        for (long i = 1L; i <= s; i++) {
            allCase = i * allCase;
        }

        for (int i = 0; i < allCase * 3; i++) {
            String shf = getString(sl.shuffle());
            st.compute(shf, (k, v) -> {
                if (v == null) return 1;
                return v + 1;
            });
        }
        int sum = 0;
        for (String k : st.keySet()) {
            sum += st.get(k);
            System.out.println(k);
        }
        Assert.assertEquals(allCase * 3, sum);
        Assert.assertEquals(allCase, st.size());
    }

    String getString(int[] arr) {
        StringBuilder b = new StringBuilder();
        for (int a : arr) {
            b.append(a).append(",");
        }
        return b.toString();
    }

    class Solution {
        Random rand = new Random();
        private int[] array;
        private int[] original;

        public Solution(int[] nums) {
            array = nums;
            original = nums.clone();
        }

        /**
         * 返回min~max的随机数
         *
         * @param min
         * @param max
         * @return
         */
        private int randRange(int min, int max) {
            return rand.nextInt(max - min) + min;
        }

        private void swapAt(int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        public int[] reset() {
            array = original;
            original = original.clone();
            return original;
        }

        public int[] shuffle() {
            //这个循环的过程，刚好满足N的阶乘
            //i=0 有n种可选数字
            //i=n 有1种可选数字
            for (int i = 0; i < array.length; i++) {
                swapAt(i, randRange(i, array.length));
            }
            return array;
        }
    }


}
