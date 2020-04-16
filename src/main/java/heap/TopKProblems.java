package heap;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 * 记住堆的性质：1. 大小 n<=n.child 2.结构：最多两层上有叶节点&节点靠左&到叶节点最大距离<=log2n
 * 堆的数组表示：left_child(i)= i*2 right_child(i)=i*2+1
 * parent(i) = i/2 (i>=1)
 * 堆的操作：
 * siftup: 把新元素放入堆中，从数组末尾开始，沿着parent路径向上移动
 * siftdown: 把根结点用新节点替换，从根开始，新节点沿着child向下移动
 * 215. 数组中的第K个最大元素
 */
public class TopKProblems {

    @Test
    public void test() {
        int[] nums = new int[]{6, 6, 5, 5, 1, 1};
        Assert.assertTrue(findKthLargestV3(nums, 2) == 6);
        Assert.assertTrue(findKthLargestV3(nums, 3) == 5);
        Assert.assertTrue(findKthLargestV3(nums, 5) == 1);
    }

    /**
     * 方法一：手动大根堆
     * <p>
     * 手动实现堆的解法，把n的元素放入大根堆，然后用堆尾元素替换根节点k-1次，提取根节点，最后根节点就是top k
     * 1.手动实现堆，处理数组下标太麻烦
     * 2.没必要构造n个元素的堆，k个元素的堆也可以
     *
     * @param nums 数组
     * @param k    k
     * @return kth
     */
    private int findKthLargestV1(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            insert(nums, i);
        }
        return extractK(nums, k);
    }

    private void insert(int[] nums, int i) {
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

    private int extractK(int[] nums, int k) {
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

    /**
     * 方法二：内置小根堆
     * <p>
     * 使用java内置堆实现，PriorityQueue默认小根堆，堆大小k，将元素保存到堆中，
     * 当堆满了，把根结点（最小元素）剔除，最后被剔除的n-k个元素都比堆中的元素小，根结点就是top k
     *
     * @param nums
     * @param k
     * @return
     */
    private int findKthLargestV2(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        for (int i : nums) {
            if (heap.size() == k) {
                heap.poll();
            }
            heap.add(i);
        }
        return heap.poll();
    }

    /**
     * 方法三：快排模仿
     * 因为5个数第2大就是第4小
     * 要求top k large，用快排的分治法找到第n-k+1小的元素
     * 和快排不同的是，只需要使用partition中包含arr[n-k]的一半数据
     * 所以不会访问整个递归树，平均复杂度 O(n)，最差O(n^2)和快排一样
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestV3(int[] nums, int k) {
        // k是要求的第几大(从1开始计数),k-1即是数组中的序号(0开始计数)
        return findKthLargest(nums, 0, nums.length - 1, k - 1);
    }

    public int findKthLargest(int[] nums, int low, int high, int k) {
        int index = partition(nums, low, high);
        if (index == k)
            return nums[index];
        else if (index > k)
            return findKthLargest(nums, low, index - 1, k);
        else
            return findKthLargest(nums, index + 1, high, k);
    }

    // 同快排的partation,每次确定一个枢轴的位置,并返回其index
    // 找第k 大 的数就把数组按大->小排列
    private int partition(int[] nums, int low, int high) {
        int pivot = nums[low];

        while (low < high) {
            while (low < high && nums[low] >= pivot)
                low++;
            nums[high] = nums[low];
            while (low < high && nums[high] <= pivot) // nums[high]<=pivot 体现出把数组按大->小排列
                high--;
            nums[low] = nums[high];


        }

        nums[low] = pivot;
        return low;
    }

    private void swap(int[] nums, int a, int b) {
        if (a != b) {
            int t = nums[a];
            nums[a] = nums[b];
            nums[b] = t;
        }
    }
}
