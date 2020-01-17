package search;

/**
 * 两个有序数组中位数
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 */
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //get m n,n>=m
        int m = nums1.length;
        int n = nums2.length;
        int a[] = nums1;
        int b[] = nums2;
        if (m > n) {
            int t = m;
            m = n;
            n = t;
            int[] ta = a;
            a = b;
            b = ta;
        }

        //binary search : i from 0 to m
        int iMin = 0, iMax = m;
        int halfLen = (m + n + 1) / 2;

        while (iMin <= iMax) {
            int i = (iMax + iMin) / 2;
            int j = halfLen - i;
            //如果i=m a[i]不存在，不能比较b[j-1] a[i]
            if (i < iMax && b[j - 1] > a[i]) {
                iMin = i + 1;
            }
            //if i=0 a[i-1] not exists
            else if (i > iMin && a[i - 1] > b[j]) {
                iMax = i - 1;
            } else {
                // i = m or i=0 or (b[j-1]<=a[i] and a[i-1] <= b[j])
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = b[j - 1];
                } else if (j == 0) {
                    maxLeft = a[i - 1];
                } else {
                    maxLeft = Math.max(b[j - 1], a[i - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = b[j];
                } else if (j == n) {
                    minRight = a[i];
                } else {
                    minRight = Math.min(a[i], b[j]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;


    }
}
