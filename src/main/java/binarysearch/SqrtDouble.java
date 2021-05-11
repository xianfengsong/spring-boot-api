package binarysearch;

/**
 * 实现开平方根的函数，要求返回整数v在指定精度t内的结果
 */
public class SqrtDouble {
    public double sqrt(int v, double t) {
        if (v <= 0) {
            return 0.0d;
        }
        if(v==1){
            return 1.0d;
        }
        return 0.0d;
    }
}
