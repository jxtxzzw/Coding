int length = s.length();
int[][] dp = new int[length][length];
for (int len = 1; len <= length; len++) {
    for (int i = 0; i + len - 1 < length; i++) {
        int j = i + len - 1;
        if (i == j) {
            dp[i][j] = 1;
        } else {
            if (s.charAt(i) == s.charAt(j)) {
                dp[i][j] = dp[i + 1][j - 1] + 2;
            } else {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
    }
}
return dp[0][length - 1];