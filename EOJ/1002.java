import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		while (n-- != 0) {
			StringBuffer sb = new StringBuffer(in.next());
			for (int i = 0; i < 4; ++i) {
				System.out.print(Integer.parseInt(sb.substring(8 * i, 8 * (i + 1)), 2));
				if (i != 3)
					System.out.print('.');
				else
					System.out.println();
			}
		}
	}
}
