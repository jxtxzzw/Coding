import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		in.nextLine();
		String regex = "[a-zA-Z_][a-zA-Z0-9_]*";
		Pattern p = Pattern.compile(regex);
		while (in.hasNext()) {
			String input = in.nextLine();
			Matcher m = p.matcher(input);
			if (m.matches())
				System.out.println("yes");
			else
				System.out.println("no");
		}
	}

}
