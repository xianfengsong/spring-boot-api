package leetcode.editor.cn;
//给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
//
// 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,0,2,1,1,0]
//输出：[0,0,1,1,2,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,0,1]
//输出：[0,1,2]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[0]
// 
//
// 示例 4： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// nums[i] 为 0、1 或 2 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以不使用代码库中的排序函数来解决这道题吗？ 
// 你能想出一个仅使用常数空间的一趟扫描算法吗？ 
// 
// Related Topics 排序 数组 双指针 
// 👍 824 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution75 {

    public void sortColors(int[] nums) {
        int i = 0, j = 1;
        int target = 0;
        while (true) {
            while (i < j && j < nums.length) {
                if(nums[i]<=target){
                    i++;
                    j++;
                    continue;
                }
                if (nums[j] == target) {
                    int t = nums[i];
                    nums[i] = nums[j];
                    nums[j] = t;
                    i++;
                }
                j++;
            }
            j = i+1;
            target += 1;
            if (target > 2) {
                break;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 一次遍历，统计0,1,2的数量，然后二次遍历，构造nums
     * 时间 O(N),
     * 空间 O(N)
     * @param nums
     */
    public void sortColorsV2(int[] nums) {
        int [] count = new int[3];
        for (int n : nums) {
            count[n]++;
        }
        int cur = 0;
        for(int i=0;i<count.length;i++){
            int c = count[i];
            while (c>0){
                nums[cur++]=i;
                c--;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 双指针，这里指向的是0,1,2三个区间的间隔！，如index1和index2 [0,1) index1 [1,2) index2  [2,n-1]
     * @param nums
     */
    public void sortColorsV3(int[] nums) {
        // 值为0的最后一个节点位置
        int zero = 0;
        // 值为1的最后一个节点位置，遍历指针,指向1，2之间的节点
        int i = 0;
        // 值为2的第一个节点位置 ，1,2 和 2,～ 的分界点
        int two = nums.length-1;
        while (i<two){
            if(nums[i]==0){
                swap(nums,i,zero);
                //发现0,后移
                zero++;
                //位置i被0占用了，先后移
                i++;
            }else if(nums[i]==1){
                //发现1,后移
                i++;
            }else {
                swap(nums,i,two);
                //发现2，前移
                two--;
            }
        }
        System.out.println(Arrays.toString(nums));
    }
    public void swap(int []nums,int a,int b){
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }
    public static void main(String []args){
        int [] nums = new int[]{2,0,2,1,1,0};
        new Solution75().sortColorsV2(nums);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
