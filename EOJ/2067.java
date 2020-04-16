import java.awt.Point;
import java.util.Scanner;

/*
 * 这道题是已经存在部分的路已经连接
 * 所以在连接最短路径的时候要考虑这些路是一定要被连进去
 * 在此基础上再找最短
 * 但是只要把这些路看作是花费为0的路
 * 就可以直接使用最短路径来做了
 * 因为花费为0就一定是最短，所以肯定会被收录
 * 这样的话，lowCost刷新已经被使用的点的时候，就不可以是用0来表示，我这里用了-1
 */

public class Main {
	
	private static final double INFINITY = Integer.MAX_VALUE / 2;
	private static final double ALREADY_USED = -1;
	// 表示这个点已经被收录

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numberOfFarms = in.nextInt();
		int numberOfRoads = in.nextInt();
		Point[] farms = new Point[numberOfFarms];
		// 读入所有农场的坐标，以Point的形式保存在数组中
		for (int i = 0; i < numberOfFarms; ++i) {
			int x = in.nextInt();
			int y = in.nextInt();
			Point farm = new Point(x, y);
			farms[i] = farm;
		}
		// 计算所有农场之间的距离
		double[][] distances = new double[numberOfFarms][numberOfFarms];
		for (int i = 0; i < numberOfFarms; ++i) {
			for (int j = 0; j < numberOfFarms; ++j) {
				// 直接取出下标为i和j的两个Point，调用Point.distance()方法求两点间距离
				distances[i][j] = distances[j][i] = farms[i].distance(farms[j]);
			}
		}
		// 把已经存在的道路设置为花费为0
		for (int i = 0; i < numberOfRoads; ++i) {
			int from = in.nextInt() - 1; // 下标从0开始
			int to = in.nextInt() - 1;
			distances[from][to] = distances[to][from] = 0;
		}
		// 从0号开始修路，最开始就是从0到i的距离
		double[] lowCost = new double[numberOfFarms];
		for (int i = 1; i < numberOfFarms; ++i) {
			lowCost[i] = distances[0][i];
		}
		double lengthToBeBuild = 0;
		// Prim算法
		for (int i = 1; i < numberOfFarms; ++i) {
			double minimumDistance = INFINITY;
			int farmIndex = 0;
			for (int j = 1; j < numberOfFarms; ++j) {
				// 这个农场没有被使用过，就取最小的
				if (lowCost[j] != ALREADY_USED && lowCost[j] < minimumDistance) {
					minimumDistance = lowCost[j];
					farmIndex = j;
				}
			}
			lengthToBeBuild += minimumDistance;
			lowCost[farmIndex] = ALREADY_USED;
			// 更新从farmIndex开始到j的lowCost
			for (int j = 1; j < numberOfFarms; ++j) {
				if (distances[farmIndex][j] < lowCost[j]) {
					lowCost[j] = distances[farmIndex][j];
				}
			}
		}
		System.out.printf("%.2f", lengthToBeBuild);
	}
}
