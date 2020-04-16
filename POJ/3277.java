import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	static Scanner in = new Scanner(System.in);

	static int[][] rawData;
	static int[] discretization;
	NODE[] data;

	public static void main(String[] args) {
		int numbers = in.nextInt();
		rawData = new int[numbers][3];
		for (int i = 0; i < numbers; ++i) {
			rawData[i][0] = in.nextInt();
			rawData[i][1] = in.nextInt();
			rawData[i][2] = in.nextInt();
		}
		discretize();
		SegmentTree st = new Main().new SegmentTree(discretization.length - 1);
		for (int i = 0; i < numbers; ++i) {
			st.newBuilding(rawToDiscretized(rawData[i][0]), rawToDiscretized(rawData[i][1]), rawData[i][2], 1);
		}
		System.out.println(st.getAnswer());

	}

	private static void discretize() {
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < rawData.length; ++i) {
			for (int j = 0; j < 2; ++j) {
				set.add(rawData[i][0]);
				set.add(rawData[i][1]);
			}
		}
		discretization = new int[set.size() + 1];
		int cnt = 0;
		for (int i : set) {
			discretization[++cnt] = i;
		}
		Arrays.sort(discretization);
	}

	public static int rawToDiscretized(int raw) {
		return Arrays.binarySearch(discretization, raw);
	}

	public static int discretizedToRaw(int discretized) {
		return discretization[discretized];
	}

	class SegmentTree {

		public SegmentTree(int length) {
			data = new NODE[length * 4];
			build(1, 1, length);
		}

		private boolean isPrimarySegment(int left, int right) {
			return left + 1 == right;
		}

		public void build(int index, int left, int right) {

			NODE node = null;
			if (isPrimarySegment(left, right)) {
				node = new NODE(index, left, right);
			} else {
				build(index * 2, left, (left + right) >>> 1);
				build(index * 2 + 1, (left + right) >>> 1, right);
				node = new NODE(index, left, right);
			}
			data[index] = node;
		}

		public void newBuilding(int from, int to, int height, int index) {
			NODE node = data[index];
			if (node.isCompletelyCovered(from, to)) {
				node.update(height);
				return;
			}
			if (to > node.getMid()) {
				newBuilding(from, to, height, node.getRightChildIndex());
			}
			if (from < node.getMid()) {
				newBuilding(from, to, height, node.getLeftChildIndex());
			}
		}

		public long getAnswer() {
			return data[1].getValue();
		}

	}

	class NODE {
		private int index = 0;
		private int left = 0;
		private int right = 0;
		private long height = 0;

		public NODE(int index, int left, int right) {
			this.index = index;
			this.left = left;
			this.right = right;
		}

		public int getRange() {
			return discretizedToRaw(right) - discretizedToRaw(left);
		}

		public int getMid() {
			return (left + right) >>> 1;
		}

		public long getValue() {
			return calculate(0);
		}

		public long calculate(long height) {
			update(height);
			if (isPrimary()) {
				return this.height * getRange();
			} else {
				NODE leftChild = data[getLeftChildIndex()];
				NODE rightChild = data[getRightChildIndex()];
				return (leftChild.calculate(this.height)) + (rightChild.calculate(this.height));
			}

		}

		public boolean isCompletelyCovered(int from, int to) {
			return from <= left && to >= right;
		}

		public int getLeftChildIndex() {
			return index * 2;
		}

		public int getRightChildIndex() {
			return index * 2 + 1;
		}

		public void update(long height) {
			if (height > this.height) {
				this.height = height;
			}

		}

		public boolean isPrimary() {
			return right - left == 1;
		}
	}

}
