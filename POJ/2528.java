import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

	static Scanner in = new Scanner(System.in);

	static int[][] rawData;
	static int[] discretization;
	NODE[] data;

	public static void main(String[] args) {
		int caseNumber = in.nextInt();
		while (caseNumber-- != 0) {
			int numbers = in.nextInt();
			rawData = new int[numbers][2];
			for (int i = 0; i < rawData.length; ++i) {
				rawData[i][0] = in.nextInt();
				rawData[i][1] = in.nextInt();
			}
			discretize();
			SegmentTree st = new Main().new SegmentTree(discretization.length - 1);
			for (int i = 0; i < numbers; ++i) {
				st.newPoster(rawToDiscretized(rawData[i][0]), rawToDiscretized(rawData[i][1]), i + 1, 1);
			}
			System.out.println(st.getAnswer());
		}
	}

	private static void discretize() {
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < rawData.length; ++i) {
			for (int j = 0; j < rawData[0].length; ++j) {
				set.add(rawData[i][0]);
				set.add(rawData[i][1]);
				set.add(rawData[i][0] + 1);
				set.add(rawData[i][1] + 1);
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

	class SegmentTree {

		public SegmentTree(int length) {
			data = new NODE[length * 4];
			build(1, 1, length);
		}

		public void print() {
			for (NODE node : data) {
				if (node != null)
					System.out.println(node);
			}
		}

		private boolean isPrimarySegment(int left, int right) {
			return left == right;
		}

		public void build(int index, int left, int right) {

			NODE node = null;
			if (isPrimarySegment(left, right)) {
				node = new NODE(index, left, right);
			} else {
				build(index * 2, left, (left + right) >>> 1);
				build(index * 2 + 1, ((left + right) >>> 1) + 1, right);
				node = new NODE(index, left, right);
			}
			data[index] = node;
		}

		public void newPoster(int from, int to, int color, int index) {
			NODE node = data[index];
			if (node.isCompletelyCovered(from, to)) {
				node.update(color);
				return;
			}
			if (to > node.getMid()) {
				newPoster(from, to, color, node.getRightChildIndex());
			}
			if (from <= node.getMid()) {
				newPoster(from, to, color, node.getLeftChildIndex());
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
		private int color = 0;

		@Override
		public String toString() {
			return "NODE [index=" + index + ", left=" + left + ", right=" + right + ", color=" + color + "]";
		}

		public NODE(int index, int left, int right) {
			this.index = index;
			this.left = left;
			this.right = right;
		}

		public int getMid() {
			return (left + right) >>> 1;
		}

		public int getValue() {
			HashSet<Integer> set = new HashSet<Integer>();
			calculate(0, set);
			return set.size();
		}

		public void calculate(int color, HashSet<Integer> set) {
			if (this.color == 0 || color > this.color)
				update(color);
			if (isPrimary()) {
				if (this.color != 0)
					set.add(this.color);
			} else {
				NODE leftChild = data[getLeftChildIndex()];
				NODE rightChild = data[getRightChildIndex()];
				leftChild.calculate(this.color, set);
				rightChild.calculate(this.color, set);
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

		public void update(int color) {
			this.color = color;
		}

		public boolean isPrimary() {
			return right - left == 0;
		}
	}

}
