
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		in.nextLine();
		while (in.hasNextLine()) {
			String s = in.nextLine();
			while (s.replaceAll("01", "").length() < s.length() || s.replaceAll("10", "").length() < s.length()) {
				s = s.replaceAll("01", "");
				s = s.replaceAll("10", "");
			}
			System.out.println(s.length());
		}

	}
}
