import java.util.Scanner;

/**
 * @author jxtxzzw
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int caseNumber = in.nextInt();
		for (int t = 0; t < caseNumber; t++) {
			int problemNumber = in.nextInt();
			// 事实上，这个就是String.length()，没有实际作用
			int scoreDerek = in.nextInt();
			int scoreAlfia = in.nextInt();
			String answerDerek = in.next();
			String answerAlfia = in.next();
			int wrongCounter = 0;
			int rightCounter = 0;
			for (int i = 0; i < answerDerek.length(); i++) {
				if (answerDerek.charAt(i) != answerAlfia.charAt(i))
					wrongCounter++;
				else
					rightCounter += 2;
			}
			if (Math.abs(scoreDerek - scoreAlfia) > wrongCounter) {
				System.out.println("Lying");
				// 如果两个人有x个答案不一样，那么最多两个人差x分，也可能差0分、差1分……差x-1分
			} else if (rightCounter + wrongCounter < scoreDerek + scoreAlfia) {
				System.out.println("Lying");
				// 两个人的答案一样，各得1分，答案不一样，最多只能有1个人得1分，也可能两个人都不得分
				// 所以错误答案+正确答案*2就是最多能多少分，如果两个人说出的分数加起来大于了这个分数，就是说谎
			} else {
				System.out.println("Not lying");
			}
		}

	}

}
