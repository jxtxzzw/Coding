class Solution {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        final int MOD = 1000000007;
        int[][][] dp = new int[m][n][maxMove];
        for (int k = 0; k < maxMove; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int t = 0, l = 0, r = 0, b = 0;
                    // CRITICAL: 如果已经出界，那么是加上 1 而不是 0
                    // 不需要加上 dp[i][j][k - 1]
                    if (k == 0) {
                        t = (i == 0) ? 1 : 0;
                        l = (j == 0) ? 1 : 0;
                        r = (j == n - 1) ? 1 : 0;
                        b = (i == m - 1) ? 1 : 0;
                    } else {
                        t = (i - 1 >= 0) ? dp[i - 1][j][k - 1] : 1;
                        l = (j - 1 >= 0) ? dp[i][j - 1][k - 1] : 1;
                        r = (j + 1 < n) ? dp[i][j + 1][k - 1] : 1;
                        b = (i + 1 < m) ? dp[i + 1][j][k - 1] : 1;
                    }
//                    dp[i][j][k] = (l + r + t + b) % MOD;
                    int d = 0;
                    d = (d + t) % MOD;
                    d = (d + l) % MOD;
                    d = (d + r) % MOD;
                    d = (d + b) % MOD;
                    dp[i][j][k] = d;
                }
            }
        }
        return maxMove == 0 ? 0 : dp[startRow][startColumn][maxMove - 1];
    }
}