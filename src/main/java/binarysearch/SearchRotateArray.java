package binarysearch;


/**
 * author Xianfeng <br/>
 * date 20-1-10 下午4:55 <br/>
 * Desc:
 * 考虑多种情况下，如何移动指针
 * 越界的错误出现好几次
 * pre/nxt不能越界 l/r不能越界
 * 错误：
 * 1. 没考虑不旋转的情况，比如[1,3]
 * 2. 没考虑空数组
 */
public class SearchRotateArray {

    int[] nums = new int[]{1, 3};

    public int search(int target) {
        int l = 0, r = nums.length - 1;
        //target 是否在左边
        boolean left = target >= nums[0];
        //防止pre/nxt越界
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] == target) {
                return m;
            } else {
                int pre = m == 0 ? nums[0] : nums[m - 1];
                int nxt = m == nums.length - 1 ? nums[m] : nums[m + 1];
                //当前a[m] 是否在左边
                boolean inLeft = nums[m] >= nums[0];

                if (inLeft == left) {
                    if (nums[m] < target) {
                        //add m
                        if (nums[m] < nxt) {
                            l = m + 1;
                        } else {
                            //meet end
                            return -1;
                        }
                    } else {
                        //less m
                        if (pre < nums[m]) {
                            r = m - 1;
                        } else {
                            //meet end
                            return -1;
                        }
                    }

                } else if (inLeft) {
                    //go right
                    l = m + 1;
                } else {
                    //go left
                    r = m;
                }
            }
        }
        if (nums[l] == target) {
            return l;
        }
        return -1;
    }


}
