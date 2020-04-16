#include <bits/stdc++.h>


int main() {
	int e, f, c;

	while (scanf ("%d%d%d", &e, &f, &c) == 3) {
		int ans = 0;
		int x = e + f;

		while (x >= c) {
			ans += x / c;
			x = x / c + x % c;
		}

		printf ("%d\n", ans);
	}

	return 0;
}
