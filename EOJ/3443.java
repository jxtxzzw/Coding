import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int sec = in.nextInt();
		int hh = sec / 3600;
		int mm = (sec % 3600) / 60;
		int ss = sec % 60;
		System.out.println(hh * 10000 + mm * 100 + ss);
	}
}
