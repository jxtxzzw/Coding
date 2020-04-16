import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		int d = in.nextInt();
		int y = b * d;
		int x = a * d + c * b;
		int gcd = gcd(x, y);
		x = x / gcd;
		y = y / gcd;
		System.out.println(a + "/" + b + "+" + c + "/" + d + "=" + x + "/" + y + ".");
	}

	private static int gcd(int x, int y) {
		return y == 0 ? x : gcd(y, x % y);
	}
}
