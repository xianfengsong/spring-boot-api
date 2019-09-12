package sort;

import java.util.Arrays;

/**
 * author Xianfeng <br/>
 * date 19-7-3 上午9:54 <br/>
 * Desc: 归并排序
 * 关键点：
 * 1.“递归”：递归地把数组二等分成left,right,直到大小为1
 * 2.合并左右数组时，要申请 大小=左右长度和 的空间，记得处理完所有元素
 */
public class MergeSort implements Sort {

    @Override
    public void sort(Integer[] arr) {
        Integer []result=mergeSort(arr);
        int i=0;
        for(Integer r:result){
            arr[i++]=r;
        }
    }

    private Integer[] mergeSort(Integer[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);
        Integer[] left = Arrays.copyOfRange(arr, 0, middle);
        Integer[] right = Arrays.copyOfRange(arr, middle, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    private Integer[] merge(Integer[] left, Integer[] right) {
        Integer[] result = new Integer[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }
        for (Integer aLeft : left) {
            result[i++] = aLeft;
        }

        for (Integer aRight : right) {
            result[i++] = aRight;
        }
        return result;
    }
}
