import java.util.Scanner;

/*
 * 就是求Nim-Sum是不是0
 */

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int caseNumber = in.nextInt();
		while (caseNumber-- != 0) {
			int numberOfHeaps = in.nextInt();
			int[] stones = new int[numberOfHeaps];
			for (int i = 0; i < numberOfHeaps; ++i) {
				stones[i] = in.nextInt();
			}
			System.out.println(isPPosition(stones) ? "Lost" : "Win");
		}
	}

	/**
	 * 判断给定的数组是不是P-Position的
	 * @param stones 数组
	 * @return 如果是，返回true，否则，false
	 */
	private static boolean isPPosition(int[] stones) {
		// 求Nim-Sum
		int nimSum = 0;
		// 遍历数组元素，求每个异或
		for (int x : stones) {
			nimSum = nimSum ^ x;
		}
		return nimSum == 0;
		/*
		 * Bouton定理
		 * 在Nim游戏中
		 * 状态(x1, x2, x3)为P状态
		 * 当且仅当x1 ⊕ x2 ⊕ x3=0
		 * 即x1, x2, x3的Nim-Sum等于0
		 */
	}

}
