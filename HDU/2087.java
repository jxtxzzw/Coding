/**
 * 很简单的一道题
 * 就是看字符串s里面有多少个t
 * 用的是replace
 * 注意因为字符的范围是所有的ASCII
 * 所以不可以用replaceAll
 * 
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s;
		while (!(s = in.next()).equals("#")) {
			String t = in.next();
			System.out.println((s.length() - s.replace(t, "").length()) / (t.length()));
		}

	}
}
