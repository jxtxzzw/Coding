import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int studentNumber = in.nextInt();
		int[] studentScore = new int[studentNumber];
		int[] studentRank = new int[studentNumber];
		for (int i = 0; i < studentNumber; i++)
			studentScore[i] = in.nextInt();
		for (int i = 0; i < studentNumber; i++) {
			for (int j = 0; j < studentNumber; j++) {
				if (studentScore[i] < studentScore[j])
					studentRank[i]++;
				// 统计成绩排名，直接看有多少人比自己Strictly高
				else if (i != j && studentScore[i] == studentScore[j] && studentRank[j] != 0) {
					studentRank[i] = studentRank[j];
					break;
					// 可以剪枝
				}
			}
		}
		for (int i = 0; i < studentNumber; i++) {
			System.out.printf("%d%c", studentRank[i] + 1, i == studentNumber - 1 ? '\n' : ' ');
		}
	}
}
