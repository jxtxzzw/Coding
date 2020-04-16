import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		while (t-- != 0) {
			String input = in.next();
			String rawRegex = in.next();
			rawRegex = rawRegex.replaceAll("(.\\*)\\1{1,}", "$1");
			String[] s = rawRegex.split("\\.\\*");
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < s.length; i++) {
				sb.append(s[i]);
				if (i < s.length - 1)
					sb.append("(.)\\" + (i + 1) + "*");
			}
			if (rawRegex.endsWith(".*"))
				sb.append("(.)\\" + (Math.max(s.length, 1)) + "*");
			String regex = sb.toString();
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(input);
			System.out.println(m.matches() ? "yes" : "no");
		}
	}

}