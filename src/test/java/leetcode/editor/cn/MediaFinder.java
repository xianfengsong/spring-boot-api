package leetcode.editor.cn;//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
//
// 例如， 
//
// [2,3,4] 的中位数是 3 
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5 
//
// 设计一个支持以下两种操作的数据结构： 
//
// 
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。 
// double findMedian() - 返回目前所有元素的中位数。 
// 
//
// 示例： 
//
// addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3) 
//findMedian() -> 2 
//
// 进阶: 
//
// 
// 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？ 
// 
// Related Topics 堆 设计 
// 👍 411 👎 0

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * no.295 数据流的中位数
 * 时间复杂度logN
 * 两个堆，一个保存[0：mid]一个保存[mid,n]
 * 大根堆保存最小的n/2个元素
 * 小根堆保存最大的n/2个元素
 * 两个堆size大小差距不超过1
 * 根据总数的奇偶从堆顶取中位数
 */
//leetcode submit region begin(Prohibit modification and deletion)
class MedianFinder {
    public static void main(String[] args) {
        MedianFinder f = new MedianFinder();
        f.addNum(-1);
        System.out.println("-1.0==" + f.findMedian());
        f.addNum(-2);
        System.out.println("-1.5==" + f.findMedian());
        f.addNum(-3);
        System.out.println("-2.0==" + f.findMedian());
        f.addNum(-4);
        System.out.println("-2.5==" + f.findMedian());
        f.addNum(-5);
        System.out.println("-3.0==" + f.findMedian());
    }

    /**
     * initialize your data structure here.
     */
    PriorityQueue<Integer> max, min;

    public MedianFinder() {
        max = new PriorityQueue<>(Comparator.reverseOrder());
        min = new PriorityQueue<>();
    }

    /**
     * 循环不变式：
     * max.peek <= min.peek
     * diff(max.size,min.size)<=1
     */
    public void addNum(int num) {
        if (max.size() >= min.size()) {
            //维护max顺序
            max.offer(num);
            //维护min的大小,min可以接受max的最大值
            min.offer(max.poll());
        } else {
            //更新min的最小值
            min.offer(num);
            //max接受min的最小值
            max.offer(min.poll());
        }
    }

    public double findMedian() {
        if (max.size() == min.size()) {
            if (max.peek() == null) {
                return 0.0d;
            }
            return (double) (max.peek() + min.peek()) / 2.0d;
        } else if (max.size() > min.size()) {
            return max.peek() == null ? 0.0d : max.peek();
        } else {
            return min.peek() == null ? 0.0d : min.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)
