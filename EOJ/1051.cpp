#include <stdio.h>
#include <stdlib.h>
#include <iostream>
#include <cstdlib>
#define MAXN 57
#define INFINITY 0xFFFFFFFFFFFFFF

using namespace std;

int matrix[MAXN][2]= {{0}};

long long currentTimes(int,int,int);

int main() {
	int caseNumber;
	scanf("%d",&caseNumber);

	while (caseNumber--) {
		int number;
		scanf("%d",&number);

		// 读入数据
		for (int i=0; i<number; ++i) scanf("%d%d",&matrix[i][0],&matrix[i][1]);

		// 初始化
		// 注意一个矩阵的时候是0
		// 其他情况全部是无穷大，而不是0
		long long dp[MAXN][MAXN]= {{0}};

		for (int i=0; i<number; ++i) {
			for (int j=0; j<number; ++j) {
				dp[i][j] = INFINITY;
			}
		}

		for (int i=0; i<number; ++i) dp[i][i]=0;

		// interval表示计算间隔，从间隔1个相乘，一直尝试到间隔全部相乘
		for (int interval = 1; interval<number; ++interval) {
			// 左乘一个矩阵的编号从0开始尝试
			for (int leftMatrixIndex = 0; leftMatrixIndex<number-interval; ++leftMatrixIndex) {
				// 根据interval计算出右乘矩阵的编号
				int rightMatrixIndex = leftMatrixIndex + interval;

				// 至此，dp[leftMatrixIndex][rightMatrixIndex]为所求
				// 该值有如下几种可能
				/*
				例如dp[1][5]，除了可以是[1][5]，也可以是[1][2]+[3][5]+(2)*(3)
				这里的[1][2]和[3][5]也是dp数组，而(2)*(3)表示2和3号矩阵相乘
				因为是相邻的，所以必定满足相乘条件，即Matrix[2][col]一定等于Matrix[3][row]
				于是这一次需要进行的操作就是Matrix[2][0]*Matrix[2][1]*Matrix[3][1]
				同样，也可以是[1][3]+[4][5]+(3)*(4)……
				取最小值
				*/
				for (int associativeIndex = leftMatrixIndex; associativeIndex<=rightMatrixIndex; ++associativeIndex) {
					dp[leftMatrixIndex][rightMatrixIndex] = min(dp[leftMatrixIndex][rightMatrixIndex],dp[leftMatrixIndex][associativeIndex] + dp[associativeIndex+1][rightMatrixIndex] + currentTimes(leftMatrixIndex,associativeIndex,rightMatrixIndex));
				}
			}
		}

		// for (int i=0;i<number;++i) for (int j=0;j<number;++j) printf("dp[%d %d]=%d\n",i,j,dp[i][j]);
		printf("%lld\n",dp[0][number-1]);
	}
}

/**
 * [currentTimes description] 当前相乘需要的次数
 * 要求操作数相邻
 * @param  leftMatrixIndex  [description] 左矩阵
 * @param  associativeIndex [description] 中间矩阵
 * @param  rightMatrixIndex [description] 右矩阵
 * @return                  [description] 返回[x][y][y][z]即x*y*z
 */
long long currentTimes(int leftMatrixIndex,int associativeIndex, int rightMatrixIndex) {
	return matrix[leftMatrixIndex][0]*matrix[associativeIndex][1]*matrix[rightMatrixIndex][1];
}
