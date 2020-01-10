package search;

import org.junit.Assert;
import org.junit.Test;

/**
 * author Xianfeng <br/>
 * date 20-1-10 下午4:55 <br/>
 * Desc:
 * 考虑多种情况下，如何移动指针
 * 越界的错误出现好几次
 * pre/nxt不能越界 l/r不能越界
 */
public class SearchRotateArray {

    int[] a = new int[]{5, 7, 0, 1, 2, 3};

    public int search(int t) {
        int l = 0, r = a.length - 1;
        //t 是否在左边
        boolean left = t >= a[0];
        //防止pre/nxt越界
        while (l < r) {
            int m = (l + r) / 2;
            if (a[m] == t) {
                return m;
            } else {
                if (m == 0 || m == a.length - 1) {
                    return 0;
                }
                int pre = a[m - 1];
                int nxt = a[m + 1];
                //当前a[m] 是否在左边
                boolean inLeft = a[m] >= a[0];

                if (inLeft == left) {
                    if (a[m] < t) {
                        //add m
                        if (a[m] < nxt) {
                            l = m + 1;
                        } else {
                            //meet end
                            return 0;
                        }
                    } else {
                        //less m
                        if (pre < a[m]) {
                            r = m - 1;
                        } else {
                            //meet end
                            return 0;
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
        if (a[l] == t) {
            return l;
        }
        return 0;
    }

    @Test
    public void t() {
        Assert.assertEquals(2, search(0));
        Assert.assertEquals(3, search(1));
        Assert.assertEquals(0, search(4));
        Assert.assertEquals(0, search(5));
        Assert.assertEquals(1, search(7));
    }

}
