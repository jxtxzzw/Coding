import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int caseNumber = in.nextInt();
		while (caseNumber-- != 0) {
			int riceNumber = in.nextInt();
			int notDeadNumber = 0;
			while (riceNumber-- != 0) {
				if (in.nextInt() <= 10)
					notDeadNumber++;
			}
			System.out.println(notDeadNumber);
		}
	}

}
