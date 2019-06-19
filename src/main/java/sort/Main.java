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

    Integer[] arr = new Integer[]{2, 8, 3, 7, 1, 7, 0};
    List<Integer> expect = Arrays.asList(0, 1, 2, 3, 7, 7, 8);

    @Before
    public void print(){
        System.out.println("2, 8, 3, 7, 1, 7, 0");
    }
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

    private void check(Integer[] arr){
        Assert.assertEquals("排序失败",expect,Arrays.asList(arr));
    }

}
