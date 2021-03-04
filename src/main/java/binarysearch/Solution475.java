package binarysearch;
//冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
//
// 在加热器的加热半径范围内的每个房屋都可以获得供暖。 
//
// 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。 
//
// 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。 
//
// 
//
// 示例 1: 
//
// 
//输入: houses = [1,2,3], heaters = [2]
//输出: 1
//解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
// 
//
// 示例 2: 
//
// 
//输入: houses = [1,2,3,4], heaters = [1,4]
//输出: 1
//解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
// 
//
// 示例 3： 
//
// 
//输入：houses = [1,5], heaters = [2]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= houses.length, heaters.length <= 3 * 104 
// 1 <= houses[i], heaters[i] <= 109 
// 
// Related Topics 二分查找 
// 👍 189 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 重点：
 * 0。用二分代替枚举查找
 * 1。用排序创造二分条件
 * 2。二分法如何搜索临近值
 */
class Solution475 {
    /**
     * 暴力查找，每个房子只需要被最近的暖气加热，所以先求每个房子到最近的暖气的距离，再求距离中的最大值
     * O(n*m)
     *
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        int ans = 0;
        if (houses.length == 0 || heaters.length == 0) {
            return ans;
        }
        for (int h : houses) {
            int minDistance = Math.abs(heaters[0] - h);
            for (int j = 1; j < heaters.length; j++) {
                minDistance = Math.min(minDistance, Math.abs(heaters[j] - h));
            }
            ans = Math.max(ans, minDistance);
        }
        return ans;
    }

    /**
     * 二分查找 既然已经有O(n*m)的解法，那么让m变成log m可以降低复杂度
     * 原来通过遍历所有距离找到房子最近的暖气，现在用二分法找房子最近的暖气
     */
    public int findRadiusV2(int[] houses, int[] heaters) {
        //heaters先排序，才能执行二分查找
        Arrays.sort(heaters);
        int ans = 0;
        for (int h : houses) {
            int l = 0, r = heaters.length;
            //通过二分搜索heaters,找到h左右相邻的暖气，求最小距离
            while (l < r) {
                int mid = (l + r) / 2;
                //小心越界
                if (heaters[mid] < h) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            //处理特殊条件，所有房子都在暖气右边，或者左边
            int toLeft = r == 0 ? Integer.MAX_VALUE : Math.abs(heaters[r - 1] - h);
            int toRight = r == heaters.length ? Integer.MAX_VALUE : Math.abs(heaters[r] - h);
            ans = Math.max(ans, Math.min(toLeft, toRight));
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
