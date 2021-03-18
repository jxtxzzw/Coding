class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[] dp_if_with = new int[n];
        int[] dp_if_without = new int[n];
        dp_if_with[0] = -prices[0];
        dp_if_without[0] = 0;
        for (int i = 1; i < n; i++) {
            dp_if_with[i] = Math.max(dp_if_with[i - 1], dp_if_without[i - 1] - prices[i]);
            dp_if_without[i] = Math.max(dp_if_without[i - 1], dp_if_with[i - 1] + prices[i] - fee);
        }
        return Math.max(dp_if_with[n - 1], dp_if_without[n - 1]);
    }
}

// dp_if_with[i] 表示第 i 天时若手上有股票，最多赚多少钱；dp_if_without[i] 表示第 i 天时若手上没有股票，最多赚多少钱
// 状态转移：dp_if_with[i]，前一天手上也有股票，因为不能重复买，所以 dp_if_with[i - 1] 不变，或者前一天没有今天买入；dp_if_without 类似
