package heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组存储的堆，小根堆
 */
public class ArrayHeap {
    public static void main(String[] args) {
        ArrayHeap heap = new ArrayHeap();
        heap.add(3);
        heap.add(4);
        heap.add(2);
        heap.add(1);
        heap.add(9);
        System.out.println(heap.arr);
    }

    List<Integer> arr = new ArrayList<>();

    /**
     * 取出堆顶元素
     *
     * @return
     */
    public Integer get() {
        if (arr.size() == 0) {
            return null;
        }
        return arr.get(0);
    }

    /**
     * 添加元素
     *
     * @param value
     */
    public void add(Integer value) {
        arr.add(value);
        int index = arr.size() - 1;
        if (index > 0) {
            siftUp(index);
        }
    }

    /**
     * 把刚添加的i处的元素移动到堆中合理的位置上
     *
     * @param i
     */
    private void siftUp(int i) {
        int p = getParent(i);
        while (p >= 0 && arr.get(p) > arr.get(i)) {
            swap(p, i);
            i = p;
            p = getParent(i);
        }
    }

    private int getParent(int i) {
        return (i - 1) / 2;
    }

    private int getLeft(int i) {
        return 2 * i + 1;
    }

    private int getRight(int i) {
        return 2 * i + 2;
    }

    private void swap(int a, int b) {
        int t = arr.get(a);
        arr.set(a, arr.get(b));
        arr.set(b, t);
    }
}
