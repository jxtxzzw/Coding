import java.util.Scanner;

public class Main {

	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		int rooms = in.nextInt();
		int queries = in.nextInt();
		SegmentTree st = new SegmentTree(rooms);
		for (int i = 0; i < queries; ++i) {
			int command = in.nextInt();
			switch (command) {
			case 1:
				int numbers = in.nextInt();
				System.out.println(st.possible(numbers));
				break;
			case 2:
				int from = in.nextInt();
				int interval = in.nextInt();
				st.checkout(from, interval);
				break;
			}
		}

	}

}

class SegmentTree {

	private NODE[] data = null;

	public SegmentTree(int length) {
		data = new NODE[length * 4];
		build(1, 1, length);
	}

	public void checkout(int from, int interval) {
		update(from, from + interval - 1, true, 1);
	}

	private void checkin(int from, int interval) {
		update(from, from + interval - 1, false, 1);
	}

	public int possible(int numbers) {
		int ret = query(numbers, 1);
		if (ret != 0) {
			checkin(ret, numbers);
		}
		return ret;
	}

	private boolean isPrimarySegment(int left, int right) {
		return left == right;
	}

	private void build(int nodeIndex, int left, int right) {
		NODE node = null;
		if (isPrimarySegment(left, right)) {
			node = new NODE(nodeIndex, left, right, true);
		} else {
			build(nodeIndex * 2, left, (left + right) >>> 1);
			build(nodeIndex * 2 + 1, ((left + right) >>> 1) + 1, right);
			node = new NODE(nodeIndex, left, right, true);
		}
		data[nodeIndex] = node;
	}

	private NODE getLeftChild(NODE node) {
		return data[node.getLeftChildIndex()];
	}

	private NODE getRightChild(NODE node) {
		return data[node.getRightChildIndex()];
	}

	public void print() {
		for (NODE n : data) {
			if (n != null)
				System.out.println(n);
		}
	}

	private void update(int from, int to, boolean rest, int index) {
		NODE node = data[index];
		if (node.isCompletelyCovered(from, to)) {
			node.update(rest);
			return;
		}
		pullDownLazy(node);
		if (to > node.getMid()) {
			update(from, to, rest, node.getRightChildIndex());
		}
		if (from < node.getMid() + 1) {
			update(from, to, rest, node.getLeftChildIndex());
		}
		maintain(node);
	}

	private void maintain(NODE node) {
		NODE leftChild = getLeftChild(node);
		NODE rightChild = getRightChild(node);
		int maxLength;
		maxLength = Math.max(Math.max(leftChild.getMaxLength(), rightChild.getMaxLength()),
				leftChild.getRightLength() + rightChild.getLeftLength());
		node.setMaxLength(maxLength);
		int leftLength;
		leftLength = leftChild.getLeftLength();
		if (leftLength == leftChild.getRange()) {
			leftLength += rightChild.getLeftLength();
		}
		node.setLeftLength(leftLength);
		int rightLength;
		rightLength = rightChild.getRightLength();
		if (rightLength == rightChild.getRange()) {
			rightLength += leftChild.getRightLength();
		}
		node.setRightLength(rightLength);
	}

	private void pullDownLazy(NODE node) {
		if (node.isRest() != null) {
			NODE leftChild = getLeftChild(node);
			NODE rightChild = getRightChild(node);
			leftChild.update(node.isRest());
			rightChild.update(node.isRest());
			node.setRest(null);
		}
	}

	private int query(int numbers, int index) {
		NODE node = data[index];
		if (isPrimarySegment(node.getLeft(), node.getRight())) {
			if (numbers == node.getRange()) {
				return node.getValue();
			} else {
				return 0;
			}
		}
		pullDownLazy(node);
		if (checkInLeft(numbers, node)) {
			return query(numbers, node.getLeftChildIndex());
		} else if (checkInCross(numbers, node)) {
			return getLeftChild(node).getValue();
		} else if (checkInRight(numbers, node)) {
			return query(numbers, node.getRightChildIndex());
		} else {
			return 0;
		}

	}

	private boolean checkInRight(int numbers, NODE node) {
		return getRightChild(node).getMaxLength() >= numbers;
	}

	private boolean checkInCross(int numbers, NODE node) {
		return getLeftChild(node).getRightLength() + getRightChild(node).getLeftLength() >= numbers;
	}

	private boolean checkInLeft(int numbers, NODE node) {
		return getLeftChild(node).getMaxLength() >= numbers;
	}

}

class NODE {
	private int index = 0;
	private int left = 0;
	private int right = 0;
	private int maxLength = 0;
	private int leftLength = 0;
	private int rightLength = 0;
	private Boolean rest = null;

	@Override
	public String toString() {
		return "NODE [index=" + index + ", left=" + left + ", right=" + right + ", maxLength=" + maxLength
				+ ", leftLength=" + leftLength + ", rightLength=" + rightLength + ", rest=" + rest + "]";
	}

	public NODE(int index, int left, int right, boolean rest) {
		this.index = index;
		this.left = left;
		this.right = right;
		this.rest = rest;
		setMaxLength(getRange());
		setLeftLength(getRange());
		setRightLength(getRange());
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getLeftLength() {
		return leftLength;
	}

	public void setLeftLength(int leftLength) {
		this.leftLength = leftLength;
	}

	public int getRightLength() {
		return rightLength;
	}

	public void setRightLength(int rightLength) {
		this.rightLength = rightLength;
	}

	public Boolean isRest() {
		return rest;
	}

	public void setRest(Boolean rest) {
		this.rest = rest;
	}

	public int getRange() {
		return right - left + 1;
	}

	public int getMid() {
		return (left + right) >>> 1;
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

	public void update(boolean rest) {
		setRest(rest);
		int length;
		length = isRest() ? getRange() : 0;
		setLeftLength(length);
		setRightLength(length);
		setMaxLength(length);
	}

	public int getValue() {
		return right - rightLength + 1;
	}

}
