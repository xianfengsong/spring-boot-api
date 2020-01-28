package search;


import org.junit.Assert;
import org.junit.Test;

/**
 * 编程珠玑 第二章 A
 * 找到不在超大随机数组中的一个数字
 * 思路：用位运算分组，用二分法查找，查找条件是比较两个分组的size
 * size小的组肯定缺少某个数字
 * 重点：num的更新方式和时机
 */
public class TheNotExistsNumber {
    public int search(int a[], int maxBit, int len) {
        int num = 0;
        int[] arrL = new int[a.length];
        int[] arrR = new int[a.length];
        //b从maxBit-1开始，不然无法二分，比如0～8，要从4开始位运算
        for (int b = maxBit - 1; b >= 0; b--) {
            int check = 1 << b;
            int l = 0, r = 0;
            for (int i = 0; i < len; i++) {
                if ((a[i] & check) == check) {
                    //二进制表示时，位置b上是1的，保存到左边数组
                    arrL[l++] = a[i];
                } else {
                    arrR[r++] = a[i];
                }
            }
            //如果右边数组小，说宁缺少数字，并且这个数字在位置b上二进制的值为0，不需要更新num
            if (l > r) {
                a = arrR;
                len = r;
            } else {
                //缺少数字的是左边，那么缺少的数字一定在位置b上值为1，要更新num在位置b上的值
                num += check;
                a = arrL;
                len = l;
            }
        }
        return num;
    }

    @Test
    public void test() {
        int a[] = new int[]{0, 1, 4, 3, 2, 6, 7, 8};
        Assert.assertEquals(5, search(a, 3, a.length));
    }
}
