
/**
 * 注意到10^9和-10^9是包含的
 * （这是一个闭区间）
 * 所以我这里的POSSIBLE_SCOPE_OF_DATA取了10^9+1
 * 
 * 用二分法猜数字
 * 
 * 这里注意其实(a+b)/2的写法不是很好
 * 因为(a+b)/2可能会引起溢出
 * 比如a=2147483647且b=2147483645
 * (a+b)/2溢出为负数而不是2147483646
 * 正确的写法是无符号位移运算
 * 即逻辑位移
 * 也就是(a+b)>>>1
 * 
 * 不过我写(a+b)/2也能AC
 * 
 * 注意交互题用的是System.out.flush();
 * 
 * 我本来以为要动用什么synchronized
 * 其实只要System.out去flush一下
 * 
 */

import java.util.Scanner;

public class Main {

	private final static int POSSIBLE_SCOPE_OF_DATA = 1000000001;
	private final static String BIGGER_THAN_THE_ANSWER = "big";
	private final static String SMALLER_THAN_THE_ANSWER = "small";
	private final static String GET_THE_ACCURATE_ANSWER = "euqal";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long leftEndPoint = -POSSIBLE_SCOPE_OF_DATA;
		long rightEndPoint = POSSIBLE_SCOPE_OF_DATA;
		long guessingNumber = (leftEndPoint + rightEndPoint) / 2;
		System.out.println(guessingNumber);
		System.out.flush();
		String replyOfInteractiveProgram;
		do {
			replyOfInteractiveProgram = in.nextLine();
			switch (replyOfInteractiveProgram) {
			case BIGGER_THAN_THE_ANSWER:
				rightEndPoint = guessingNumber;
				break;
			case SMALLER_THAN_THE_ANSWER:
				leftEndPoint = guessingNumber;
				break;
			case GET_THE_ACCURATE_ANSWER:
				break;
			}
			guessingNumber = (leftEndPoint + rightEndPoint) / 2;
			System.out.println(guessingNumber);
			System.out.flush();
		} while (!replyOfInteractiveProgram.equals(GET_THE_ACCURATE_ANSWER));
	}

}
