import java.util.Scanner;

public class Main {
	int[][] a = new int[1007][1007];

	/**
	 * @param a
	 * @param b
	 */
	public Main(int[][] a, int[][] b) {
		this.a = a;
		this.b = b;
	}

	int[][] b = new int[1007][1007];

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);

		int m = in.nextInt();
		int n = in.nextInt();
		int h = in.nextInt();

		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				a[i][j] = in.nextInt();

		b[0][0] = h + a[0][0];
		for (int i = 1; i < m; i++)
			b[i][0] = b[i - 1][0] >= 0 ? b[i - 1][0] + a[i][0] : -1;
		for (int i = 1; i < n; i++)
			b[0][i] = b[0][i - 1] >= 0 ? b[0][i - 1] + a[0][i] : -1;

		for (int i = 1; i < m; i++)
			for (int j = 1; j < n; j++) {
				if (b[i - 1][j] < 0 && b[i][j - 1] >= 0) {
					b[i][j] = b[i][j - 1] + a[i][j];
				} else if (b[i - 1][j] >= 0 && b[i][j - 1] < 0) {
					b[i][j] = b[i - 1][j] + a[i][j];
				} else if (b[i - 1][j] >= 0 && b[i][j - 1] >= 0) {
					b[i][j] = (b[i - 1][j] > b[i][j - 1] ? b[i - 1][j] : b[i][j - 1]) + a[i][j];
				} else if (b[i - 1][j] < 0 && b[i][j - 1] < 0) {
					b[i][j] = -1;
				}

			}

		System.out.println(b[m - 1][n - 1] >= 0 ? b[m - 1][n - 1] : -1);

	}

}
