package sort;

/**
 * author Xianfeng <br/>
 * date 19-6-14 上午11:11 <br/>
 * Desc:
 */
public interface Sort {
    void sort(Integer[] arr);

    default void swap(Integer[] arr, int a, int b) {
        if (a != b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }
}
