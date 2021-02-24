package math;
//有 buckets 桶液体，其中 正好 有一桶含有毒药，其余装的都是水。它们从外观看起来都一样。为了弄清楚哪只水桶含有毒药，你可以喂一些猪喝，通过观察猪是否
//会死进行判断。不幸的是，你只有 minutesToTest 分钟时间来确定哪桶液体是有毒的。
//
// 喂猪的规则如下：
//
//
// 选择若干活猪进行喂养
// 可以允许小猪同时饮用任意数量的桶中的水，并且该过程不需要时间。
// 小猪喝完水后，必须有 minutesToDie 分钟的冷却时间。在这段时间里，你只能观察，而不允许继续喂猪。
// 过了 minutesToDie 分钟后，所有喝到毒药的猪都会死去，其他所有猪都会活下来。
// 重复这一过程，直到时间用完。
//
//
// 给你桶的数目 buckets ，minutesToDie 和 minutesToTest ，返回在规定时间内判断哪个桶有毒所需的 最小 猪数。
//
//
//
// 示例 1：
//
//
//输入：buckets = 1000, minutesToDie = 15, minutesToTest = 60
//输出：5
//
//
// 示例 2：
//
//
//输入：buckets = 4, minutesToDie = 15, minutesToTest = 15
//输出：2
//
//
// 示例 3：
//
//
//输入：buckets = 4, minutesToDie = 15, minutesToTest = 30
//输出：2
//
//
//
//
// 提示：
//
//
// 1 <= buckets <= 1000
// 1 <= minutesToDie <= minutesToTest <= 100
//
// Related Topics 数学
// 👍 159 👎 0

/**
 * lc.458 可怜的小猪
 * 这种稀奇古怪的题，而且是给你两个变量求第三个变量，那么要考虑数学方法
 * 关键词：数学，逆向思维，化简
 * 思路：已知变量 r 表示测试几轮，n表示被测试的桶数量，求"最小"测试工具数量x
 * 既然是求最小x，那么逆向思考，要找出r和x固定时能测试的最大桶数量
 * （试过正向思维，让n固定，那么要x从1开始逐渐增加，结果没有发现规律）
 * 比如：r=1 x=1 max(n)=2, r=2 x=1 max(n)=3,r=3 x=1 max(n)=4
 *      r=1 x=2 max(n)=4, r=2 x=2 max(n)=9,r=3 x=2 max(n)=16
 * 通过举例发现规律，一只猪在r轮实验后，有r+1个状态，两只猪有(r+1)*(r+1)个状态
 *  那么 max(n)=pow(r+1,x)
 * 化简情况，只有2只猪，4个桶，在实际执行时，要把桶放到n*n的矩阵中（两只猪是二维矩阵，三只是三维矩阵，，）
 * 一只按行喝水，一只按列喝水，可以一次求解
 *
 */
public class PoorPig {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int r = minutesToTest/minutesToDie;
        int num = 0,sum=1;
        //让num递增，满足条件就返回结果
        while (true){
            if(sum>=buckets){
                break;
            }
            sum *= (r+1);
            num+=1;
        }
        return num;
    }
}
