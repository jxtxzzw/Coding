/**
 * 这是一道考察栈的题目
 * 印象中C语言应该写过类似的
 * 这里直接用了正则表达式去replaceAll
 * 注意括号是逃逸字符且反斜杠是逃逸字符
 * 
 * 另外注意不管先替换()还是[]
 * 外层都要保证到不可替换为止
 * 于是最大的循环就是s.replaceAll().length()比s.length()小
 * 
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		in.nextLine();
		while (in.hasNextLine()) {
			String s = in.nextLine();
			while (s.replaceAll("\\(\\)", "").length() < s.length()
					|| s.replaceAll("\\[\\]", "").length() < s.length()) {
				while (!s.replaceAll("\\(\\)", "").equals(s)) {
					s = s.replaceAll("\\(\\)", "");
				}
				while (!s.replaceAll("\\[\\]", "").equals(s)) {
					s = s.replaceAll("\\[\\]", "");
				}
			}
			if (s.isEmpty()) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
		}

	}
}
