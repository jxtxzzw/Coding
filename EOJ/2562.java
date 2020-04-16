
/**
 * 备注：
 * 这道题一开始用的是含“路径压缩”与“按树的深度合并”这双重优化的有根树
 * 没想到竟然在第3组数据的时候是超时的
 * 后来重新用第1种做法
 * 就是不带优化的线性表来表示的
 * 老老实实遍历所有的元素
 * 反而没有超时
 * 这很奇怪
 * 我写的是没有问题的
 * 路径压缩和桉树的深度合并也完全正确的
 * 后经过与测试数据的对比
 * 发现完全一致
 * 而本地用System.currentTimeMillis计时也没有超过时限
 * 本地仅用了约4秒，时限为5秒
 * 所以做过压缩路径和按秩合并，理论上讲应该不会超时的
 * 然而事实就是超了
 * 那我也不管了
 * 反正用不带优化的方法是AC的
 *
 */

import java.util.HashMap;
import java.util.Scanner;

public class Main {
	private static int[] disjointSet = new int[100007];
	private static HashMap<String, Integer> map = new HashMap<String, Integer>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int caseNumber = in.nextInt();
		while (caseNumber-- != 0) {
			int queryNumber = in.nextInt();
			// 清空disjointSet和map
			disjointSet = new int[100007];
			map = new HashMap<String, Integer>();
			while (queryNumber-- != 0) {
				// 读入姓名，并将字符串映射成数值
				String friendNameA = in.next();
				String friendNameB = in.next();
				int friendIndexA;
				int friendIndexB;
				if (map.containsKey(friendNameA)) {
					friendIndexA = map.get(friendNameA);
				} else {
					friendIndexA = map.size() + 1;
					map.put(friendNameA, friendIndexA);
					disjointSet[friendIndexA] = friendIndexA;
				}
				if (map.containsKey(friendNameB)) {
					friendIndexB = map.get(friendNameB);
				} else {
					friendIndexB = map.size() + 1;
					map.put(friendNameB, friendIndexB);
					disjointSet[friendIndexB] = friendIndexB;
				}
				// 至此得到人物编号
				// 首先令所属集合的根为A的根
				int root = findSet(friendIndexA);
				// 如果他们属于不同的朋友圈，合并，并更新root
				if (findSet(friendIndexA) != findSet(friendIndexB)) {
					root = unionSet(friendIndexA, friendIndexB);
				}
				// 遍历所有人，如果属于同一个朋友圈，计数器加1
				int counter = 0;
				for (int i = 1; i <= map.size(); ++i) {
					if (findSet(i) == root) {
						counter++;
					}
				}
				System.out.println(counter);
			}
		}

	}

	// 查，这里用了不带优化的做法
	private static int findSet(int friendIndex) {
		return disjointSet[friendIndex]; // 直接返回父节点编号
	}

	// 并，这里用了不带优化的做法，对于合并操作，必须搜索全部元素
	private static int unionSet(int friendIndexA, int friendIndexB) {
		// j合并到i中，因为令i是两个集合的节点编号较小的那个
		int i = Math.min(findSet(friendIndexA), findSet(friendIndexB));
		int j = Math.max(findSet(friendIndexA), findSet(friendIndexB));
		// 遍历所有的元素，更新他们父节点的编号
		for (int k = 1; k <= map.size(); k++)
			if (disjointSet[k] == j) // 如果是另一个集合
				disjointSet[k] = i; // 合并过来
		// 返回i就是合并后集合的编号
		return i;
	}

}