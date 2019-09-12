package question.easy.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 初级算法： 数组
 * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/21/
 */
public class Solution {
    /**
     * 从"排序"数组中删除重复项,在原地删除重复出现的元素
     * 不要使用额外的数组空间
     *
     * @param nums 排序数组
     * @return 数组的新长度
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //这个简洁多了。。。
        int index = 0;
        for (int j = index + 1; j < nums.length; j++) {
            if (nums[index] != nums[j]) {
                index++;
                if (index != j) {
                    nums[index] = nums[j];
                }
            }
        }
        return index + 1;
    }

    /**
     * 买卖股票的最佳时机 II
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/22/
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int profit = 0;
        //注意处理特殊情况
        if (prices.length <= 1) {
            return profit;
        }
        int i = prices.length - 1;
        int value = prices[i--];
        while (i >= 0) {
            if (value > prices[i]) {
                profit += value - prices[i];
            }
            value = prices[i];
            i--;
        }
        return profit;
    }

    /**
     * 旋转数组
     * https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/23/
     * 最简单的方法：每次移动向右一位，循环k次
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if (k >= nums.length) {
            k = k % nums.length;
        }
        while (k != 0 && nums.length > 1) {
            int temp = nums[0];
            nums[0] = nums[nums.length - 1];
            for (int i = 1; i < nums.length; i++) {
                int t = nums[i];
                nums[i] = temp;
                temp = t;
            }
            k--;
        }
    }

    /**
     * 找规律：可以直接移动到制定位置，但是当数组元素是偶数个时，会出现循环
     * 所以如果又回到了开始的位置，就把指针向右移动一次
     * 直到所有元素被移动完成，旋转完成
     *
     * @param nums
     * @param k
     */
    public void rotate0(int[] nums, int k) {
        if (k >= nums.length) {
            k = k % nums.length;
        }
        for (int start = 0, count = 0; count < nums.length; start++) {
            int cur = start;
            int prev = nums[cur];
            do {
                int next = (cur + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                cur = next;
                count++;
            } while (cur != start);
        }
    }

    @Test
    public void testRotate() {
        int[] nums = new int[]{-1, -100, 3, 99};
        rotate(nums, 2);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    @Test
    public void testRemoveDuplicates() {
        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        Assert.assertEquals(5, removeDuplicates(nums));

    }


}
