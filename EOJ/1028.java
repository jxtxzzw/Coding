import java.util.HashMap;
import java.util.Scanner;

/*
 * 这道题就是一个最短路径的Floyd算法，三层循环
 * 由于题目给出的是IP地址，所以要有一个String和int的转化过程
 * 我这里用了HashMap来完成这个映射
 * 
 */

public class Main {

	final static int INFINITY = Integer.MAX_VALUE / 2;
	// INFINITY没有设置成Integer.MAX_VALUE是防止相加以后溢出为负数导致结果错误

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numberOfRouters = in.nextInt();
		int numberOfCommunicationLine = in.nextInt();
		int[][] timeSpents = new int[numberOfRouters][numberOfRouters];
		for (int row = 0; row < timeSpents.length; ++row) {
			for (int col = 0; col < timeSpents[row].length; ++col) {
				timeSpents[row][col] = INFINITY;
			}
		}
		// HashMap完成一一对应，判断contains()
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		while (numberOfCommunicationLine-- != 0) {
			String routerAddressFrom = in.next();
			String routerAddressTo = in.next();
			int timeSpent = in.nextInt();
			int routerIndexFrom;
			int routerIndexTo;
			if (map.containsKey(routerAddressFrom)) {
				routerIndexFrom = map.get(routerAddressFrom);
			} else {
				routerIndexFrom = map.size();
				map.put(routerAddressFrom, routerIndexFrom);
			}
			if (map.containsKey(routerAddressTo)) {
				routerIndexTo = map.get(routerAddressTo);
			} else {
				routerIndexTo = map.size();
				map.put(routerAddressTo, routerIndexTo);
			}
			// 每条通信线路都是全双工的 (双向的)
			timeSpents[routerIndexFrom][routerIndexTo] = timeSpent;
			timeSpents[routerIndexTo][routerIndexFrom] = timeSpent;
		}
		// Floyd
		for (int k = 0; k < numberOfRouters; ++k) {
			for (int i = 0; i < numberOfRouters; ++i) {
				for (int j = 0; j < numberOfRouters; ++j) {
					timeSpents[i][j] = Math.min(timeSpents[i][j], timeSpents[i][k] + timeSpents[k][j]);
				}
			}
		}
		int numberOfMessages = in.nextInt();
		// 从Map取出，然后获得结果，根据是不是INFINITY输出
		while (numberOfMessages-- != 0) {
			String routerAddressFrom = in.next();
			String routerAddressTo = in.next();
			int timeSpent = INFINITY;
			if (map.containsKey(routerAddressFrom)) {
				int routerIndexFrom = map.get(routerAddressFrom);
				if (map.containsKey(routerAddressTo)) {
					int routerIndexTo = map.get(routerAddressTo);
					timeSpent = timeSpents[routerIndexFrom][routerIndexTo];
				}
			}
			System.out.println(timeSpent == INFINITY ? -1 : timeSpent);
		}
	}
}
