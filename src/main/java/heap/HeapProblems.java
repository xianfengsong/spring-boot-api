package heap;

import org.junit.Assert;
import org.junit.Test;

/**
 * 记住堆的性质：1. 大小 n<=n.child 2.结构：最多两层上有叶节点&节点靠左&到叶节点最大距离<=log2n
 * 堆的数组表示：left_child(i)= i*2 right_child(i)=i*2+1
 * parent(i) = i/2 (i>=1)
 */
public class HeapProblems {
    @Test
    public void test() {
        int[] nums = new int[]{3, 2, 1, 4, 4, 5};
        Assert.assertEquals(4, findKthLargest(nums, 3));
        nums = new int[]{3, 2, 1, 4, 4, 5};
        Assert.assertEquals(3, findKthLargest(nums, 4));
    }
    //--215. 数组中的第K个最大元素

    /**
     * 强行用堆的解法
     * 1.处理数组下标太麻烦
     * 2.交换次数太多
     *
     * @param nums 数组
     * @param k    k
     * @return kth
     */
    public int findKthLargest(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            insert(nums, i);
        }
        return extractK(nums, k);
    }

    public void insert(int[] nums, int i) {
        if (i == 0) {
            return;
        }
        int p;
        while ((p = (i + 1) / 2 - 1) >= 0) {
            if (nums[p] < nums[i]) {
                swap(nums, p, i);
                i = p;
            } else {
                break;
            }
        }
    }

    public int extractK(int[] nums, int k) {
        int i = 0;
        while (i < k - 1) {
            nums[0] = nums[nums.length - 1 - i];
            int c;
            for (int j = 0; (c = (j + 1) * 2 - 1) < nums.length - i; j = c) {
                if (c + 1 < nums.length - i && nums[c] < nums[c + 1]) {
                    c += 1;
                }
                if (nums[c] < nums[j]) {
                    break;
                }
                swap(nums, c, j);
            }
            i++;
        }
        return nums[0];
    }

    public void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
}
