import java.util.Scanner;

public class Main {

	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		int numbers = in.nextInt();
		int queries = in.nextInt();
		long[] rawData = new long[numbers];
		for (int i = 0; i < numbers; ++i) {
			rawData[i] = in.nextInt();
		}
		SegmentTree st = new SegmentTree(rawData);

		int begin = 0;
		int end = 0;
		int increcement = 0;
		for (int i = 0; i < queries; ++i) {
			String command = in.next();
			begin = in.nextInt();
			end = in.nextInt();
			switch (command.charAt(0)) {
			case 'Q':
				System.out.println(st.query(begin, end, 1));
				break;
			case 'C':
				increcement = in.nextInt();
				st.increase(begin, end, increcement, 1);
				break;
			}
		}

	}

}

class SegmentTree {

	private long[] rawData = null;
	private int counter = 0;
	private NODE[] data = null;

	public SegmentTree(long[] rawData) {
		this.rawData = rawData.clone();
		int length = rawData.length;
		data = new NODE[length * 4];
		build(1, 1, length);
	}

	private boolean isPrimarySegment(int left, int right) {
		return left == right;
	}

	public void build(int nodeIndex, int left, int right) {

		NODE node = null;
		if (isPrimarySegment(left, right)) {
			node = new NODE(nodeIndex, left, right, rawData[counter++]);
		} else {
			build(nodeIndex * 2, left, (left + right) >>> 1);
			build(nodeIndex * 2 + 1, ((left + right) >>> 1) + 1, right);
			node = new NODE(nodeIndex, left, right, 0);
			node.setSum(getChildrenValueSum(node));
		}
		data[nodeIndex] = node;
	}

	private NODE getLeftChild(NODE node) {
		return data[node.getLeftChildIndex()];
	}

	private NODE getRightChild(NODE node) {
		return data[node.getRightChildIndex()];
	}

	private long getChildrenValueSum(NODE node) {
		return getLeftChild(node).getValue() + getRightChild(node).getValue();
	}

	public void print() {
		for (NODE n : data) {
			if (n != null)
				System.out.println(n);
		}
	}

	public void increase(int from, int to, long increasement, int index) {
		NODE node = data[index];
		if (node.isCompletelyCovered(from, to)) {
			node.increase(increasement);
			return;
		}
		pullDownLazy(node);
		if (to > node.getMid()) {
			increase(from, to, increasement, node.getRightChildIndex());
		}
		if (from < node.getMid() + 1) {
			increase(from, to, increasement, node.getLeftChildIndex());
		}
		node.setSum(getChildrenValueSum(node));
	}

	private void pullDownLazy(NODE node) {
		NODE leftChild = getLeftChild(node);
		NODE rightChild = getRightChild(node);
		leftChild.increase(node.getIncreasement());
		rightChild.increase(node.getIncreasement());
		node.clearIncreasement();
	}

	public long query(int from, int to, int index) {
		NODE node = data[index];
		if (node.isCompletelyCovered(from, to)) {
			return node.getValue();
		}
		pullDownLazy(node);
		node.setSum(getChildrenValueSum(node));

		if (to <= node.getMid()) {
			return query(from, to, node.getLeftChildIndex());
		} else if (from > node.getMid()) {
			return query(from, to, node.getRightChildIndex());

		} else {
			return query(from, to, node.getLeftChildIndex()) + query(from, to, node.getRightChildIndex());
		}

	}

}

class NODE {
	private int index = 0;
	private int left = 0;
	private int right = 0;
	private long sum = 0;
	private long increasement = 0;

	public NODE(int index, int left, int right, long sum) {
		this.index = index;
		this.left = left;
		this.right = right;
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "NODE [index=" + index + ", left=" + left + ", right=" + right + ", sum=" + sum + ", increasement="
				+ increasement + "]";
	}

	public int getIndex() {
		return index;
	}

	public int getLeft() {
		return left;
	}

	public int getRight() {
		return right;
	}

	public long getSum() {
		return sum;
	}

	public void setSum(long sum) {
		this.sum = sum;
	}

	public long getIncreasement() {
		return increasement;
	}

	public void clearIncreasement() {
		increasement = 0;
	}

	public int getRange() {
		return right - left + 1;
	}

	public int getMid() {
		return (left + right) >>> 1;
	}

	public long getValue() {
		return increasement * getRange() + sum;
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

	public void increase(long increasement) {
		this.increasement += increasement;
	}
}
