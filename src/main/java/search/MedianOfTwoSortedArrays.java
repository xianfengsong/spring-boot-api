package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 4.两个有序数组中位数
 * 注意复杂度要求
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 */
public class MedianOfTwoSortedArrays {
    //O(m+n)解法，不满足要求
    public double find(int[] nums1, int[] nums2) {
        int l = nums1.length + nums2.length;
        if (l == 0) {
            return 0.0d;
        }
        List<Integer> merge = new ArrayList<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] > nums2[j]) {
                merge.add(nums2[j++]);
            } else {
                merge.add(nums1[i++]);
            }
        }
        while (j < nums2.length) {
            merge.add(nums2[j++]);
        }
        while (i < nums1.length) {
            merge.add(nums1[i++]);
        }
        if (l % 2 == 0) {
            return ((double) merge.get(l / 2) + (double) merge.get(l / 2 - 1)) / 2.0d;
        } else {
            return (double) merge.get(l / 2);
        }
    }

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

    public double findMedianSortedArraysV2(int[] nums1, int[] nums2) {
        int l = nums1.length + nums2.length;
        if (l == 0) {
            return 0.0d;
        }
        if (l % 2 == 0) {
            return ((double) getKth(l / 2, nums1, 0, nums2, 0) + (double) getKth(l / 2 + 1, nums1, 0, nums2, 0)) / 2.0d;
        } else {
            return (double) getKth(l / 2 + 1, nums1, 0, nums2, 0);
        }
    }

    public int getKth(int k, int[] nums1, int i, int[] nums2, int j) {
        if (i >= nums1.length) {
            return nums2[j + k - 1];
        }
        if (j >= nums2.length) {
            return nums1[i + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }
        int ni = Math.min(i + k / 2, nums1.length) - 1;
        int nj = Math.min(j + k / 2, nums2.length) - 1;

        int a = nums1[ni], b = nums2[nj];
        if (a > b) {
            k = k - (nj + 1 - j);
            return getKth(k, nums1, i, nums2, nj + 1);
        } else {
            k = k - (ni + 1 - i);
            return getKth(k, nums1, ni + 1, nums2, j);
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[1][0];
        arr[0] = new int[]{};
        System.out.println(arr.length);
        System.out.println(arr[0].length);
    }
}
