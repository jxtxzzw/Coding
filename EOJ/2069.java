import java.util.Arrays;
import java.util.Scanner;

/*
 * 这道题和上课的一题猎人打鸟的例题是很像的
 * 要把问题抽象成二分图
 * 如果说(R,C)存在一个小行星
 * 就认为R和C是连通的
 * 于是
 * 在所有小行星的坐标确定以后
 * 就可以得到一个图
 * 下面就是用匈牙利算法求增广路径
 * 遍历size的Row（或者Column，一样的）
 * 对于每一个遍历都去做DFS
 * 如果return的是true说明找到一条路径
 * 于是计数器加1表示要消耗一个炮弹
 * 如果是false就继续
 * 最后计数器的结果就是答案
 * 
 */

public class Main {

	private static boolean[] visited; // 这个点是不是被访问过
	private static int size; // N*N的大小
	private static boolean[][] asteroidInRowAndColumn; // 某行某列是不是有一个小行星
	private static int[] match; // 是不是能找到这样一条路径配对

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			size = in.nextInt();
			int numberOfAsteroid = in.nextInt();
			// 注意小行星的编号是从1～N，不是0～N-1
			visited = new boolean[size + 1];
			match = new int[size + 1];
			asteroidInRowAndColumn = new boolean[size + 1][size + 1];
			// 读入小行星的坐标，并且标记row和column是相互连通的
			for (int i = 0; i < numberOfAsteroid; ++i) {
				int row = in.nextInt();
				int column = in.nextInt();
				asteroidInRowAndColumn[row][column] = true;
			}
			// 同样，match的重置在循坏外
			Arrays.fill(match, -1);
			int weaponUsed = 0; // 使用的武器数
			for (int j = 1; j <= size; ++j) {
				// visited的重置在循环内
				Arrays.fill(visited, false);
				if (dfs(j)) {
					++weaponUsed; // 如果能找到，就在这里放武器，就加1
				}

			}
			System.out.println(weaponUsed);
		}

	}

	/**
	 * 二分图的匈牙利算法
	 * 
	 * @param from
	 *            起点
	 * @return 如果能找到，返回true，否则，false
	 */
	private static boolean dfs(int from) {
		for (int to = 1; to <= size; ++to) {
			// 如果连通，并且没有被访问过
			if (asteroidInRowAndColumn[from][to] && !visited[to]) {
				visited[to] = true;
				// 如果还没有配对过，或者能够给他腾出一个新的配对
				if (match[to] == -1 || dfs(match[to])) {
					match[to] = from;
					return true;
				}
			}
		}
		return false;
	}

}
