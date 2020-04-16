import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		if (s.toLowerCase().equals(s) || s.toUpperCase().equals(s)) {
			System.out.println("Yes");
		} else if (s.substring(0, 1).toUpperCase().equals(s.substring(0, 1)) && s.substring(1).toLowerCase().equals(s.substring(1))) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}

}
