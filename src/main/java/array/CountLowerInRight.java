package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * author Xianfeng <br/>
 * date 20-1-19 下午8:27 <br/>
 * Desc: No.315
 */
public class CountLowerInRight {

    public int[] count(int[] s) {
        if (s == null) {
            return null;
        } else if (s.length <= 1) {
            return new int[]{0};
        }
        //和MaxWidthRamp中一样，构造索引
        Integer[] idx = new Integer[s.length];
        for (int i = 0; i < s.length; i++) {
            idx[i] = i;
        }
        //按照s中值的大小为idx的元素排序
        Arrays.sort(idx, (i, j) -> {
            return Integer.compare(s[i], s[j]);
        });
        for (int i = 0; i < idx.length; i++) {
            if (i == 0) {
                s[idx[0]] = 0;
            } else {
                int j = i - 1;
                int v = 0;
                while (j >= 0) {
                    if (idx[j] > idx[i]) {
                        v += 1;
                    }
                    j--;
                }
                s[idx[i]] = v;
            }
        }
        return s;
    }

    @Test
    public void test() {
        int[] source = new int[]{5, 2, 6, 1};
        int[] expect = new int[]{2, 1, 1, 0};
        int[] result = count(source);
        for (int i = 0; i < source.length; i++) {
            Assert.assertEquals(expect[i], result[i]);
        }

    }
}
