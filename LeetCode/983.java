class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int d = 365;
        int[] dp = new int[d + 1];
        int idx = days.length - 1;
        do {
            int x = 0;
            if (idx >= 0) {
                x = days[idx--];
            }
            for (int i = x; i <= d; i++) {
                dp[i] = dp[d];
            }
            int one = costs[0];
            if (x + 1 < 366) {
                one += dp[x + 1];
            }
            int seven = costs[1];
            if (x + 7 < 366) {
                seven += dp[x + 7];
            }
            int thirty = costs[2];
            if (x + 30 < 366) {
                thirty += dp[x + 30];
            }
            dp[x] = Math.min(one, Math.min(seven, thirty));
            d = x;
        } while (d >= 1);
        return dp[1];
    }
}
