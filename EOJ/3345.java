import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int myNumber = 0;
		int yourGuessingNumber = in.nextInt();
		while (yourGuessingNumber != myNumber) {
			if (yourGuessingNumber < myNumber) {
				System.out.println("small");
			} else {
				System.out.println("big");
			}
			System.out.flush();
			yourGuessingNumber = in.nextInt();
		}
		System.out.println("equal");
		System.out.flush();
	}

}
