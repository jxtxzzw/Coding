import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int caseNumber = in.nextInt();
		while (caseNumber-- != 0) {
			int height = in.nextInt();
			// 原始数据
			int[][] data = new int[height + 1][height + 1];
			// DP数组，表示第i行第j列出现k是true还是false
			boolean[][][] dp = new boolean[height + 1][height + 1][10];
			for (int i = 1; i <= height; ++i) {
				for (int j = 1; j <= i; ++j) {
					data[i][j] = in.nextInt();
				}
			}
			// 边界条件
			dp[1][1][data[1][1] % 10] = true;
			// 循环遍历二维数组，动态转移方程
			for (int i = 1; i <= height; ++i) {
				for (int j = 1; j <= i; ++j) {
					for (int k = 0; k < 10; ++k) {
						if (dp[i - 1][j][k] || dp[i - 1][j - 1][k]) {
							dp[i][j][(k + data[i][j]) % 10] = true;
						}
					}
				}
			}
			// 在所有的dp[height][j][k]为true的里面找到最大的k
			int ans = -1;
			for (int j = 1; j <= height; ++j) {
				for (int k = 0; k < 10; ++k) {
					if (dp[height][j][k])
						ans = Math.max(ans, k);
				}
			}
			System.out.println(ans);
		}
	}
}

/*
 * 显然由题意
 * 只要保存每一个位置可能出现的个位数的情况
 * 然后遍历最后一行
 * 在最后一行可能出现的个位数中找到最大的那个就行了
 * 
 * dp[i][j][k]表示第i行第j列出现k为true或者false
 * 
 * 那么
 * 对于第i行第j列
 * 如果第i-1行第j列可能出现k
 * 经过这条路径到[i][j]
 * 就是(k+data[i][j])%10
 * 于是dp[i][j][(k+data[i][j])%10]=true
 * 同理
 * 如果是从第i-1行第j-1列过来的
 * 只要dp[i-1][j-1][k]是true
 * dp[i][j][(k+data[i][j])%10]=true
 * 于是循环遍历二维数组，动态转移方程
 * 
 * 最后只要在所有的dp[height][j][k]为true的里面找到最大的k
 * 
 * 考虑边界条件
 * 最上面的data[1][1]是无论如何要经过的
 * 于是dp[1][1][data[1][1]%10]=true;
 * 
 * 另外处理的时候
 * 我的下标全部是从1开始的
 * 这样的话[i-1]或者[j-1]最小就是0
 * 不会出现越界
 * 而且没有给初始值，默认就是false
 * 也不用特殊处理
 * 
 */
