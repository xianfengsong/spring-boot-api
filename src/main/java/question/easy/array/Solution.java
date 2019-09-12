package question.easy.array;

import java.util.Arrays;

public class Solution {

    /**
     * 从排序数组中删除重复项,在原地删除重复出现的元素
     * 不要使用额外的数组空间
     *
     * @param nums 排序数组
     * @return 数组的新长度
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //这个简洁多了。。。
//        int index=0;
//        for(int j=index+1;j<nums.length;j++){
//            if(nums[j]!=nums[index]){
//                index++;
//                nums[index]=nums[j];
//            }
//        }
//        return index+1;
        int newLength = 1;
        boolean moved = false;

        for (int pointer = 0; pointer < nums.length - 1; pointer++) {

            int compare = nums[pointer];
            for (int i = pointer + 1; i < nums.length; i++) {
                if (compare != nums[i]) {
                    newLength++;
                    if (moved) {
                        nums[newLength - 1] = nums[i];
                        pointer = i - 1;
                    }
                    break;
                } else {
                    moved = true;
                }
            }
        }
        return newLength;
    }

    //是否重复
    public boolean isRepeat(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {

            if (nums[i] == nums[i + 1]) {
                return true;
            }

        }
        return false;
    }
}
