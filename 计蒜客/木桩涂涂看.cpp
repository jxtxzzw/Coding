#include <bits/stdc++.h>

using namespace std;

int n = 0;
const int MAX_N = 100007;
int dif[MAX_N] = {0};

int main() {
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        int a, b;
        scanf("%d%d", &a, &b);
        dif[a]++;
        dif[b + 1]--; // 维护差分
    }
    int ans = 0;
    for (int i = 1; i <= n; i++) {
        ans += dif[i];
        printf("%d%c", ans, i == n ? '\n' : ' ');
    }

    return 0;
}
