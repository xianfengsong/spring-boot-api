package dp;

/**
 * 买卖股票最佳时机（一次交易）
 */
public class SellStock1 {
    /**
     * 一次遍历
     * 从前向后遍历，保存当前遇到的最小值（避免重复计算）和当前的最大盈利 O(N) 这个没想到？
     *
     * @param prices
     * @return
     */
    public int maxProfit(int prices[]) {
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < min) {
                min = price;
            } else {
                maxProfit = Math.max(maxProfit, price - min);
            }
        }
        return maxProfit;
    }
}
