import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int caseNumber = in.nextInt();
		while (caseNumber-- != 0) {
			int LBB = in.nextInt();
			int JDG = in.nextInt();
			System.out.printf("LBB made %s coffee today.\n",
					LBB == 0 ? "no" : LBB == 1 ? "a cup of" : (LBB + " cups of"));
			System.out.printf("JDG made %s coffee today.\n",
					JDG == 0 ? "no" : JDG == 1 ? "a cup of" : (JDG + " cups of"));
			System.out.printf("BOSS had %s coffee today.\n",
					(LBB + JDG) == 0 ? "no" : (LBB + JDG) == 1 ? "a cup of" : ((LBB + JDG) + " cups of"));
		}
	}

}
