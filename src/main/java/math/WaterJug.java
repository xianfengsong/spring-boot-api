package math;

/**
 * 桶倒水问题
 * https://leetcode-cn.com/problems/water-and-jug-problem/
 */
public class WaterJug {
    /**
     * a*x + b*y = z
     * g是x,y最大公约数
     * a*g*i + b*g*j = z
     * g(a*i + b*j) = z ， z满足被g整除
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) {
            return false;
        }
        if (x + y == z) {
            return true;
        }
        return z % greatCommonDivisor(x, y) == 0;
    }

    private int greatCommonDivisor(int a, int b) {
        int m, n;
        if (a > b) {
            m = a;
            n = b;
        } else {
            m = b;
            n = a;
        }
        int t = 0;
        while (n != 0) {
            t = m % n;
            m = n;
            n = t;
        }

        return m;
    }
}
