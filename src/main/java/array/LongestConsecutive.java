package array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * author Xianfeng <br/>
 * date 2021/2/1 下午8:30 <br/>
 * Desc:
 */
public class LongestConsecutive {

    public static void main(String[] args) {
        int[] nums = new int[]{1, -8, 7, -2, -4, -4, 6, 3, -4, 0, -7, -1, 5, 1, -9, -3};
        System.out.println(new LongestConsecutive().longestConsecutive(nums));
        System.out.println(new LongestConsecutive().longestConsecutive_(nums));
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

    public int longestConsecutive_(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = 1;
        Map<Integer, Integer> set = new HashMap<>();
        for (int i : nums) {
            set.put(i, 1);
        }
        for (int i : nums) {
            result = Math.max(result, foo(i - 1, set) + 1);
        }
        return result;
    }

    private int foo(int i, Map<Integer, Integer> m) {
        if (m.containsKey(i)) {
            if (m.get(i) > 1) {
                return m.get(i);
            } else {
                return 1 + foo(i - 1, m);
            }
        } else {
            return 0;
        }
    }
}
