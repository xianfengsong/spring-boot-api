package leetcode.editor.cn;
//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 
// 👍 2971 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution15 {

    Map<Integer, Long> m = new HashMap<>();
    Set<String> f = new HashSet<>();
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        m= Arrays.stream(nums).boxed().collect(Collectors.groupingBy(i->i,Collectors.counting()));
        for (int n : nums) {
            ans.addAll(twoSum(nums, n));
        }
        return ans;
    }

    public List<List<Integer>> twoSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        m.put(target, m.get(target) - 1);
        for (int n : nums) {
            String key = Math.max(n,target)+"/"+Math.min(n,target);
            if(f.contains(key)){
                continue;
            }
            long old = m.get(n);
            if(old==0){
                continue;
            }
            int e = - target - n;
            m.put(n, old - 1);
            if (m.getOrDefault(e, 0L) > 0) {
                List<Integer> a = new ArrayList<>();
                a.add(target);
                a.add(e);
                a.add(n);
                ans.add(a);
                f.add(key);
                f.add(Math.max(e,target)+"/"+Math.min(e,target));
                f.add(Math.max(e,n)+"/"+Math.min(e,n));
            }
            m.put(n, old);
        }
        m.put(target, m.get(target) + 1);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution15().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
