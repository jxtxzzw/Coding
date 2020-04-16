/**
 * 这是线段树的模板题
 * 直接用区间最大值的线段树模板就可以了
 * 
 * 注意理解递归
 * 这是线段树的难点
 * 
 * 需要注意的是分片的时候什么时候要加1，什么时候不加1
 * (nodeIndex * 2)
 * (nodeIndex * 2 + 1)
 * ((left + right) / 2)
 * ((left + right) / 2 + 1)
 * 
 * 还要注意的是比较的时候等号取还是不取
 * (from <= left && to >= right)
 * (from <= mid)
 * (to > mid)
 * 
 * 下示为AC代码
 * 后面给出MLE代码
 * 
 * 注意这里的风格是C/C++的风格，仅仅为了做出这道题
 * 没有考虑“低耦合、高内聚、数据表现相分离”
 * 也没有一点点的Object-Oriented的思想
 * 
 * 这是区间最大值
 * 如果要求最小值
 * 是类似的
 * 只要改动flush
 * 如果是求区间和
 * 也是类似的
 * 只不过flush改动的多一点
 * 
 * 所以
 * 我想着什么时候再彻底地学习一遍线段树
 * 然后把SegmentTree抽象成接口
 * 然后抽空实现几个类
 * 到时候遇到题目就直接调用
 * 
 */

import java.util.Scanner;

public class Main {

	static Scanner in = new Scanner(System.in);
	static int [] max;

	public static void main(String[] args) {
		while (in.hasNextInt()) {
			int numberOfStudents = in.nextInt();
			int numberOfOperations = in.nextInt();
			max = new int[numberOfStudents * 5];
			SegmentTree.build(1,1,numberOfStudents);
//			DEBUG_USE_ONLY();
			while (numberOfOperations-- != 0) {
				switch (in.next()) {
				case "Q":
//					DEBUG_USE_ONLY();
					int ans = SegmentTree.query(in.nextInt(), in.nextInt(), 1,1,numberOfStudents);
					System.out.println(ans);
					break;
				case "U":
					SegmentTree.update(in.nextInt(), in.nextInt(), 1,1,numberOfStudents);
//					DEBUG_USE_ONLY();
					break;
				}
			}
		}
	}

//	private static void DEBUG_USE_ONLY() {
//		for (int i = 0; i < trees.length; i++)
//			if (trees[i] != null)
//				System.out.println(trees[i]);
//	}

	static class SegmentTree {

		private static boolean isPrimarySegment(int left, int right) {
			return left == right;
		}

		public static void build(int nodeIndex, int left, int right) {
			if (isPrimarySegment(left, right)) {
				max[nodeIndex] = in.nextInt();
			} else {
				build(nodeIndex*2,left,(left+right)/2);
				build(nodeIndex*2+1,(left+right)/2+1,right);
				flush(nodeIndex);
			}
		}

		private static void flush(int nodeIndex) {
			max[nodeIndex] = Math.max(max[nodeIndex*2], max[nodeIndex*2+1]);
		}

		public static void update(int targetIndex, int newValue, int nodeIndex, int left, int right) {
			if (isPrimarySegment(left,right)) {
				max[nodeIndex] = newValue;
			} else {
				if (targetIndex <= (left+right) / 2) {
					update(targetIndex, newValue,nodeIndex*2,left,(left+right)/2);
				} else {
					update(targetIndex, newValue, nodeIndex*2+1,(left+right)/2+1,right);
				}
				flush(nodeIndex);
			}
		}

		public static int query(int from, int to, int nodeIndex, int left, int right) {
			if (from <= left && to >= right) {
				return max[nodeIndex];
			} else {
				int tmp = 0;
				int mid = (left + right) / 2;
				if (from <= mid)
					tmp = Math.max(tmp, query(from, to, nodeIndex*2,left,(left+right)/2));
				if (to > mid)
					tmp = Math.max(tmp, query(from, to, nodeIndex*2+1,(left+right)/2+1,right));
				return tmp;
			}
		}

	}
}



/*
 * 我没有想到下面这段代码竟然会是Memory Limit Exceeded
 * 所以说不能把每一棵树留下来
 * 只能留下结果
 * 也就是只有value
 * 其他的什么left、right不需要
 *
 */

/*

import java.util.Scanner;

public class Main {

	static Scanner in = new Scanner(System.in);
	static SegmentTree[] trees;

	public static void main(String[] args) {
		while (in.hasNextInt()) {
			int numberOfStudents = in.nextInt();
			int numberOfOperations = in.nextInt();
			trees = new SegmentTree[numberOfStudents * 5];
			final SegmentTree ROOT = new SegmentTree(1, 1, numberOfStudents);
			SegmentTree.build(ROOT);
			while (numberOfOperations-- != 0) {
				switch (in.next()) {
				case "Q":
					int ans = SegmentTree.query(in.nextInt(), in.nextInt(), ROOT);
					System.out.println(ans);
					break;
				case "U":
					SegmentTree.update(in.nextInt(), in.nextInt(), ROOT);
					break;
				}
			}
		}
	}


	static class SegmentTree {
		int nodeIndex;
		int left;
		int right;
		int value;

		public SegmentTree(int nodeIndex, int left, int right) {
			this.nodeIndex = nodeIndex;
			this.left = left;
			this.right = right;
		}

		private static boolean isPrimarySegment(SegmentTree st) {
			return st.left == st.right;
		}

		public static void build(SegmentTree st) {
			if (isPrimarySegment(st)) {
				st.value = in.nextInt();
			} else {
				build(st.leftSubSegmentTree());
				build(st.rightSubSegmentTree());
				flush(st);
			}
		}

		private SegmentTree leftSubSegmentTree() {
			if (trees[nodeIndex * 2] == null)
				trees[nodeIndex * 2] = new SegmentTree(nodeIndex * 2, left, (left + right) / 2);
			return trees[nodeIndex * 2];
		}

		private SegmentTree rightSubSegmentTree() {
			if (trees[nodeIndex * 2 + 1] == null)
				trees[nodeIndex * 2 + 1] = new SegmentTree(nodeIndex * 2 + 1, (left + right) / 2 + 1, right);
			return trees[nodeIndex * 2 + 1];
		}

		private static void flush(SegmentTree st) {
			st.value = Math.max(st.leftSubSegmentTree().value, st.rightSubSegmentTree().value);
		}

		public static void update(int targetIndex, int newValue, SegmentTree st) {
			if (isPrimarySegment(st)) {
				st.value = newValue;
			} else {
				if (targetIndex <= (st.left + st.right) / 2) {
					update(targetIndex, newValue, st.leftSubSegmentTree());
				} else {
					update(targetIndex, newValue, st.rightSubSegmentTree());
				}
				flush(st);
			}
		}

		public static int query(int from, int to, SegmentTree st) {
			if (from <= st.left && to >= st.right) {
				return st.value;
			} else {
				int tmp = 0;
				int mid = (st.left + st.right) / 2;
				if (from <= mid)
					tmp = Math.max(tmp, query(from, to, st.leftSubSegmentTree()));
				if (to > mid)
					tmp = Math.max(tmp, query(from, to, st.rightSubSegmentTree()));
				return tmp;
			}
		}

		@Override
		public String toString() {
			return "SegmentTree [nodeIndex=" + nodeIndex + ", left=" + left + ", right=" + right + ", value=" + value
					+ "]";
		}

	}
}

*/
