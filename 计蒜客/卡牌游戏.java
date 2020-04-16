import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] num = new int[n + 1];
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			num[i] = in.nextInt();
		}
		for (int i = 2; i < n; i++) {
			dp[i][i] = num[i - 1] * num[i] * num[i + 1];
		}
		for (int length = 2; length <= n - 2; length++) {
			for (int i = 2; i <= n - 1 - length + 1; i++) {
				int j = i + length - 1;
				dp[i][j] = 0x3F3F3F3F;
				for (int k = i; k <= j; k++) {
					int x = dp[i][k - 1] + num[i - 1] * num[k] * num[j + 1] + dp[k + 1][j];
					dp[i][j] = Math.min(dp[i][j], x);
				}
			}
		}
		System.out.println(dp[2][n - 1]);
	}
}
