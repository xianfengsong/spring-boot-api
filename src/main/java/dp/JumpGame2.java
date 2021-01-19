package dp;
//给定一个非负整数数组，你最初位于数组的第一个位置。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
//
// 示例:
//
// 输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
//
//
// 说明:
//
// 假设你总是可以到达数组的最后一个位置。
// Related Topics 贪心算法 数组

/**
 * LC no.45 用贪心策略解决，问题是代码不够简洁，使用了额外空间
 */
public class JumpGame2 {
    /**
     * 我的版本：直线思维，按实际跳跃情况遍历数组，使用price数组，记录每个节点能到达的最远位置
     */
    public int jump(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int[] price = new int[nums.length];
        int jump = 0;
        for (int i = 0; i < nums.length; i++) {
            price[i] = nums[i] == 0 ? 0 : nums[i] + i;
        }
        for (int i = 0; i < nums.length; ) {
            jump++;
            if (nums.length - 1 <= nums[i] + i) {
                break;
            } else {
                i = find(price, i + 1, i + nums[i]);
            }
        }
        return jump;
    }

    private int find(int[] price, int s, int e) {
        int p = s;
        int max = 0;
        while (s <= e) {
            if (price[s] != 0 && price[s] >= max) {
                p = s;
                max = price[s];
            }
            s++;
        }
        return p;
    }

    /**
     * LC 题解：逻辑思维，按正常顺序遍历每个节点，使用变量记录每次跳跃选择的节点
     * 遍历时：更新当前最优解，记录下个跳跃节点
     */
    public int jump_v1(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int jump = 0;
        int maxPosition = 0;
        int end = 0;
        //i<nums.length-1 :最后一个节点不用访问
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i == end) {
                //本次跳跃完成，做下一次跳跃准备
                end = maxPosition;
                jump += 1;
            }
        }
        return jump;
    }
}