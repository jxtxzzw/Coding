import java.awt.Point;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int numberOfVertex = in.nextInt();
			if (numberOfVertex == 0)
				break;
			// 依次读入所有的点的坐标
			Point[] points = new Point[numberOfVertex];
			for (int i = 0; i < numberOfVertex; ++i) {
				points[i] = new Point(in.nextInt(), in.nextInt());
			}
			double answer = 0;
			// 讲原始图形分隔成若干个小的三角形，分别求面积并求和
			for (int i = 1; i <= numberOfVertex - 2; ++i) {
				// 对于每一个三角形，求出它的三个顶点
				Point begin = points[0];
				Point transfer = points[i];
				Point terminal = points[i + 1];
				// 求出这个三角形的有向面积，然后加和
				answer += size(begin, transfer, terminal);
			}
			System.out.printf("%.1f\n", answer);
		}

	}

	/**
	 * 求出以给定的三个点为顶点的三角形的有向面积
	 * 
	 * @param begin
	 *            起点，第一个向量第起点
	 * @param transfer
	 *            中间点，第一个向量的终点，同时也是第二个向量的起点
	 * @param terminal
	 *            终点，第二个向量的终点
	 * @return 以给定的三个点为顶点的三角形的有向面积
	 */
	private static double size(Point begin, Point transfer, Point terminal) {
		double[][] determinant = new double[2][2];
		// 利用向量的叉积计算
		determinant[0][0] = transfer.getX() - begin.getX();
		determinant[0][1] = transfer.getY() - begin.getY();
		determinant[1][0] = terminal.getX() - begin.getX();
		determinant[1][1] = terminal.getY() - begin.getY();
		// 因为多边形可能是凹多边形，所以计算的是有向面积，不带绝对值
		return (determinant[0][0] * determinant[1][1] - determinant[0][1] * determinant[1][0]) / 2;
	}
}
