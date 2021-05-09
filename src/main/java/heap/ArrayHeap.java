package heap;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

/**
 * 实现数组存储的堆（小根堆）
 * 使用siftUp,siftDown维护堆的性质
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
        for (int i = 1; i <= 3; i++) {
            Assert.assertEquals("i==" + i, i, (int) heap.get());
        }
        System.out.println(heap.arr);
    }

    List<Integer> arr = new ArrayList<>();

    /**
     * 取出堆顶元素
     * 维护堆性质：把最后的元素i移动到堆顶，然后向下交换直到i到达合理位置
     */
    public Integer get() {
        if (arr.size() == 0) {
            return null;
        }
        int result = arr.get(0);
        if (arr.size() > 1) {
            swap(0, arr.size() - 1);
            arr.remove(arr.size() - 1);
            siftDown();
        }
        return result;
    }

    /**
     * 添加元素
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
     */
    private void siftUp(int i) {
        int p = getParent(i);
        while (p >= 0 && arr.get(p) > arr.get(i)) {
            swap(p, i);
            i = p;
            p = getParent(i);
        }
    }

    /**
     * 把新的堆顶元素向下移动到合理位置
     */
    private void siftDown() {
        int i = 0;
        while (i < arr.size()) {
            int l = getLeft(i);
            int r = getRight(i);
            if (l >= arr.size() && r >= arr.size()) {
                break;
            }
            int child;
            if (r < arr.size() && l < arr.size()) {
                child = arr.get(r) < arr.get(l) ? r : l;
            } else if (r < arr.size()) {
                child = r;
            } else {
                child = l;
            }
            if (arr.get(i) > arr.get(child)) {
                swap(i, child);
                i = child;
            } else {
                break;
            }
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
