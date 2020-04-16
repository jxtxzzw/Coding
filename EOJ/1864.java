import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 这道题用二分图的匈牙利算法
 * 不存在难处
 * 关键是：如何把问题抽象成二分图
 * 显然
 * 对于每一个word来说都是相互独立的
 * word之间相互不影响
 * 于是
 * word的第i个字符如果出现在第j个cube中
 * 可以认为i和j是连通的
 * 由此可以得到lineExist数组
 * 下面就是一个二分图的dfs（下详）
 * 
 */

public class Main {

	private static boolean[] visited; // 这个点是不是被访问过
	private static int numberOfCube; // cube的个数
	private static boolean[][] lineExist; // word的第i个字符是不是出现在第j个cube中
	private static int[] match; // 是不是能找到这样一条路径配对

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			numberOfCube = in.nextInt();
			int numberOfWord = in.nextInt();
			String[] cube = new String[numberOfCube];
			visited = new boolean[numberOfCube];
			match = new int[numberOfCube];
			ArrayList<Integer> list = new ArrayList<Integer>(); // 存放哪几个word是可以用cube拼出来的
			for (int i = 0; i < numberOfCube; ++i) {
				cube[i] = in.next();
			}
			for (int i = 0; i < numberOfWord; ++i) {
				String word = in.next();
				lineExist = new boolean[word.length()][numberOfCube];
				// 遍历word的每一个字符，如果第j个字符出现在第k个cube，就认为他们是连通的
				for (int j = 0; j < word.length(); ++j) {
					for (int k = 0; k < numberOfCube; ++k) {
						if (cube[k].indexOf(word.charAt(j)) != -1) {
							lineExist[j][k] = true;
						}
					}
				}
				// 默认能够为word找到cube们拼出来
				boolean matched = true;
				/*
				 * 这里我调试了很久 match数组的初始化应该放在循环外面 而不是循环里面 因为如果第x个字符用掉了第y个cube 于是后面的字符就都不能用这个cube了
				 * 所以一旦match[x]=y以后 就说明这个x和y已经配对了 后面如果要让x和其他的cube配对 就应该是dfs(match[x])了
				 * 也就是要为x腾出一个新的cube 而不是match[x]==-1
				 * 
				 */
				Arrays.fill(match, -1);

				// 对word的每一个字符，开始找
				for (int j = 0; j < word.length(); ++j) {
					/*
					 * 这里我调试了很久 visit数组的初始化应该放在循环里面 而不是循环外面 因为对于每一个字符来说 一开始都是没有尝试腾出过某一个cube为它
					 * 上一个字符想要找这个cube 不管他是不是成功 这个字符都是可以去试一试要不要这个cube的 而不是说上一个尝试过了 这个就不让尝试了
					 * 至于最后进不进这个if 是由这个字符自己决定的 所以每一个的visited都要重置
					 * 
					 */
					Arrays.fill(visited, false);
					// 如果某个字符不能找到配对，就可以不用往下了，已经不可能拼成了
					if (!dfs(j)) {
						matched = false;
						break;
					}
				}
				// 如果找到，就加入到list
				if (matched) {
					list.add(i);
				}
			}
			// 如果list为空，就输出-1，否则，输出所有的编号
			if (list.isEmpty()) {
				System.out.println(-1);
			} else {
				for (int i = 0; i < list.size(); ++i) {
					System.out.print(list.get(i));
					System.out.print(i == list.size() - 1 ? "\n" : " ");
				}
			}
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
		// 遍历所有的cube
		for (int to = 0; to < numberOfCube; ++to) {
			// 如果连通，并且没有被访问过
			if (lineExist[from][to] && !visited[to]) {
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

