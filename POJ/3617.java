import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; ++i) {
			String s = in.next();
			sb.append(s);
		}

		StringBuffer reversed = new StringBuffer(sb);
		reversed.reverse();

		StringBuffer t = new StringBuffer();

		while (sb.length() > 0) {
			if (sb.toString().compareTo(reversed.toString()) < 0) {
				t.append(sb.charAt(0));
				sb.deleteCharAt(0);
				reversed.deleteCharAt(reversed.length() - 1);
			} else {
				t.append(reversed.charAt(0));
				reversed.deleteCharAt(0);
				sb.deleteCharAt(sb.length() - 1);
			}
			// System.out.println(sb + ":" + reversed + "=" + t);
		}

		for (int i = 1; i <= t.length(); ++i) {
			System.out.print(t.charAt(i - 1));
			if (i % 80 == 0)
				System.out.println();
		}
		System.out.println();

	}

}