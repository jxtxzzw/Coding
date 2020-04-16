#include <stdio.h>
 
int solve(int n, int m) {
	int isKilled[1000] = {0};
	int rest = n;
	int cnt = 0;
	while (rest > 1) {
		for (int i = 1; i <= n; i++) {
			if(isKilled[i]) {
				continue;
			}
			cnt++;
			if (cnt == m) {
				isKilled[i] = 1;
				cnt = 0;
				rest--;
			}
		}
	}
	for (int i = 1; i <= n; i++) {
		if (!isKilled[i]) {
			return i;
		}
	}
}
 
int main() {
	int cas;
	scanf("%d", &cas);
	for (int t = 0; t < cas; t++) {
		printf("case #%d:\n", t);
		int n, m;
		scanf("%d%d", &n, &m);
		printf("%d\n", solve(n, m));
	}
	return 0;
}