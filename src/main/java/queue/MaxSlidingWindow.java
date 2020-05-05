package queue;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 239. 滑动窗口最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 * 滑动窗口问题 考虑双向队列
 * 1. 维护大小k的大根堆,复杂度 O(log k *n)
 * 2. 使用双向队列 O(n)
 * 维护队列第一个元素为窗口最大值,元素入队时干掉前面小于它的元素
 * 需要记录队列第一个元素是否在当期窗口,技巧是队列中保存元素在数组的下标i,而不是元素的值
 */
public class MaxSlidingWindow {
    @Test
    public void test() {
        int[] source = new int[]{1, 3, 1, 2, 0, 5};
        int[] r = maxSlidingWindow(source, 3);
        Assert.assertEquals(Arrays.toString(new int[]{3, 3, 2, 5}), Arrays.toString(r));
        int[] r_ = maxSlidingWindow_(source, 3);
        Assert.assertEquals(Arrays.toString(new int[]{3, 3, 2, 5}), Arrays.toString(r_));
    }

    public int[] maxSlidingWindow_(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        int[] r = new int[nums.length - k + 1];
        int c = 0;
        for (int i = 0; i < nums.length; i++) {
            q.offer(nums[i]);
            if (q.size() == k) {
                r[c] = q.peek();
                q.remove(nums[c]);
                c++;
            }
        }
        return r;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> q = new LinkedList<>();
        if (nums == null || nums.length < k || k <= 0) {
            return null;
        }
        int[] m = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            //去掉不属于当期窗口的元素
            if (!q.isEmpty() && i - q.peekFirst() >= k) {
                q.pollFirst();
            }
            //干掉前面的小数
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            //记录下标
            q.addLast(i);
            //保存最大值
            if (i >= k - 1) {
                //注意越界
                m[i + 1 - k] = nums[q.peekFirst()];
            }
        }
        return m;
    }
}
