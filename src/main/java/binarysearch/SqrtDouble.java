package binarysearch;

/**
 * 实现开平方根的函数，要求返回整数v在指定精度t内的结果
 * 使用二分查找
 * 问题：
 * 1. 精度t=0,但v的开方是无理数，程序会不断循环下去
 */
public class SqrtDouble {

    public static void main(String[] args) {
        int v = 3;
        double t = 0.01d;
        SqrtDouble s = new SqrtDouble();
        double result = s.sqrt(v, t);
        System.out.println("根号" + v + "=" + result);
    }

    public double sqrt(int v, double t) {
        double result = binsearch(v, t);
        //把结果转成指定的精度
        double pad = t == 0.0d ? 1 : 1 / t;
        result = Math.round(result * pad) / pad;
        if (Math.abs(result * result - v) <= t) {
            return result;
        } else {
            //没有得到满足精度的结果
            return -1.0d;
        }
    }

    /**
     * 返回最精确的结果
     */
    public double binsearch(int v, double t) {
        if (v <= 0) {
            return 0.0d;
        }
        if (v == 1) {
            return 1.0d;
        }
        double l = 0.0d;
        double r = v;
        double last = 0.0d;
        while (l < r) {
            double m = (l + r) / 2;
            double dif = m * m - (double) v;
            System.out.println("l=" + l + ",r=" + r + ",m=" + m + ",dif=" + dif);
            //避免死循环，当m不变时退出
            if (m == last) {
                return m;
            }
            if (Math.abs(dif) <= t) {
                return m;
            } else if (dif > 0) {
                r = m;
            } else {
                l = m;
            }
            last = m;
        }
        return 0.0d;
    }
}
