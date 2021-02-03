package array;

import java.util.HashSet;
import java.util.Set;
//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
//
//
//
// 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
//
//
//
// 示例 1：
//
//
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
//
// 示例 2：
//
//
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
//
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 104
// -109 <= nums[i] <= 109
//
// Related Topics 并查集 数组
// 👍 662 👎 0
/**
 * author Xianfeng <br/>
 * date 2021/2/1 下午8:30 <br/>
 * Desc: lc no.128 最长连续子序列
 * 为了实现O(n) 借助额外存储空间set保存所有元素
 * 为了避免重复计算，只有当遇到的数i在set中没有前驱时，才按照i++的顺序随机遍历set，保证
 */
public class LongestConsecutive {

    public static void main(String[] args) {
        int[] nums = new int[]{1, -8, 7, -2, -4, -4, 6, 3, -4, 0, -7, -1, 5, 1, -9, -3};
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
            //交给在上升起点的数字处理
            if (set.contains(i - 1)) {
                continue;
            }
            int c = 1;
            while (set.contains(i + 1)) {
                c++;
                i--;
            }
            result = Math.max(result, c);
        }
        return result;
    }
}
