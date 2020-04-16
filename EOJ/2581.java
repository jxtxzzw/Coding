import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		while (n-- != 0) {
			int x = in.nextInt();
			String s = Integer.toBinaryString(x);
			int cntZero = 0;
			for (int i = 0; i < s.length(); ++i)
				if (s.charAt(i) == '0')
					cntZero++;
			System.out.println(cntZero + " " + (s.length() - cntZero));
		}

	}
}
