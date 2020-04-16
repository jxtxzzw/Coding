#include <stdio.h>
#include <stdlib.h>
#define MAX(a,b) a>b?a:b
#define MAXN 27
#define MAXM 100007

int main() {
	int caseNumber;
	scanf ( "%d", &caseNumber );

	while (caseNumber--) {
		int number, maxCapacity;
		scanf ( "%d%d", &number, &maxCapacity );

		//int dp[N][M] = {{0}};
		int (*dp)[MAXM]=(int(*)[MAXM])malloc(sizeof(int)*MAXN*MAXM);
		/*
		int **dp=(int**)malloc(sizeof(int*)*N);for(int i=0;i<N;i++){dp[i]=(int*)malloc(sizeof(int)*M);}
		for (int i=0;i<N;i++){
			for (int j=0;j<M;j++){
				dp[i][j]=0;
			}
		}
		*/
		// 初始化
		for ( int i = 1; i <= maxCapacity; i++ ) {
			dp[0][i] = 0;
		}
		for ( int i = 1; i <= number; i++ ) {
			dp[i][0] = 0;
		}

		// 读入数据
		int weight[MAXN] = {0};
		int value[MAXN] = {0};
		for ( int i = 1; i <= number; i++ ) {
			scanf ( "%d%d", &weight[i], &value[i] );
		}

		/*
		前i-1件物品
		背包剩余容量为j
		只要不选第i个物品
		前i-1件物品
		背包剩余容量为j-w[i]
		选第i个物品
		取较大者
		*/
		// 遍历，并求得动态转移方程
		for ( int i = 1; i <= number; i++ ) {
			for ( int j = 1; j <= maxCapacity; j++ ) {
				if ( j >= weight[i] )  {
					dp[i][j] = MAX ( dp[i - 1][j - weight[i]] + value[i], dp[i - 1][j] );
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
        // 答案就是dp[number][maxCapacity]
		printf ( "%d\n", dp[number][maxCapacity] );
		free(dp);
	}

	return 0;
}
