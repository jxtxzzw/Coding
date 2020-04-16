import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cnt = 0;
		while (in.hasNextInt()) {
			int n = in.nextInt();
			long k = in.nextLong();
			if (k <= n) {
				System.out.println("Case #" + (++cnt) + ": " + k);
			} else {
				k -= n;
				k %= (2 * n - 2);
				if (k == 0)
					k = (2 * n - 2);
				// Notice that if k equals to 0 then k should be (2*n-2)
				if (k <= n - 1) {
					System.out.println("Case #" + (++cnt) + ": " + k);
				} else {
					k -= (n - 1);
					if (k == n - 1)
						k++;
					System.out.println("Case #" + (++cnt) + ": " + k);
				}
			}
		}

	}

}