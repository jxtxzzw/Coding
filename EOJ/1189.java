import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		while (in.hasNextInt()) {

			int numbers = in.nextInt();
			int radius = in.nextInt();

			// 读入所有的向量点
			Vector[] vectors = new Vector[numbers];
			for (int i = 0; i < vectors.length; ++i) {
				vectors[i] = new Vector(in.nextInt(), in.nextInt());
			}

			// 用Graham扫描法获得最小凸包的栈
			Stack<Vector> stack = graham(vectors);

			// 答案是最小凸包的周长加上一个圆周长
			double answer = Math.PI * 2 * radius + perimeter(stack);
			System.out.printf("%.0f\n", answer);

		}
	}

	/**
	 * 计算最小凸包的周长
	 * 
	 * @param stack
	 *            表示最小凸包的栈
	 * @return 周长
	 */
	private static double perimeter(Stack<Vector> stack) {
		double perimeter = 0;
		// 依次取出相邻的两个点，计算距离，加和
		for (int idx = 1; idx < stack.size(); ++idx) {
			perimeter += stack.get(idx).distance(stack.get(idx - 1));
		}
		// 最后一个点和第一个点
		perimeter += stack.get(0).distance(stack.peek());
		return perimeter;
	}

	/**
	 * 求出最小凸包
	 * 
	 * @param vectors
	 *            所有向量的点
	 * @return 表示最小凸包的栈
	 */
	private static Stack<Vector> graham(Vector[] vectors) {
		// 对所有的点进行排序，找出纵坐标最小的点，如果纵坐标相等取最小的横坐标（因为Vector实现了Comparable接口）
		Arrays.sort(vectors);

		// 于是第0个元素就是原点
		Vector basePoint = vectors[0];

		// 计算各个点相对于原点的幅角，按从小到大的顺序对各个点排序，如果幅角相等取距离原点近的
		// 这里的排序从第1个元素开始，因为第0个是原点不用排序
		// 需要给定一个内部类Comparator
		Arrays.sort(vectors, 1, vectors.length, new Comparator<Vector>() {

			/* (non-Javadoc)
			 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
			 */
			@Override
			public int compare(Vector v1, Vector v2) {

				double crossValue = basePoint.cross(v1, v2); // 计算<原点,v1>和<原点,v2>的叉积

				final double EPSILON = 1E-9;

				if (crossValue > 0) {
					return -1; // 按幅角大小排序
				} else if (Math.abs(crossValue) < EPSILON && basePoint.distance(v1) < basePoint.distance(v2)) {
					return -1; // 如果幅角相等取距离原点近的
				} else {
					return 1;
				}
			}

		});

		// 存放最小凸包的栈
		Stack<Vector> stack = new Stack<Vector>();

		// 原点和P1显然是凸包上的点
		stack.push(basePoint);
		stack.push(vectors[1]);

		// 从下一个点开始，循环，直到扫描完全部的点
		int index = 2;
		while (index != vectors.length) {

			double crossValue;
			Vector currentJudging;

			do {
				currentJudging = vectors[index]; // 现在开始检查第index个点是不是凸包上的点
				Vector top = stack.peek(); // 取出栈顶元素
				Vector lastJudged = stack.get(stack.size() - 2); // 取出栈顶下面的那个元素作为前一个已经检查的点

				crossValue = lastJudged.cross(top, currentJudging); // 计算<前一个已经检查的点,栈顶>和<前一个已经检查的点,当前检查的点>的叉积
				if (crossValue < 0) {
					stack.pop(); // 如果叉积小于0，说明栈顶元素不是凸包上的点，出栈，并循环继续检查，直到确保栈顶元素是凸包上的点
				}

			} while (crossValue < 0);

			// 当前点入栈，检查下一个点
			stack.push(currentJudging);
			index++;
		}

		// 最后一个点一定是凸包上的点，入栈
		stack.push(vectors[vectors.length - 1]);
		return stack;
	}
}

/**
 * 向量类 继承Point类，表示单个点的坐标 实现Comparable接口，自然序列取纵坐标小的，如果纵坐标相等取横坐标小的
 *
 */
class Vector extends Point implements Comparable<Vector> {

	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造函数，构造一个坐标为(x,y)的点
	 * 
	 * @param x
	 *            横坐标
	 * @param y
	 *            纵坐标
	 */
	public Vector(int x, int y) {
		super(x, y); // 交给父类Point构造
	}

	/**
	 * 计算向量的叉积
	 * 
	 * @param v1
	 *            第1个向量的终止点
	 * @param v2
	 *            第2个向量的终止点
	 * @return 该点与第1个向量的终止点所构成的向量，与该点与第2个向量的终止点所构成的向量，这两个向量的叉积
	 */
	public double cross(Vector v1, Vector v2) {
		return (v1.x - x) * (v2.y - y) - (v1.y - y) * (v2.x - x);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Vector v) {
		if (y == v.y) {
			return x - v.x;
		} else {
			return y - v.y;
		}

	}

}
