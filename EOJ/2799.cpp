#include <stdio.h>
#include <iostream>
#define MAX_N 307
#define INFINITY 0x3F3F3F3F

using namespace std;

int costs[MAX_N][MAX_N]; // costs[i][j]表示从i放到j的花费
int dp[MAX_N][MAX_N]; // dp[i][j]表示在总共i个里面放k个的最小花费

int main() {
	int caseNumber;
	scanf("%d", &caseNumber);
	for (int caseIndex = 0; caseIndex<caseNumber; ++caseIndex) {
		// 初始化使得costs和dp都是无穷大
		for (int i=0; i<MAX_N; ++i) {
			for (int j=0; j<MAX_N; ++j) {
				costs[i][j]=INFINITY;
			}
		}
		for (int i=0; i<MAX_N; ++i) {
			for (int j=0; j<MAX_N; ++j) {
				dp[i][j]=INFINITY;
			}
		}
		int n, m, k;
		scanf("%d%d%d", &n, &m, &k);
		while (m--) {
			// 读入数据
			int left, right, cost;
			scanf("%d%d%d", &left, &right, &cost);
			costs[left][right] = min(costs[left][right], cost);
			/**
			 * 这里有一个坑
			 * costs[left][right]=cost;
			 * 是不行的
			 * 输入的数据可能会有重复的
			 * 比如1 2 5和1 2 1和1 2 10
			 * 这时要取最小的
			 * 即1 2 1
			 */
			for (int i=left; i<=right; ++i) {
				for (int j=i; j<=right; ++j) {
					costs[i][j]=min(costs[i][j],cost);
				}
			}
			/**
			 * 比如从4放到6是花费3
			 * 那么从4放到4、从4放到5、从4放到6、从5放到5、从5放到6、从6放到6都是花费3
			 * 这一个循环就是在更新这个信息
			 */
		}

		/**
		 * 有点类似于最短路径
		 * 就是从i到j的花费
		 * 中间取一个中转站t
		 * 如果中转之后能够使得花费更小
		 * 就更新所有的costs为最小的花费
		 */
		for (int i=1; i<=n; ++i) {
			for (int j=i; j<=n; ++j) {
				for (int t=i; t<j; ++t) {
					costs[i][j]=min(costs[i][j],costs[i][t]+costs[t+1][j]);
				}
			}
		}

		// 动态规划的边界条件
		/**
		 * 在i个里面放0个显然是花费为0
		 * 虽然没有实际意义
		 * 但是为了使得动态转移方程能够继续
		 */
		for (int i = 0; i <= n; ++i) dp[i][0] = 0;

		/**
		 * dp[i][j]表示在总共i个里面放j个
		 * 那么i就是从1遍历到n
		 * j就是从1遍历到i
		 * （显然最多是i个放i个，j>i没有意义）
		 * 事实上j<=min(i,k)就可以了
		 * 反正最后要的答案就是放k个
		 * 不过无所谓
		 * 全部求完也可以
		 */
		for (int i = 1; i <= n; ++i)
			for (int j = 1; j <= i; ++j) {
				// 在i个里面放j个
				// 显然如果i-1个里放j个能够放得下的话
				// i个里面放j个就按照i-1个里面放j个那样的花费就行了
				dp[i][j] = dp[i - 1][j];

				// 接下来看看能不能更省
				// t表示少放多少个
				// 从少放1个开始，到少放j个（最大是j）
				for (int t = 1; t <= j; ++t)
					// 如果从i-t+1放到i
					// （这里一共放了t个，就是现在少放的t个）
					// 的花费不是INFINITY
					// 就说明这是一种合法的可能
					// 就需要去尝试
					if (costs[i - t + 1][i] != INFINITY)
						dp[i][j] = min(dp[i][j], dp[i - t][j - t] + costs[i - t + 1][i]);
					// 一种是现在的i个里面放j个
					// 另一种是先在i-t个里面放j-t个
					// 然后把这个花费加上放那t个的花费
					// 放t个的花费是从i-t+1放到i的花费
					// 也就是costs[i-t+1][i]
					// 于是dp[i][j]就是dp[i][j]和dp[i-t][j-t]+costs[i-t+1][i]中较小的一个
			}
		// 最后答案就是在n个里面放k个的最小花费
		printf("case #%d:\n%d\n",caseIndex,dp[n][k]==INFINITY?-1:dp[n][k]);
	}

	return 0;
}
