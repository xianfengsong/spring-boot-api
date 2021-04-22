package heap;

import java.util.Arrays;
import java.util.Scanner;

/**
 * author Xianfeng <br/>
 * date 2021/4/15 下午5:31 <br/>
 * Desc:
 * 练习如何手动从数组创建堆
 * 数组表示法的基本原则
 * left(i)=i/2+1
 * right(i)=i/2+2
 * parent(i)=(i-1)/2 (记不住就举例验证下)
 * 创建堆的方法：
 * 1.递归，每次递归保证已arr[i]为顶的堆的性质，从后向前遍历数组建堆
 */
public class CreateHeap {

    //---使用递归方式创建堆---

    public void buildMaxHeap(int[] arr) {
        int lastParent = ((arr.length - 1) - 1) / 2;
        for (int i = lastParent; i >= 0; i--) {
            heapifyMax(arr, i);
        }
    }

    public void buildMinHeap(int[] arr) {
        int lastParent = ((arr.length - 1) - 1) / 2;
        for (int i = lastParent; i >= 0; i--) {
            heapifyMin(arr, i);
        }
    }

    public void heapifyMax(int[] arr, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;
        //比较左节点和i
        if (left < arr.length && arr[left] > arr[max]) {
            max = left;
        }
        //比较右节点和i
        if (right < arr.length && arr[right] > arr[max]) {
            max = right;
        }
        if (max != i) {
            swap(arr, i, max);
            //继续向下维护以arr[max](就是arr[i])为root的堆
            heapifyMax(arr, max);
        }
    }

    public void heapifyMin(int[] arr, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int min = i;
        //比较左节点和i
        if (left < arr.length && arr[left] < arr[min]) {
            min = left;
        }
        //比较右节点和i
        if (right < arr.length && arr[right] < arr[min]) {
            min = right;
        }
        if (min != i) {
            swap(arr, i, min);
            //继续向下维护以arr[min](就是arr[i])为root的堆
            heapifyMax(arr, min);
        }
    }

    public void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        CreateHeap createHeap = new CreateHeap();
        int[] arr = {4, 10, 3, 5, 1};
        createHeap.buildMaxHeap(arr);
        System.out.println("max heap:" + Arrays.toString(arr));
        createHeap.buildMinHeap(arr);
        System.out.println("min heap:" + Arrays.toString(arr));

        Scanner sc = new Scanner(System.in);
        System.out.println("input..");
    }
}
