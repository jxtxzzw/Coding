// 初始化 dp 数组，第一行和第一列总是只有一种走法
int[][] dp = new int[m][n];
for (int i = 0; i < m; i++) {
		dp[i][0] = 1;
}
for (int j = 0; j < n; j++) {
		dp[0][j] = 1;
}

// 其他每一个格子的走法等于它上面格子的走法加上它左面格子的走法
for (int i = 1; i < m; i++) {
		for (int j = 1; j < n; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
		}
}

System.out.println(dp[m - 1][n - 1]);