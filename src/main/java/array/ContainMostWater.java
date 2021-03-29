package array;
//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
//ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
//
// 说明：你不能倾斜容器。
//
//
//
// 示例 1：
//
//
//
//
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
//
// 示例 2：
//
//
//输入：height = [1,1]
//输出：1
//
//
// 示例 3：
//
//
//输入：height = [4,3,2,1,4]
//输出：16
//
//
// 示例 4：
//
//
//输入：height = [1,2,1]
//输出：2
//
//
//
//
// 提示：
//
//
// n = height.length
// 2 <= n <= 3 * 104
// 0 <= height[i] <= 3 * 104
//
// Related Topics 数组 双指针
// 👍 2312 👎 0
/**
 * No.11 装水最多
 * 双指针 + 剪枝
 */
public class ContainMostWater {
    public static int maxArea(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        if (height.length == 1) {
            return height[0];
        }
        int size = 0, l = 0, r = height.length - 1;
        while (l < r) {
            int lh = height[l], rh = height[r];
            size = Math.max(size, Math.min(lh, rh) * (r - l));
            //关键：可以根据指针对应的高度来过滤掉无效的遍历
            //无论是移动l还是r,下次的size宽度w(i)相等,而h(i)取决与当前高度h(l)或h(r),和下一个高度x的最小值
            //假设h(l)<h(r),那么对于h(i)=min(h,x)，h越大,h(i)可能的值也越大，所以r不动，让l移动。（可以画数轴理解）
            if (lh < rh) {
                l++;
            } else {
                r--;
            }
        }
        return size;
    }

    public static void main(String[] args) {
        int[] h = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(h));
        int i = 0;
        System.out.println(i++);
        System.out.println(i++);
        System.out.println(i);
    }
}
