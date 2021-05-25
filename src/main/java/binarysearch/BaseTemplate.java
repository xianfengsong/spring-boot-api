package binarysearch;

/**
 * 二分查找的基本模版
 * 1.二分查找可以精确查找,
 * 2. 也可以查找最接近的值
 * 3.  还可以在有重复元素时查找target第一次出现的位置
 * 基本模板
 * function binary_search(A, n, T) is
 *  L := 0
 *  R := n − 1      //注意R别越界
 *  while L ≤ R do  // 注意L<=R
 *      m := floor((L + R) / 2)
 *      if A[m] < T then
 *          L := m + 1
 *      else if A[m] > T then
 *          R := m − 1
 *      else:
 *          return m
 *      return unsuccessful
 */
public class BaseTemplate {

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 6, 8};
        BaseTemplate t = new BaseTemplate();
        System.out.println("find:" + t.find(5, arr));
        System.out.println("findFloor:" + t.findFloor(3, arr));
        System.out.println("findCeil:" + t.findCeil(5, arr));
        System.out.println("findFirst:" + t.findFirst(1, new int[]{1, 2, 2, 2, 3}));
    }

    /**
     * 返回target在有序数组中的位置，没有则返回-1
     */
    public int find(int target, int[] arr) {
        // r取最后一个下标
        int l = 0, r = arr.length - 1;
        //这里如果写l<r,那么r要等于arr的长度，r是不可访问的下标
        // 让l<=r 避免漏掉元素
        while (l <= r) {
            //如果l+r可能越界，需要用 m = l+(r-l)/2代替
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

    /**
     * 返回target在数组中第一次出现的位置返回（也可以是数组中小于target的元素数量）
     * 如果不在数组中返回-1
     * 比如 [1,2,2,2,3] target=2 返回 1
     */
    public int findFirst(int target, int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        // l<=r 说明我们在 [l,r]的闭区间内查找
        while (l <= r) {
            System.out.println("l=" + l + ",r=" + r);
            int m = (l + r) / 2;
            if (arr[m] == target) {
                //把查找范围缩小到 [l,m-1]
                r = m - 1;
            } else if (arr[m] < target) {
                l = m + 1;
            } else if (arr[m] > target) {
                r = m - 1;
            }
        }
        System.out.println("final l=" + l );
        // 此时l=r+1，而r=m-1，则l=m
        // 如果target在数组中，arr[l]等于target，否则 l越界（target太大），或者arr[l]不等于target(不能用l==0判断)
        if (l >= arr.length || arr[l] != target) {
            return -1;
        }
        return l;
    }
}
