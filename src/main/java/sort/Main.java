package sort;

import design.ShuffleArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 测试 排序
 */
public class Main {

    private Integer[] arr = new Integer[]{5, 4, 3, 6, 1, 1};
    private List<Integer> expect = Arrays.asList(1, 1, 3, 4, 5, 6);

    @Before
    public void shuffle() {
        int[] arrx = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrx[i] = arr[i];
        }
        ShuffleArray.Solution sl = new ShuffleArray.Solution(arrx);
        arrx = sl.shuffle();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arrx[i];
        }
    }
    //------------------三种简单排序-----------------------------

    /**
     * O(n^2)
     * 最好 O(n)
     * 最坏 O(n^2)
     */
    @Test
    public void insert() {
        InsertSort is = new InsertSort();
        is.sort(arr);
        check(arr);
    }

    /**
     * O(n^2)
     * 最好 O(n)
     * 最坏 O(n^2)
     */
    @Test
    public void bubble() {
        BubbleSort sort = new BubbleSort();
        sort.sort(arr);
        check(arr);
    }

    /**
     * O(n^2)
     * 最好 O(n)
     * 最坏 O(n^2)
     */
    @Test()
    public void select() {
        Sort sort = new SelectSort();
        sort.sort(arr);
        check(arr);
    }
    //-----------------高级排序-------------

    /**
     * 快排
     * 最好 O(nlogn)
     * 最坏 O(n^2)
     */
    @Test
    public void quick() {
        Sort sort = new QuickSort();
        sort.sort(arr);
        check(arr);
    }

    /**
     * 堆排序
     * 最好 O(nlogn)
     * 最坏 O(nlogn)
     */
    @Test
    public void heap() {
        Sort sort = new HeapSort();
        sort.sort(arr);
        check(arr);
    }

    /**
     * 归并排序
     * 最好 O(nlogn)
     * 最坏 O(nlogn)
     */
    @Test
    public void merge() {
        Sort sort = new MergeSort();
        sort.sort(arr);
        check(arr);
    }

    private void check(Integer[] arr) {
        Assert.assertEquals("排序失败", expect, Arrays.asList(arr));
    }

}
