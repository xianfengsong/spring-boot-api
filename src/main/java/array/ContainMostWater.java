package array;

/**
 * No.11
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
