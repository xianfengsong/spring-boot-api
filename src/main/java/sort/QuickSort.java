package sort;

import java.util.Random;

/**
 * author Xianfeng <br/>
 * date 19-6-14 下午2:43 <br/>
 * Desc:
 * 关键："递归分治" "找到中间点p"
 * <p>
 * 1.找到position:
 * 选择基准值c,遍历数组找到位置p,并交换数组元素和arr[p],让p左边小于c，右边大于c
 * 最后交换arr[p]和c
 * 2.递归：
 * 然后从p将数组分成两份递归执行这个步骤
 */
public class QuickSort implements Sort {

    @Override
    public void sort(Integer[] arr) {
        if (arr.length <= 1) {
            return;
        }
        quickSort(0, arr.length - 1, arr);
    }

    Random rand = new Random();
    private void quickSort(int l, int r, Integer[] arr) {
        if (l < r & r < arr.length & l >= 0) {
            int p = partition(l, r, arr);
            quickSort(l, p - 1, arr);
            quickSort(p + 1, r, arr);
        }

    }

    private int partition(int l, int r, Integer[] arr) {
        int rd = rand.nextInt(r - l) + l + 1;
        int pivot = arr[rd];
        arr[rd] = arr[l];
        arr[l] = pivot;
        while (l < r) {
            while (l < r && arr[r] >= pivot) {
                r--;
            }
            arr[l] = arr[r];
            while (l < r && arr[l] <= pivot) {
                l++;
            }
            arr[r] = arr[l];
        }
        arr[l] = pivot;
        return l;
    }

}
