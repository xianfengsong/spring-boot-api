package sort;

import java.util.Arrays;

/**
 * author Xianfeng <br/>
 * date 19-6-14 上午11:03 <br/>
 * Desc: 通过交换相邻的两个，每次把范围内最大的移到最后
 */
public class BubbleSort implements Sort {

    @Override
    public void sort(Integer[] arr) {
        boolean swapped=false;
        for (int i = arr.length - 1; i > 0; i--) {
            //到i即可，i后面的已经是最大的
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                    swapped=true;
                }
            }
            System.out.println(Arrays.asList(arr));
            if(!swapped){
                //没有交换，说明已经排序完成
                //对已经有序的情况，可以减少循环
                break;
            }
        }
    }
}
