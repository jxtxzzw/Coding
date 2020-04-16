import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int caseNumber = in.nextInt();
		while (caseNumber-- != 0) {
			int balance = in.nextInt();
			int price = in.nextInt();
			int counter = 0;
			int purchaseIn5 = balance / (5 * price);
			counter += purchaseIn5 * 7;
			balance -= purchaseIn5 * (5 * price);
			int purchaseIn3 = balance / (3 * price);
			counter += purchaseIn3 * 4;
			balance -= purchaseIn3 * (3 * price);
			int purchaseIn1 = balance / price;
			counter += purchaseIn1;
			System.out.println(counter);
		}

	}
}
