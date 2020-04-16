
import java.util.Scanner;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String s = in.next();
			StringBuffer sb = null;
			int max = -1;
			for (int beginIndex = 0; beginIndex <= s.length(); beginIndex++)
				for (int endIndex = beginIndex; endIndex <= s.length(); endIndex++) {
					sb = new StringBuffer(s.substring(beginIndex, endIndex));

					if (sb.toString().equals(sb.reverse().toString())) {
						max = max > sb.length() ? max : sb.length();
					}
				}
			System.out.println(max);
		}

	}

}
