package array;

import java.util.HashSet;
import java.util.Set;

/**
 * author Xianfeng <br/>
 * date 2021/2/1 下午8:30 <br/>
 * Desc:
 */
public class LongestConsecutive {

    public static void main(String[] args) {
        int[] nums = new int[]{1, -8, 7, -2, -4, -4, 6, 3, -4, 0, -7, -1, 5, 1, -9, -3};
        nums = new int[]{10, 4, 9, 1, 3, 2, 5};
        System.out.println(new LongestConsecutive().longestConsecutive(nums));
    }

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = 1;
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        for (int i : nums) {
            int c = 1;
            while (set.contains(i - 1)) {
                c++;
                i--;
            }
            result = Math.max(result, c);
        }
        return result;
    }
}
