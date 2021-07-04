class Solution {
    int a = 0, e = 1, i = 2, o = 3, u = 4;
    int MOD = 1000000007;

    public int countVowelPermutation(int n) {
        int[][] dp = new int[n + 1][5];

        // Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
        for (int j = 0; j < 5; j++) {
            dp[1][j] = 1;
        }

        for (int len = 2; len <= n; len++) {
            // Each vowel 'a' may only be followed by an 'e'.
            dp[len][a] = dp[len - 1][e];
            // Each vowel 'e' may only be followed by an 'a' or an 'i'.
            dp[len][e] = (int)(((long)dp[len - 1][a] + dp[len - 1][i]) % MOD);
            // Each vowel 'i' may not be followed by another 'i'.
            dp[len][i] = (int)(((long)dp[len - 1][a] + dp[len - 1][e] + dp[len - 1][o] + dp[len - 1][u]) % MOD);
            // Each vowel 'o' may only be followed by an 'i' or a 'u'.
            dp[len][o] = (int)(((long)dp[len - 1][i] + dp[len - 1][u]) % MOD);
            // Each vowel 'u' may only be followed by an 'a'.
            dp[len][u] = dp[len - 1][a];
        }

        return (int)(((long)dp[n][a] + dp[n][e] + dp[n][i] + dp[n][o] + dp[n][u]) % MOD);
    }
}
