import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()){
			System.out.println(in.nextLine().length());
		}
	}

}
