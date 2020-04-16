import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while (t-- != 0) {
			int n = in.nextInt();
			int cnt = 0;
			for (int w = n / 4; w >= 0; --w)
				for (int x = n / 3; x >= 0; --x)
					for (int y = n / 2; y >= 0; --y) {
						// z不需要枚举，可以算出来，否则会超时
						int z = n - 4 * w - 3 * x - 2 * y;
						if (z >= 0)
							cnt++;
					}
			System.out.println(cnt);
		}
	}
}
