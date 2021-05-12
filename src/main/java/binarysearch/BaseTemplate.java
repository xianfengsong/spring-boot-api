package binarysearch;

/**
 * 二分查找的基本模版
 * 二分查找可以精确查找，也已可以查找最接近的值
 * 基本模板
 * function binary_search(A, n, T) is
 *     L := 0
 *     R := n − 1      //注意R别越界
 *     while L ≤ R do  // 注意L<=R
 *         m := floor((L + R) / 2)
 *         if A[m] < T then
 *             L := m + 1
 *         else if A[m] > T then
 *             R := m − 1
 *         else:
 *             return m
 *     return unsuccessful
 */
public class BaseTemplate {

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 6, 8};
        BaseTemplate t = new BaseTemplate();
        System.out.println("find:" + t.find(5, arr));
        System.out.println("findFloor:" + t.findFloor(3, arr));
        System.out.println("findCeil:" + t.findCeil(5, arr));
    }

    /**
     * 返回target在有序数组中的位置，没有则返回-1
     */
    public int find(int target, int[] arr) {
        // r取最后一个下标
        int l = 0, r = arr.length - 1;
        //这里如果写l<r,那么r要等于arr的长度，r是不可访问的下标
        // 让l<=r 避免漏掉元素
        while (l < r) {
            int m = (l + r) / 2;
            if (arr[m] < target) {
                l = m + 1;
            } else if (arr[m] > target) {
                r = m - 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    /**
     * 返回target在有序数组的位置，没有则返回小于target的最大值
     */
    public int findFloor(int target, int[] arr) {
        int l = 0, r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (arr[m] < target) {
                l = m + 1;
                //只要小于target就保存m到ans
                ans = m;
            } else if (arr[m] > target) {
                r = m - 1;
            } else {
                return m;
            }
        }
        return ans;
    }

    /**
     * 返回target在有序数组的位置，没有则返回大于target的最小值
     */
    public int findCeil(int target, int[] arr) {
        int l = 0, r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (arr[m] < target) {
                l = m + 1;
            } else if (arr[m] > target) {
                r = m - 1;
                //只要大于target就保存m到ans
                ans = m;
            } else {
                return m;
            }
        }
        return ans;
    }
}
