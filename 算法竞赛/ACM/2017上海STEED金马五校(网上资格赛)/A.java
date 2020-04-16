import java.util.Scanner;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String s = null;
		String tmp = null;
		while (in.hasNext()) {
			int cnt = -1;
			s = in.nextLine();
			tmp = s;
			do {
				s = tmp;
				tmp = s.replace("()", "");
				// System.out.println(tmp);
				cnt++;
			} while (!s.equals(tmp) && tmp != null);
			if (tmp.equals("")) {
				System.out.println("YES " + cnt);
			} else {
				System.out.println("NO");
			}
		}
	}

}
