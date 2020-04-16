import java.util.Scanner;

public class Main {

	private static final int POSSIBLE_MAXIMUM_AandB = 33;
	private static int numberOfRecords;
	private static int sizeOfEachRecordInArrayP;
	private static int sizeOfEachRecordInArrayQ;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			numberOfRecords = in.nextInt();
			sizeOfEachRecordInArrayP = in.nextInt();
			sizeOfEachRecordInArrayQ = in.nextInt();
			Long A = null;
			Long B = null;
			Long K = null;
			for (long tryingA = 0; tryingA < POSSIBLE_MAXIMUM_AandB; tryingA++) {
				for (long tryingB = 0; tryingB < POSSIBLE_MAXIMUM_AandB; tryingB++) {
					if (isValidForEachRecordInArrayQ(tryingA, tryingB)) {
						long tryingK = memoryUsageOfArrayQ(tryingA, tryingB);
						if (K == null || tryingK < K) {
							K = tryingK;
							A = tryingA;
							B = tryingB;
						}
					}
				}
			}
			System.out.println(K + " " + A + " " + B);
		}
	}

	private static long memoryUsageOfArrayQ(long tryingA, long tryingB) {
		return byteOffsetOfIthRecordInArrayQWithNewFormula(numberOfRecords - 1, tryingA, tryingB) + sizeOfEachRecordInArrayQ;
	}
	/**
	 * 这里我犯下了一个大错
	 * 导致一直WA
	 * 最后是柳天奕帮忙看出来的错误
	 * 
	 * 注意record是0到n-1
	 * 所以要保证的就是0..n-1的每一个i都满足
	 * 那么memoryUsage就是offset(numberOfRecords-1)+sizeOfEachRecordInArrayQ
	 * 而不是offset(numberOfRecords)
	 * 
	 * 这是因为我只要保证了offset(numberOfRecords-1)满足式子
	 * 加上sizeOfEachRecordInArrayQ就是整个占用的内存
	 * 而这个后面其实还是会空出一段空间才是offset(numberOfRecords)
	 * 所以offset(numberOfRecords)会大于等于memoryUsage
	 * 有时候是对的，是因为正好是0
	 * 有时候差1是因为正好中间的间隔是1
	 * 但是有时就会差很多
	 * 
	 * 如果计算offset(numberOfRecords)的话不知道要减去多少的
	 * 只能用offset(numberOfRecords-1)+sizeOfEachRecordInArrayQ
	 * 
	 * 另外注意到
	 * 其实interval是固定的
	 * 对于原始的数组来说，sq+(interval=0)+sq+(interval=0)...
	 * 而对于新数组，sq+(interval=Q)+sq+(interval=Q)...
	 * 其中Q是一个确定的常数
	 * 也就是说，对于确定的A和B，Q是不会变的
	 * 因此只要保证了offset(n-1)大于等于n*sq
	 * 就可以保证新数组所有的interval=Q这个常数大于等于0
	 * 也就是不会出现Overlap
	 * 
	 * 所以其实整个程序直接遍历A和B两层循环
	 * 后面直接判断Q(n-1)>=n*sq&&Q(n-1)<best就可以了
	 * 
	 */
	
	// 另外
	// 如果这道题用C/C++来写
	// 注意Linux判题下面应该要用%lld输出long long
	// 而不能用%I64d
	// 柳天奕用%I64d输出拿了2个Presentation Error以后才发现
	// 改成%lld马上就AC了

	/**
	 * Satisfy each i-th record in (0..n), means non-overlapping for all
	 * 
	 */
	/*
	 * for (int recordIndex = 0; recordIndex < numberOfRecords; recordIndex++) {
	 * if (isOverlapping(recordIndex, tryingA, tryingB)) return false;
	 * return true;
	 * }
	 * 
	 */
	/**
	 * Note that the ergodic will cause TLE
	 * 
	 * Ergodic shown above is eqivalent to the following expression
	 * (2^a+1)/(2^b)>=sq/sp
	 * ^ means power operation instead of exclusive-or operation
	 * a, b, sq, sp share the same meaning in the problem description
	 * 
	 * So we can use this single expression to judge, avoiding the unnecessary ergodic
	 */
	/*
	 * 
	 * 
	 */ 
	/**
	 * Remember that this expression should be calculated with type double
	 * Otherwise sq/sp may get an int which may cause Wrong Answer
	 *
	 */
	/*
	 * (Math.pow(2, a) + 1) / Math.pow(2, b) >= (double)sizeOfEachRecordInArrayQ / (double)sizeOfEachRecordInArrayP
	 * 
	 */ 
	/**
	 * In this way, the method isOverlapping and minimumValidByteOffsetOfIthRecordInArrayQ called in ergodic are useless
	 * 
	 */
	/*
	 * private static boolean isOverlapping(int recordIndex, int tryingA, int tryingB) {
	 * return byteOffsetOfIthRecordInArrayQWithNewFormula(recordIndex, tryingA,
	 * tryingB) < minimumValidByteOffsetOfIthRecordInArrayQ(recordIndex);
	 * }
	 * private static int minimumValidByteOffsetOfIthRecordInArrayQ(int recordIndex) {
	 * return byteOffsetOfIthRecordInArrayWithTraditionalFormula(recordIndex, sizeOfEachRecordInArrayQ);
	 * }
	 * 
	 */
	/**
	 * A direct check with the maximum index of records (which means the number of records)
	 * can guarantee the result
	 * We can compare the memory usage of array Q and the minimum valid memory usage of array Q
	 * 
	 */
	
	private static boolean isValidForEachRecordInArrayQ(long tryingA, long tryingB) {
//		System.out.println(memoryUsageOfArrayQ(tryingA, tryingB) + ">=?" + minimumValidMemoryUsageOfArrayQ());
		return memoryUsageOfArrayQ(tryingA, tryingB) >= minimumValidMemoryUsageOfArrayQ();
	}

	private static long minimumValidMemoryUsageOfArrayQ() {
		return byteOffsetOfIthRecordInArrayWithTraditionalFormula(numberOfRecords, sizeOfEachRecordInArrayQ);
	}

	private static long byteOffsetOfIthRecordInArrayQWithNewFormula(int recordIndex, long tryingA, long tryingB) {
		return  (long) ((byteOffsetOfIthRecordInArrayP(recordIndex)
				+ byteOffsetOfIthRecordInArrayP(recordIndex) * (long) Math.pow(2, tryingA)) / (long) Math.pow(2, tryingB));
	}

	private static long byteOffsetOfIthRecordInArrayP(int recordIndex) {
		return byteOffsetOfIthRecordInArrayWithTraditionalFormula(recordIndex, sizeOfEachRecordInArrayP);
	}

	private static long byteOffsetOfIthRecordInArrayWithTraditionalFormula(int recordIndex, int sizeOfEachRecord) {
		return recordIndex * sizeOfEachRecord;
	}
}
