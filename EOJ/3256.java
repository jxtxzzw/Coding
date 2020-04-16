import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int caseNumber = in.nextInt();
		in.nextLine();
		for (int t=1; t<=caseNumber;++t) {
			String ans = null;
			String s = in.nextLine();
			if (s.charAt(s.length()-1)<='4') {
				int replaceAt = 0;
				char replaceWith = 0;
				if (s.contains("a")) {
					replaceAt = s.indexOf('a');
					switch(s.charAt(s.length()-1)) {
					case '1':
						replaceWith = 'ā';
						break;
					case '2':
						replaceWith = 'á';
						break;
					case '3':
						replaceWith = 'ǎ';
						break;
					case '4':
						replaceWith = 'à';
						break;
					}
				} else if (s.contains("o")){
					replaceAt = s.indexOf('o');
					switch(s.charAt(s.length()-1)) {
					case '1':
						replaceWith = 'ō';
						break;
					case '2':
						replaceWith = 'ó';
						break;
					case '3':
						replaceWith = 'ǒ';
						break;
					case '4':
						replaceWith = 'ò';
						break;
					}
				} else if (s.contains("e")) {
					replaceAt = s.indexOf('e');
					switch(s.charAt(s.length()-1)) {
					case '1':
						replaceWith = 'ē';
						break;
					case '2':
						replaceWith = 'é';
						break;
					case '3':
						replaceWith = 'ě';
						break;
					case '4':
						replaceWith = 'è';
						break;
					}
				} else if (s.contains("v")) {
					replaceAt = s.indexOf('v');
					switch(s.charAt(s.length()-1)) {
					case '1':
						replaceWith = 'ǖ';
						break;
					case '2':
						replaceWith = 'ǘ';
						break;
					case '3':
						replaceWith = 'ǚ';
						break;
					case '4':
						replaceWith = 'ǜ';
						break;
					}
				}
				else {
					int a = s.indexOf('u');
					int b = s.indexOf('i');
					replaceAt = Math.max(a, b);
					if (s.charAt(replaceAt)=='u') {
						switch(s.charAt(s.length()-1)) {
						case '1':
							replaceWith = 'ū';
							break;
						case '2':
							replaceWith = 'ú';
							break;
						case '3':
							replaceWith = 'ǔ';
							break;
						case '4':
							replaceWith = 'ù';
							break;
						}
					} else {
						switch(s.charAt(s.length()-1)) {
						case '1':
							replaceWith = 'ī';
							break;
						case '2':
							replaceWith = 'í';
							break;
						case '3':
							replaceWith = 'ǐ';
							break;
						case '4':
							replaceWith = 'ì';
							break;
						}
					}
				
				}
				char[] c = s.toCharArray();
				c[replaceAt] = replaceWith;
				ans = String.copyValueOf(c, 0, s.length()-1);
			} else {
				ans = s;
			}
			System.out.printf("Case %d: %s\n",t,ans);
		}

	}

}
