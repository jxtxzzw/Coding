import java.util.Scanner;

/*
 * C语言程序设计基础
 * 竟然有概率DP
 * 
 */

public class Main {

	static final int MAXN = 2007;
	static final int DICE_POLYHEDRAL = 6;
	static final double probability = (double) 1 / (double) DICE_POLYHEDRAL;
	static double[][] dp = new double[MAXN][MAXN * DICE_POLYHEDRAL];

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int n = in.nextInt();
			int k = in.nextInt();
			getAllResult(n);
			if (k > 6 * n)
				System.out.println((double) 0);
			else
				System.out.println(dp[n][k]);
		}
	}

	private static void getAllResult(int n) {
		for (int i = 1; i <= DICE_POLYHEDRAL; ++i) {
			dp[1][i] = probability;
		}
		for (int i = 2; i <= n; ++i) {
			for (int j = 0; j <= DICE_POLYHEDRAL * n; ++j) {
				for (int t = 1; t <= Math.min(DICE_POLYHEDRAL, j); ++t) {
					dp[i][j] += dp[i - 1][j - t];
				}
				dp[i][j] *= probability;
			}
		}
	}
}
