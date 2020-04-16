#include <stdio.h>
#include <algorithm>
#define MAX_N 1007
int a[MAX_N];
int main() {
	int r, n;

	while (scanf ("%d%d", &r, &n) == 2 && r != -1) {
		for (int i = 0; i < n; ++i)
		{ scanf ("%d", &a[i]); }

		std::sort (a, a + n);
		int ans = 0;
		int i = 0;

		while (i < n) {
			int start = a[i];

			//i++;
			while (i < n && a[i] <= start + r) {
				i++;
			}

			start = a[i - 1];

			while (i < n && a[i] <= start + r) {
				i++;
			}

			ans++;
		}

		printf ("%d\n", ans);
	}
}
