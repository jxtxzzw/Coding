/**
 * 晚上在LTY的邀请下一起刷了一道UVa的题509
 * LTY说是水题
 * 但是其实理解题意花了很长的时间
 * 这道题难就难在理解题目意思上面
 * 只要理解题目意思
 * 一步步做还是很容易想到的
 * 不过写起来也不是那么容易的
 * 需要及其强大的清晰的逻辑才能一次成型
 * 要不然的话Debug的时候真的是要崩溃的
 * 
 * 需要注意的只有1个点
 * 那就是校验块的判断
 * (col%numberOfDisks)==(row)是校验块
 * 其他的部分只要用标准的编程思想去实现
 * 一次通过不难
 * 
 */
import java.util.Scanner;

public class Main {

	static char[][] raid;
	static int numberOfDisks;
	static int sizeOfEachBlockInBits;
	static int totalNumberOfDataAndParityBlocksOnEachDisk;
	static String verticalParity;

	final static String EVEN_PARITY = "E";
	final static String ODD_PARITY = "O";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int diskCounter = 0;
		while ((numberOfDisks = in.nextInt()) != 0) {
			sizeOfEachBlockInBits = in.nextInt();
			totalNumberOfDataAndParityBlocksOnEachDisk = in.nextInt();
			in.nextLine();
			verticalParity = in.nextLine();
			raid = new char[numberOfDisks][sizeOfEachBlockInBits * totalNumberOfDataAndParityBlocksOnEachDisk];
			for (int diskIndex = 0; diskIndex < numberOfDisks; diskIndex++)
				raid[diskIndex] = in.nextLine().toCharArray();

			if (isValidRaid()) {
				System.out.printf("Disk set %d is valid, contents are: ", ++diskCounter);
				printContentsFromBinaryToHexadecimal(getBinaryContents());
			} else
				System.out.printf("Disk set %d is invalid.\n", ++diskCounter);
		}
	}

	private static void printContentsFromBinaryToHexadecimal(StringBuffer binaryContents) {
		for (int charIndex = 0; charIndex < binaryContents.length() / 4; charIndex++)
			System.out.printf("%X", Integer.parseInt(binaryContents.substring(charIndex * 4, (charIndex + 1) * 4), 2));
		if (binaryContents.length() % 4 == 0)
			System.out.println();
		else {
			StringBuffer incompletePart = new StringBuffer(binaryContents.substring((binaryContents.length() / 4 * 4)));
			while (incompletePart.length() != 4)
				incompletePart.append("0");
			System.out.printf("%X\n", Integer.parseInt(incompletePart.toString(), 2));
		}
	}

	private static boolean isValidRaid() {
		for (int col = 0; col < raid[0].length; col++) {
			Integer alreadyExistPosition = null;
			Integer xorResult = null;
			for (int row = 0; row < raid.length; row++)
				if (raid[row][col] == 'x')
					if (alreadyExistPosition != null)
						return false;
					else
						alreadyExistPosition = row;
				else if (xorResult == null)
					xorResult = raid[row][col] - '0';
				else
					xorResult = xorResult ^ (raid[row][col] - '0');
			if (alreadyExistPosition != null) {
				int x = verticalParity.equals(EVEN_PARITY) ? xorResult : 1 - xorResult;
				raid[alreadyExistPosition][col] = (char) (x + '0');
			} else if (xorResult != (verticalParity.equals(EVEN_PARITY) ? 0 : 1))
				return false;
		}
		return true;
	}

	private static StringBuffer getBinaryContents() {
		StringBuffer sb = new StringBuffer();
		for (int col = 0; col < totalNumberOfDataAndParityBlocksOnEachDisk; col++)
			for (int row = 0; row < numberOfDisks; row++)
				if (isNotVerticalParityBlock(col, row))
					formBitsInBlocks(sb, col, row);
		return sb;
	}

	private static void formBitsInBlocks(StringBuffer sb, int col, int row) {
		for (int i = col * sizeOfEachBlockInBits; i < (col + 1) * sizeOfEachBlockInBits; i++)
			sb.append(raid[row][i]);
	}

	private static boolean isNotVerticalParityBlock(int col, int row) {
		return (col % numberOfDisks) != (row);
		// 注意校验块的判断是(col%numberOfDisks)==(row)
		// 那么不是校验块就是(col%numberOfDisks)!=(row)
	}

}
