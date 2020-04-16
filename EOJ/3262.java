import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		int n = in.nextInt();
		for (int i = 2; i <= n; i++) {
			System.out.println(i / gcd(i, x));
		}
	}

	private static int gcd(int a, int b) {
		return b > 0 ? gcd(b, a % b) : a;
	}

}