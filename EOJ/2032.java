import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		while (in.hasNextBigDecimal()) {
			BigDecimal a = in.nextBigDecimal();
			BigDecimal b = in.nextBigDecimal();
			if (a.compareTo(b) == 0) {
				System.out.println("It's xiao qiang");
			} else {
				System.out.println("It isn't xiao qiang");
			}
		}

	}
}
