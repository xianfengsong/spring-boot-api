package heap;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Random;

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
    int idx = 0;

    @Test
    public void test() {
        int[] nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6, 7, 7, 8, 2, 3, 1, 1, 1, 10, 11, 5, 6, 2, 4, 7, 8, 5, 6};
        Assert.assertEquals(8, findKthLargestV3(nums, 2));


    }

    /**
     * 方法一：手动大根堆
     *
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

    private int findKthLargestV3(int[] nums, int k) {
        idx = nums.length - k;

        return quickSelect(nums, 0, nums.length - 1);
    }

    private int quickSelect(int[] nums, int l, int r) {
        if (l == r) {
            return nums[l];
        }
        Random rnd = new Random();
        int p = l + rnd.nextInt(r - l + 1);
        p = partition(nums, l, r, p);
        if (p == idx) {
            return nums[p];
        }
        //如果是快排，这里两个分支都要执行
        if (p < idx) {
            return quickSelect(nums, p + 1, r);
        } else {
            return quickSelect(nums, l, p - 1);
        }
    }

    /**
     * 返回p,l~p比arr[p]小，p~r比arr[p]大
     *
     * @param nums nums
     * @param l    left
     * @param r    right
     * @param i    随机哨兵位置
     * @return p
     */
    private int partition(int[] nums, int l, int r, int i) {
        int pivot = nums[i];
        swap(nums, l, i);
        int p = l + 1;
        for (int j = p; j <= r; j++) {
            if (nums[j] < pivot) {
                swap(nums, p, j);
                p++;
            }
        }
        swap(nums, l, p - 1);
        return p - 1;
    }

    private void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
}
