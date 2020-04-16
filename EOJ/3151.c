#include <stdio.h>

int main() {
	int a[107] = { 0 };
	int n, i, k;
	scanf("%d%d%d", &n, &i, &k);

	for (int i = 0; i < n; i++)
		a[i] = i + 1;

	int rest = n; // 表示还有多少个元素没有输出
	int cursor = i - 2; // 表示现在的下标，一开始的下标先要减1，由于下标从0开始再减1

	// 只要有元素没有被输出，就循环打印
	while (rest != 0) {
		for (int j = 0; j < k; ++j) {
			cursor = (cursor + 1) % n;
			// 移动k个单位，如果超过范围就回到头
			while (a[cursor] == -1)
				cursor = (cursor + 1) % n;
			// 如果这个数已经被输出就继续往下
		}

		printf("%d%c", a[cursor], rest == 1 ? '\n' : ' ');
		a[cursor] = -1; // 标记为已输出
		--rest;
	}

	return 0;
}
