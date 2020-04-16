import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int p = in.nextInt();
		int[] a = new int[6];
		int[] b = new int[6];
		for (int i = 0; i < a.length; ++i) {
			a[i] = in.nextInt();
		}
		for (int i = 0; i < b.length; ++i) {
			b[i] = in.nextInt();
		}
		int[] P = new int[p];
		for (int i = 0; i < a.length; ++i) {
			for (int j = 0; j < b.length; ++j) {
				int x = (a[i] + b[j]) % p;
				++P[x];
			}
		}
		for (int i = 1; i < p; ++i) {
			if (P[i] != P[i - 1]) {
				System.out.println("NO");
				System.exit(0);
			}
		}
		System.out.println("YES");

	}

}
