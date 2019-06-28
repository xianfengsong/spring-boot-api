package sort;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试 排序
 */
public class Main {

    private Integer[] arr = new Integer[]{2, 8, 3, 7, 1, 7, 0};
    private List<Integer> expect = Arrays.asList(0, 1, 2, 3, 7, 7, 8);

    //------------------三种简单排序-----------------------------
    /**
     * O(n^2)
     * 最好 O(n)
     * 最坏 O(n^2)
     */
    @Test
    public void insert(){
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
    public void bubble(){
        BubbleSort sort = new BubbleSort();
        sort.sort(arr);
        check(arr);
    }
    /**
     * O(n^2)
     * 最好 O(n)
     * 最坏 O(n^2)
     */
    @Test
    public void select(){
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
    public void quick(){
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
    public void heap(){
        Sort sort = new HeapSort();
        sort.sort(arr);
        check(arr);
    }

    private void check(Integer[] arr){
        Assert.assertEquals("排序失败",expect,Arrays.asList(arr));
    }

}
