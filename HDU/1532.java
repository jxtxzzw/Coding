import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int numberOfIntersectionsPoints; // 点的个数
	static int[] previousPoint; // 从哪一个点到达的这个点
	static int[][] flow; // 流量

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			// 处理输入
			int numberOfDitches = in.nextInt();
			numberOfIntersectionsPoints = in.nextInt();
			flow = new int[numberOfIntersectionsPoints + 1][numberOfIntersectionsPoints + 1];
			while (numberOfDitches-- != 0) {
				int from = in.nextInt();
				int to = in.nextInt();
				int traffic = in.nextInt();
				flow[from][to] += traffic;
				/*
				 * 这里有一个坑
				 * 有的时候从from到to不止一条水沟
				 * 所以
				 * flow[from][to] = traffic
				 * 是不对的
				 * 正确的Capacity是所有水沟的容量和
				 * 于是
				 * flow[from][to] += traffic
				 */
			}
			// 得到答案并输出
			final int SOURCE_POINT = 1;
			final int TERMINAL_POINT = numberOfIntersectionsPoints;
			System.out.println(maxFlow(SOURCE_POINT, TERMINAL_POINT));
		}
	}

	/**
	 * 广度优先搜索，判断是不是还存在增广路径
	 * 
	 * @param sourcePoint
	 *            源点
	 * @param terminalPoint
	 *            终点
	 * @return 如果存在增广路径，返回true，否则，false
	 */
	static boolean bfs(int sourcePoint, int terminalPoint) {
		Queue<Integer> queue = new LinkedList<Integer>(); // 广度优先搜索用队列保存
		previousPoint = new int[numberOfIntersectionsPoints + 1];
		boolean[] visited = new boolean[numberOfIntersectionsPoints + 1]; // 这个点是不是被访问过
		// 定义源点是从源点来的，记录访问，并入队
		previousPoint[sourcePoint] = sourcePoint;
		visited[sourcePoint] = true;
		queue.add(sourcePoint);
		// 当队列非空，取出队首元素，表示从这个点开始广搜
		while (!queue.isEmpty()) {
			int from = queue.poll();
			// 遍历所有的终点作为到达的点
			for (int to = 1; to <= numberOfIntersectionsPoints; ++to) {
				// 如果从from到to可以走并且to这个点没有被访问过，就记录to是从from来的，并且记录已访问
				if (flow[from][to] > 0 && !visited[to]) {
					previousPoint[to] = from;
					visited[to] = true;
					if (to == terminalPoint) {
						return true; // 如果到达终点，说明存在增广路径，true
					}
					queue.add(to); // 入队，继续
				}
			}
		}
		return false; // 不存在增广路径了
	}

	/**
	 * 计算最大流
	 * 
	 * @param sourcePoint
	 *            源点
	 * @param terminalPoint
	 *            终点
	 * @return 从源点到终点的最大流
	 */
	static int maxFlow(int sourcePoint, int terminalPoint) {
		int maxFlow = 0; // 最大流
		int delta = 0; // 增量
		while (bfs(sourcePoint, terminalPoint)) {
			delta = Integer.MAX_VALUE; // 增量取最小值，所以初始化为无穷大
			int to = terminalPoint; // 从终点倒着走
			// 还没有走到源点的时候，不断取出previousPoint为from，取flow[from][to]与delta较小的，由此求得最小的增量
			while (to != sourcePoint) {
				int from = previousPoint[to];
				delta = Math.min(delta, flow[from][to]);
				to = from;
			}
			// 继续从终点倒着走
			to = terminalPoint;
			// 还没有走到源点的时候，求出全部的反向边
			while (to != sourcePoint) {
				int from = previousPoint[to];
				// 取出from，维护[from][to]和[to][from]
				flow[from][to] -= delta;
				flow[to][from] += delta;
				to = from;
			}
			// 最大流加上增量
			maxFlow += delta;
		}
		return maxFlow;
	}

}
