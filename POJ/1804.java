import java.util.Scanner;

public class Main {

	static int length;
	static int[] dataArray;
	static int answer = 0;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		int caseNumber = in.nextInt();
		
		for (int caseIndex = 1; caseIndex <= caseNumber; caseIndex++) {
			length = in.nextInt();
			dataArray = new int[length];

			for (int i = 0; i < dataArray.length; i++) 
				dataArray[i] = in.nextInt();

			answer = 0;
			
			mergeSort(0, dataArray.length - 1);

			System.out.printf("Scenario #%d:\n%d\n\n", caseIndex, answer);
		}

	}

	private static void mergeSort(int leftEndPoint, int rightEndPoint) {
		if (leftEndPoint < rightEndPoint) {
			int middlePoint = (leftEndPoint + rightEndPoint) >>> 1;
			mergeSort(leftEndPoint, middlePoint);
			mergeSort(middlePoint + 1, rightEndPoint);
			merge(leftEndPoint, middlePoint, rightEndPoint);
		}
	}

	private static void merge(int leftEndPoint, int middlePoint, int rightEndPoint) {
		int leftCursor = leftEndPoint;
		int rightCursor = middlePoint + 1;
		int ancillaryCursor = leftEndPoint;
		int[] ancillaryArray = new int[dataArray.length];
		while (leftCursor <= middlePoint && rightCursor <= rightEndPoint) {
			if (dataArray[leftCursor] <= dataArray[rightCursor]) {
				ancillaryArray[ancillaryCursor] = dataArray[leftCursor];
				++leftCursor;
			} else {
				answer += (middlePoint - leftCursor + 1); // 核心代码就只有这一句，其他都是归并排序
				ancillaryArray[ancillaryCursor] = dataArray[rightCursor];
				++rightCursor;
			}
			++ancillaryCursor;
		}
		while (leftCursor <= middlePoint) {
			ancillaryArray[ancillaryCursor] = dataArray[leftCursor];
			++leftCursor;
			++ancillaryCursor;

		}
		while (rightCursor <= rightEndPoint) {
			ancillaryArray[ancillaryCursor] = dataArray[rightCursor];
			++rightCursor;
			++ancillaryCursor;

		}
		for (int i = leftEndPoint; i <= rightEndPoint; ++i) 
			dataArray[i] = ancillaryArray[i];
	}
}
