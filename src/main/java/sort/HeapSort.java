package sort;

import java.util.Arrays;

/**
 * author Xianfeng <br/>
 * date 19-6-28 上午10:50 <br/>
 * Desc:
 * 堆排序要点：
 * 1.用数组表示堆的规则 left(i)=2*i+1 right(i)=2*i+2 parent(i)=floor(i/2)
 * 2.保证大根堆的性质：每个节点的根节点都大于本节点，构造大根堆的过程，需要递归(遇到较小的节点)
 * 3.构造大根堆后，排序通过不断交换根节点和最后一个节点完成（升序排序，构造小根堆也可以吧？）
 */
public class HeapSort implements Sort {

    @Override
    public void sort(Integer[] arr) {
        buildMaxHeap(arr);
        int len = arr.length;
        for (int i = arr.length - 1; i > 0; i--) {
            //0是当前堆的最大根节点，交换到数组末尾
            swap(arr, i, 0);
            //最后一个节点位置已确定
            len--;
            //整理堆
            heapify(arr, 0, len);
        }
    }

    private void buildMaxHeap(Integer[] arr) {
        //start是数组中最后一个非叶子节点
        //最后一个根节点就是最后一个节点的parent
        //没有child的非叶子节点没有必要调整（当然从arr.length-1开始也行）
        int lastIndex = arr.length - 1;
        int start = (lastIndex - 1) / 2;
        for (int i = start; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
    }

    private void heapify(Integer[] arr, int root, int len) {
        int l = root * 2 + 1;
        int r = root * 2 + 2;
        int max = root;
        //找到左中右节点中的最大节点
        if (l < len && arr[l] > arr[max]) {
            max = l;
        }
        if (r < len && arr[r] > arr[max]) {
            max = r;
        }
        if (max != root) {
            //最大节点值放到root上
            swap(arr, root, max);
            //给新的root节点的初始值找到合适位置
            heapify(arr, max, len);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {4, 10, 3, 5, 1};
        HeapSort sort = new HeapSort();
        sort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
